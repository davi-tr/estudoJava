package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.*;
import com.google.gson.*;

public class callAPI {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException  {

        HttpRequest request =  HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://cep.awesomeapi.com.br/json/05424020"))
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(4))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        JSONObject obj = new JSONObject(response.body());
        System.out.println("CEP: "+obj.getString("cep"));
        System.out.println("Rua: "+obj.getString("address_name"));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(response.body().toString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);


    }

}
