package com.challenge.one.conversor.historial;

import com.challenge.one.conversor.convertir.ConvertirRespuesta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HistorialDeConversion {

    private ConvertirRespuesta convertirRespuesta;

    private Map<String, ConvertirRespuesta> historial;

    Gson gson;

    public HistorialDeConversion() {
        this.historial = new HashMap<>();
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void guardarConversion(ConvertirRespuesta convertirRespuesta) {
        FileReader leer = obtenerHistorialDeConversion();

        Type lista = new TypeToken<Map<String, ConvertirRespuesta>>(){}.getType();
        historial = gson.fromJson(leer, lista);

        if (historial == null) {
            historial = new HashMap<>();
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatoFechaHora = LocalDateTime.now().format(formatoFecha);

        historial.put(formatoFechaHora, convertirRespuesta);

        try {
            Writer escribir = new FileWriter("historial.json");

            gson.toJson(historial, escribir);

            escribir.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static FileReader obtenerHistorialDeConversion() {
        File archivo = new File("historial.json");
        FileReader leer;
        try {
            if (!archivo.exists() && !archivo.createNewFile()) {
                throw new RuntimeException("Error al crear el historial.");
            }
            leer = new FileReader("historial.json");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return leer;
    }

    public void imprimirHistorial()  {
        FileReader leer = obtenerHistorialDeConversion();

        Type lista = new TypeToken<Map<String, ConvertirRespuesta>>(){}.getType();

        historial = gson.fromJson(leer, lista);


        System.out.println("-----------------------------------------------------------------------------\n\t\t    Tu historial de conversión de monedas");
        for (Map.Entry<String, ConvertirRespuesta> entry : historial.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("-----------------------------------------------------------------------------\n");

    }

}