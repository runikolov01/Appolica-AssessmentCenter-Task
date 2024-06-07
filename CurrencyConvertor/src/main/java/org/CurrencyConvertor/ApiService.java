package org.CurrencyConvertor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONObject;

public class ApiService {

    private static final String BASE_URL = "https://api.fastforex.io/fetch-one";

    public double getExchangeRate(String date, String baseCurrency, String targetCurrency) throws IOException, URISyntaxException {
        ConfigService config = new ConfigService();
        String apiKey = config.getApiKey();

        String urlString = String.format("%s?from=%s&to=%s&date=%s&api_key=%s", BASE_URL, baseCurrency, targetCurrency, date, apiKey);
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
}