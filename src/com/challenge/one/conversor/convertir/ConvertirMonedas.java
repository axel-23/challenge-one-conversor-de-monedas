package com.challenge.one.conversor.convertir;

import com.challenge.one.conversor.exception.ErrorConvertirException;
import com.challenge.one.conversor.historial.HistorialDeConversion;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertirMonedas {

    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;
    URI urlAPI;
    Gson gson;

    public ConvertirMonedas() {
        client = HttpClient.newHttpClient();
        gson = new GsonBuilder().create();

    }

    public double consultarAPI(String monedaConvertir, String monedaDestino, double cantidad) {

        try {

            urlAPI = URI.create("https://v6.exchangerate-api.com/v6/6c77d940d4d03573aa19415d/pair/" + monedaConvertir + "/" + monedaDestino + "/" + cantidad);

            request = HttpRequest.newBuilder()
                    .uri(urlAPI)
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ConvertirRespuesta convertirRespuesta = gson.fromJson(response.body(), ConvertirRespuesta.class);

            if (convertirRespuesta.result().equals("error")) {
                throw new ErrorConvertirException("Error de respuesta: " + convertirRespuesta.error_type());
            }

            HistorialDeConversion historyConversionRate = new HistorialDeConversion();
            historyConversionRate.guardarConversion(convertirRespuesta);

            return convertirRespuesta.conversion_result();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Error al dar formato a la respuesta.");
        }

    }
}