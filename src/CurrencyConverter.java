import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_KEY = "a136550dd81777be37ca3ae3ef";
    private static final String API_URL_TEMPLATE = "https://v6.exchangerate-api.com/v6/%s/latest/%s";
    private static final Gson gson = new Gson();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarBienvenida();
        
        while (true) {
            mostrarMenu();
            int opcion = capturarOpcion();
            
            switch (opcion) {
                case 1:
                    realizarConversion();
                    break;
                case 2:
                    mostrarMonedasDisponibles();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el Conversor de Divisas!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 3.");
            }
            
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine();
        }
    }
    
    private static void mostrarBienvenida() {

        System.out.println("¡Bienvenido al conversor de divisas en tiempo real!");
    }
    
    private static void mostrarMenu() {
        System.out.println("\n");
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("1. Realizar conversión de divisas");
        System.out.println("2. Ver monedas disponibles");
        System.out.println("3. Salir");
        System.out.println("\n");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int capturarOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        return opcion;
    }
    
    private static void realizarConversion() {
        System.out.println("\n");
        System.out.println("CONVERSIÓN DE DIVISAS");
        
        try {
            // Capturar moneda origen
            System.out.print("Ingrese el código de la moneda origen (ej: USD, EUR, MXN): ");
            String monedaOrigen = scanner.nextLine().toUpperCase();
            
            // Capturar moneda destino
            System.out.print("Ingrese el código de la moneda destino (ej: USD, EUR, MXN): ");
            String monedaDestino = scanner.nextLine().toUpperCase();
            
            // Capturar cantidad
            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad;
            while (!scanner.hasNextDouble()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
            cantidad = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea
            
            // Obtener datos de la API usando la moneda origen como base
            String jsonResponse = obtenerDatosAPI(monedaOrigen);
            if (jsonResponse == null) {
                System.out.println("Error: No se pudieron obtener los datos de la API.");
                return;
            }
            
            // Parsear JSON con Gson (ExchangeRate-API)
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (jsonObject.has("result") && !"success".equalsIgnoreCase(jsonObject.get("result").getAsString())) {
                System.out.println("Error de API: " + (jsonObject.has("error-type") ? jsonObject.get("error-type").getAsString() : "desconocido"));
                return;
            }
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
            if (rates == null) {
                System.out.println("Error: Formato de respuesta de API inválido.");
                return;
            }
            if (!rates.has(monedaDestino)) {
                System.out.println("Error: Moneda destino no válida.");
                return;
            }

            // Realizar conversión
            double tasaDestino = rates.get(monedaDestino).getAsDouble();
            double resultado = cantidad * tasaDestino;
            
            // Mostrar resultado
            System.out.println("\n");
            System.out.println("RESULTADO");
            System.out.printf("%.2f %s = %.2f %s \n",
                            cantidad, monedaOrigen, resultado, monedaDestino);
            System.out.printf("Tasa de cambio: 1 %s = %.4f %s \n",
                            monedaOrigen, tasaDestino, monedaDestino);
            
        } catch (Exception e) {
            System.out.println("Error durante la conversión: " + e.getMessage());
        }
    }
    
    private static void mostrarMonedasDisponibles() {
        System.out.println("\n");
        System.out.println("MONEDAS DISPONIBLES");
        
        try {
            // Usamos USD como base para listar monedas disponibles
            String jsonResponse = obtenerDatosAPI("USD");
            if (jsonResponse == null) {
                System.out.println("Error: No se pudieron obtener los datos de la API.");
                return;
            }
            
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (jsonObject.has("result") && !"success".equalsIgnoreCase(jsonObject.get("result").getAsString())) {
                System.out.println("Error de API: " + (jsonObject.has("error-type") ? jsonObject.get("error-type").getAsString() : "desconocido"));
                return;
            }
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
            
            if (rates == null) {
                System.out.println("Error: Formato de respuesta de API inválido.");
                return;
            }
            
            System.out.println("Monedas disponibles:");
            int contador = 0;
            for (String moneda : rates.keySet()) {
                System.out.printf("%-8s", moneda);
                contador++;
                if (contador % 8 == 0) {
                    System.out.println();
                }
            }
            if (contador % 8 != 0) {
                System.out.println();
            }
            System.out.println("\nTotal de monedas: " + contador);
            
        } catch (Exception e) {
            System.out.println("Error al obtener las monedas disponibles: " + e.getMessage());
        }
    }
    
    private static String obtenerDatosAPI(String baseCurrency) {
        try {
            String endpoint = String.format(API_URL_TEMPLATE, API_KEY, baseCurrency);
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error de conexión: " + responseCode);
                return null;
            }
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );
            
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            return response.toString();
            
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
