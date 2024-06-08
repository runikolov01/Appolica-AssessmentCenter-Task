package org.CurrencyConvertor;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConversion {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the date in format 'YYYY-MM-DD' as a command-line argument.");
            return;
        }

        String dateInput = args[0];

        if (!ValidationService.isValidDate(dateInput)) {
            System.out.println("Please provide a valid date in format 'YYYY-MM-DD'");
            return;
        }

        CurrencyConverter converter = new CurrencyConverter(dateInput);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the amount to convert (or type 'END' to exit): ");
            String amountInput = scanner.nextLine();
            if (amountInput.equalsIgnoreCase("END")) break;

            if (!ValidationService.isValidAmount(amountInput)) {
                System.out.println("Please enter a valid amount");
                continue;
            }

            double amount = Double.parseDouble(amountInput);

            System.out.print("Enter the base currency code: ");
            String baseCurrency = scanner.nextLine();
            if (!ValidationService.isValidCurrency(baseCurrency)) {
                System.out.println("Please enter a valid currency code");
                continue;
            }

            System.out.print("Enter the target currency code: ");
            String targetCurrency = scanner.nextLine();
            if (!ValidationService.isValidCurrency(targetCurrency)) {
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
        scanner.close();
    }
}