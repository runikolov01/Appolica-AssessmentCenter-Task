package org.CurrencyConvertor;

import java.util.regex.Pattern;

public class ValidationService {

    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
}