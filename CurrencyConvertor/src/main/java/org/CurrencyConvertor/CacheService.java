package org.CurrencyConvertor;

import java.util.HashMap;
import java.util.Map;

public class CacheService {

    private final Map<String, Double> cache = new HashMap<>();

    public double getExchangeRate(String date, String baseCurrency, String targetCurrency) {
        String key = date + "_" + baseCurrency + "_" + targetCurrency;
        return cache.getOrDefault(key, -1.0);
    }

    public void saveExchangeRate(String date, String baseCurrency, String targetCurrency, double rate) {
        String key = date + "_" + baseCurrency + "_" + targetCurrency;
        cache.put(key, rate);
    }
}