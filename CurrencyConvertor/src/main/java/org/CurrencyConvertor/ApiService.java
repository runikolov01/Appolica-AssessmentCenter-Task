package org.CurrencyConvertor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONException;

public class ApiService {

    private static final String BASE_URL = "https://api.fastforex.io";
    private static final String FETCH_ONE_ENDPOINT = BASE_URL + "/fetch-one";
    private static final String FETCH_ALL_ENDPOINT = BASE_URL + "/fetch-all";

    public double getExchangeRate(String date, String baseCurrency, String targetCurrency) throws IOException, URISyntaxException {
        ConfigService config = new ConfigService();
        String apiKey = config.getApiKey();

        String urlString = String.format("%s?from=%s&to=%s&date=%s&api_key=%s", FETCH_ONE_ENDPOINT, baseCurrency, targetCurrency, date, apiKey);
        URI uri = new URI(urlString);
        HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
        conn.setRequestMethod("GET");

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }
        sc.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getJSONObject("result").getDouble(targetCurrency);
    }

    public Map<String, Double> getExchangeRates(String date) throws IOException, URISyntaxException {
        ConfigService config = new ConfigService();
        String apiKey = config.getApiKey();

        String urlString = String.format("%s?date=%s&api_key=%s", FETCH_ALL_ENDPOINT, date, apiKey);
        URI uri = new URI(urlString);
        HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
        conn.setRequestMethod("GET");

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }
        sc.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject results = jsonResponse.getJSONObject("results");

        Map<String, Double> exchangeRates = new HashMap<>();
        for (String key : results.keySet()) {
            try {
                exchangeRates.put(key, results.getDouble(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return exchangeRates;
    }
}