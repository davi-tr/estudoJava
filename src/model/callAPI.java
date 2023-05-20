package model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.*;
import com.google.gson.*;

public class callAPI {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException  {
        //Nosso HTTP request é onde vamos informar nosso metodo, nossa URI, HEADERS, etc.
        HttpRequest request =  HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://cep.awesomeapi.com.br/json/05424020"))
                .timeout(Duration.ofSeconds(3)) //Por padrão não precisamos especificar um timeout no nosso HTTP request
                                                //Mas como boas praticas, é importante declarar um timeout, do contrario
                                                //podemos fazer nossa aplicação ficar aguardando uma resposta por tempo
                                                //indefinido.
                .build();
                //Por fim, usamos o metodo build() para montarmos nossa request

        //O HTTP client tem como objetivo estabelecer a conexão com nossa API
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(4)) //Precisamos estabelecer um timeout aqui tambem
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
        System.out.println("CEP: "+obj.getString("cep")); //por exemplo, quero obter o valor da chave CEP do json
        System.out.println("Rua: "+obj.getString("address_name")); //Aqui o nome da rua

        //Aqui temos como objetivo realizar a indentação do json que obtivemos
        //Para isso é necessario utilizar a biblioteca externa: import com.google.gson.*;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(response.body());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);


    }

}
