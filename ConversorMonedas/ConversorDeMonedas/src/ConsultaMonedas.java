   import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

       public class ConsultaMonedas {

           private static final String API_KEY = "6a2b9241b7ed4b8d2aef7c7c";
           private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

           public Monedas buscarMoneda(String tipoDeDivisa) {
               if (tipoDeDivisa == null || tipoDeDivisa.isEmpty()) {
                   throw new IllegalArgumentException("El tipo de divisa no puede estar vacío.");
               }

               URI direction = URI.create(API_URL + API_KEY + "/latest/" + tipoDeDivisa);

               HttpClient client = HttpClient.newHttpClient();
               HttpRequest request = HttpRequest.newBuilder()
                       .uri(direction)
                       .build();

               try {
                   HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                   if (response.statusCode() != 200) {
                       throw new RuntimeException("Error en la solicitud a la API. Código de estado: " + response.statusCode());
                   }


                   return new Gson().fromJson(response.body(), Monedas.class);

               } catch (IOException | InterruptedException e) {
                   e.printStackTrace();
                   throw new RuntimeException("No fue posible hacer la conversión.", e);
               }
           }
       }
