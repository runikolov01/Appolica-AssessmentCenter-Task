package org.CurrencyConvertor;

import java.util.regex.Pattern;

public class ValidationService {

    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static boolean isValidCurrency(String currency) {
        return !CURRENCY_PATTERN.matcher(currency).matches();
    }

    public static boolean isValidAmount(String amount) {
        return AMOUNT_PATTERN.matcher(amount).matches();
    }

    public static boolean isValidDate(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
}