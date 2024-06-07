package org.CurrencyConvertor;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConversion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the date in format 'YYYY-MM-DD': ");
        String dateInput = scanner.nextLine();

        if (!ValidationService.isValidDate(dateInput)) {
            System.out.println("Please provide a valid date in format 'YYYY-MM-DD'");
            return;
        }

        CurrencyConverter converter = new CurrencyConverter(dateInput);

        while (true) {
            String amountInput = scanner.nextLine();
            if (amountInput.equalsIgnoreCase("END")) break;

            if (!ValidationService.isValidAmount(amountInput)) {
                System.out.println("Please enter a valid amount");
                continue;
            }

            double amount = Double.parseDouble(amountInput);

            String baseCurrency = scanner.nextLine();
            if (ValidationService.isValidCurrency(baseCurrency)) {
                System.out.println("Please enter a valid currency code");
                continue;
            }

            String targetCurrency = scanner.nextLine();
            if (ValidationService.isValidCurrency(targetCurrency)) {
                System.out.println("Please enter a valid currency code");
                continue;
            }

            try {
                double convertedAmount = converter.convert(amount, baseCurrency.toUpperCase(), targetCurrency.toUpperCase());
                System.out.printf("%.2f %s is %.2f %s\n", amount, baseCurrency.toUpperCase(), convertedAmount, targetCurrency.toUpperCase());
            } catch (IOException e) {
                System.out.println("Error occurred while converting currencies.");
            }
        }
    }
}