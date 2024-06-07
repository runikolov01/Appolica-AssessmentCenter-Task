package org.CurrencyConvertor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonService {

    private static final String FILE_NAME = "conversions.json";

    public static void saveConversion(String date, double amount, String baseCurrency, String targetCurrency, double convertedAmount) throws IOException {
        JSONArray conversions;

        Path pathFile = Paths.get(FILE_NAME);
        if (Files.exists(pathFile)) {
            String content = new String(Files.readAllBytes(pathFile));
            conversions = new JSONArray(content);
        } else {
            conversions = new JSONArray();
        }

        convertedAmount = Math.round(convertedAmount * 100.0) / 100.0;

        JSONObject conversion = new JSONObject();
        conversion.put("date", date);
        conversion.put("amount", amount);
        conversion.put("base_currency", baseCurrency);
        conversion.put("target_currency", targetCurrency);
        conversion.put("converted_amount", convertedAmount);

        conversions.put(conversion);

        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(conversions.toString(2));
        }
    }
}