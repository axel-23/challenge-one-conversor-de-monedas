package com.challenge.one.conversor;

import com.challenge.one.conversor.convertir.ConvertirMonedas;
import com.challenge.one.conversor.historial.HistorialDeConversion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entradaTeclado = new Scanner(System.in);
        ConvertirMonedas conversor = new ConvertirMonedas();

        int opcion = 0;

        while(opcion != 8) {

            String monedaConvertir = "";
            String monedaDestino = "";

            System.out.print("""
                    *******************************************************
                    Sea bienvenido/a al Conversor de Monedas =]
                    
                    1)  Dólar =>> Peso Argentino
                    2)  Peso Argentino =>> Dólar
                    3)  Dólar =>> Real Brasileño
                    4)  Real Brasileño =>> Dólar
                    5)  Dólar =>> Peso Colombiano
                    6)  Peso Colombiano =>> Dólar
                    7)  Ver Historial
                    8)  Salir
                    
                    Elija una opción válida:
                    *******************************************************
                    """);

            opcion = entradaTeclado.nextInt();

            if (opcion == 8) {
                System.out.println("¡Hasta luego, vuelva pronto!");
                break;
            };

            Integer[] opciones = new Integer[]{1, 2, 3, 4, 5, 6};

            List<Integer> listaOpciones = new ArrayList<>(Arrays.asList(opciones));

            switch (opcion) {
                case 1:
                    monedaDestino = "ARS";
                    monedaConvertir = "USD";
                    break;
                case 2:
                    monedaDestino = "BRL";
                    monedaConvertir = "USD";
                    break;
                case 3:
                    monedaDestino = "COP";
                    monedaConvertir = "USD";
                    break;
                case 4:
                    monedaDestino = "USD";
                    monedaConvertir = "ARS";
                    break;
                case 5:
                    monedaDestino = "USD";
                    monedaConvertir = "BRL";
                    break;
                case 6:
                    monedaDestino = "USD";
                    monedaConvertir = "COP";
                    break;
                case 7:
                    HistorialDeConversion historial = new HistorialDeConversion();
                    historial.imprimirHistorial();
                    break;
                default:
                    System.out.println("Por favor, digite una opción valida");
            }

            if (listaOpciones.contains(opcion)) {
                System.out.print("Ingrese el valor de la moneda que desea convertir: ");
                double cantidad = entradaTeclado.nextDouble();

                double tasaDeConversion = conversor.consultarAPI(monedaConvertir, monedaDestino, cantidad);

                System.out.println("""
                        ------------------------------------------------------------
                                %.2f [%s] equivalen a %.2f [%s]
                        ------------------------------------------------------------
                        """.formatted(cantidad, monedaConvertir, tasaDeConversion, monedaDestino));
            }

        }


    }
}