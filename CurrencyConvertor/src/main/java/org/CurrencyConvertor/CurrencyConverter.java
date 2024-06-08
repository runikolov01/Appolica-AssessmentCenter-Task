package org.CurrencyConvertor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyConverter {
    private final String date;
    private final ApiService apiService;
    private final CacheService cacheService;
    private final Map<String, Double> exchangeRateCache;
    private static final Logger logger = Logger.getLogger(CurrencyConverter.class.getName());

    public CurrencyConverter(String date) {
        this.date = date;
        this.apiService = new ApiService();
        this.cacheService = new CacheService();
        this.exchangeRateCache = new HashMap<>();
    }

    public double convert(double amount, String baseCurrency, String targetCurrency) throws IOException {
        if (isValidCurrency(baseCurrency) || isValidCurrency(targetCurrency)) {
            System.out.println("Enter valid currencies");
        }

        double rate;

        if (exchangeRateCache.containsKey(baseCurrency)) {
            rate = exchangeRateCache.get(baseCurrency);
        } else {
            rate = cacheService.getExchangeRate(date, baseCurrency, targetCurrency);
            if (rate == -1) {
                try {
                    rate = apiService.getExchangeRate(date, baseCurrency, targetCurrency);
                    if (rate == -1) {
                        throw new IllegalArgumentException("Enter valid currency");
                    }
                    cacheService.saveExchangeRate(date, baseCurrency, targetCurrency, rate);
                } catch (URISyntaxException e) {
                    logger.log(Level.SEVERE, "Error occurred while fetching exchange rate from API", e);
                    throw new IOException("Error fetching exchange rate from API", e);
                }
            }
            exchangeRateCache.put(baseCurrency, rate);
        }

        double convertedAmount = amount * rate;
        JsonService.saveConversion(date, amount, baseCurrency, targetCurrency, convertedAmount);
        return convertedAmount;
    }

    private boolean isValidCurrency(String currency) throws IOException {
        try {
            Map<String, Double> exchangeRates = apiService.getExchangeRates(date);
            return !exchangeRates.containsKey(currency);
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, "Error occurred while fetching exchange rates from API", e);
            throw new IOException("Error fetching exchange rates from API", e);
        }
    }
}