package com.oneproyect.conversormonedas.service;

import com.google.gson.Gson;
import com.oneproyect.conversormonedas.classes.ExchangeRateResponse;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Servicio encargado de obtener tasas de cambio desde la API externa
 * ExchangeRate-API y de realizar conversiones entre distintas monedas.
 * Utiliza HttpClient para hacer solicitudes HTTP y Gson para parsear la respuesta JSON.
 *
 * @author Luciano Emmanuel Gatti Flekenstein
 */
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyService {
    
    // Clave de API proporcionada por ExchangeRate-API
    private static final String API_KEY = "b3a18f1d3e30ae55d8625907";

    // URL base de la API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Cliente HTTP para realizar las peticiones
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    // Objeto Gson para convertir JSON a objetos Java
    private static final Gson gson = new Gson();

    /**
     * Realiza una solicitud HTTP GET a la API para obtener las tasas de cambio
     * respecto a una moneda base específica (por ejemplo: USD).
     *
     * @param base código de la moneda base (ej. "USD")
     * @return un objeto ExchangeRateResponse con las tasas obtenidas, o null si falla
     */
    public ExchangeRateResponse obtenerTasas(String base) {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            // Ejecuta la petición y obtiene la respuesta como texto
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Convierte la respuesta JSON a un objeto Java
            return gson.fromJson(response.body(), ExchangeRateResponse.class);
        } catch (Exception e) {
            // Muestra el error si ocurre una excepción durante la solicitud
            System.out.println("Error al obtener tasas: " + e.getMessage());
            return null;
        }
    }

    /**
     * Convierte un monto entre dos monedas, utilizando las tasas de cambio
     * previamente obtenidas desde la API.
     *
     * @param amount monto a convertir
     * @param from moneda de origen (ej. "ARS")
     * @param to moneda de destino (ej. "USD")
     * @param rates objeto con las tasas de cambio
     * @return monto convertido a la moneda destino
     * @throws IllegalArgumentException si alguna de las monedas no está presente en las tasas
     */
    public BigDecimal convertirMoneda(BigDecimal amount, String from, String to, ExchangeRateResponse rates) {
        BigDecimal rateFrom = rates.getConversionRates().get(from);
        BigDecimal rateTo = rates.getConversionRates().get(to);

        // Verifica si ambas monedas están disponibles
        if (rateFrom == null || rateTo == null) {
            throw new IllegalArgumentException("Tasa no disponible para las monedas indicadas.");
        }

        // Primero convierte el monto a USD (o moneda base), luego a la moneda de destino
        BigDecimal amountInUSD = amount.divide(rateFrom, 10, BigDecimal.ROUND_HALF_UP);
        return amountInUSD.multiply(rateTo);
    }
}
