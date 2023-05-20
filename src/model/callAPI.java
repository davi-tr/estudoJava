package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Properties;

import org.json.*;
import com.google.gson.*;

public class callAPI {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException  {
        //Nosso HTTP request é onde vamos informar nosso metodo, nossa URI, HEADERS, etc.
        String chave = loadKey();
        HttpRequest request =  HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("https://imdb-api.com/pt/API/Search/%s/MoonLight",chave)))
                .timeout(Duration.ofSeconds(20)) //Por padrão não precisamos especificar um timeout no nosso HTTP request
                                                //Mas como boas práticas, é importante declarar um timeout, do contrário
                                                //podemos fazer nossa aplicação ficar aguardando uma resposta por tempo
                                                //indefinido.
                .build();
                //Por fim, usamos o metodo build() para montarmos nossa request

        //O HTTP client tem como objetivo estabelecer a conexão com nossa API
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20)) //Precisamos estabelecer um timeout aqui tambem
                                                        //Neste timeout vamos definir o limite de tempo para estabelecer
                                                        //Conexão com nossa API, diferente do tempo limite para obter
                                                        //Uma resposta.
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        //Aqui vamos guardar nossa resposta dentro de uma string, para isso vamos chamar nosso httpClient e passar o
        //Request que declaramos no começo.


        //Essa parte tem o objetivo de obter valores do json que foi gerado pela requisição que fizemos anteriormente.
        //Para isso precisamos da biblioteca externa: import org.json.*;
        JSONObject obj = new JSONObject(response.body());
        String test =  obj.getJSONArray("results").getJSONObject(0).toString();
        JSONObject selected = obj.getJSONArray("results").getJSONObject(0);
        System.out.println("ID: "+selected.getString("id")); //por exemplo, quero obter o valor da chave CEP do json
        System.out.println("Titulo: "+selected.getString("title"));
        //System.out.println("ID: "+obj.getString("id")); //por exemplo, quero obter o valor da chave CEP do json
       //System.out.println("Rua: "+obj.getString("title")); //Aqui o nome da rua

        //Aqui temos como objetivo realizar a indentação do json que obtivemos
        //Para isso é necessario utilizar a biblioteca externa: import com.google.gson.*;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(test);
        String prettyJsonString = gson.toJson(je);
        //System.out.println(prettyJsonString);


    }

    public static String loadKey() throws IOException {
        InputStream input = new FileInputStream("chaves.properties") ;
            Properties prop = new Properties();

            prop.load(input);

        return (prop.getProperty("api.key"));
    }
}
