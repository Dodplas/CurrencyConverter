package actions;



import lombok.extern.slf4j.Slf4j;
import models.CurrencyConverterRequest;
import resourceLoaders.CsvLoader;
import validators.CurrencyConvertRequestValidator;

import java.util.List;

@Slf4j
public class CurrencyConverter {

    public static void main(String[] args) {
        startCurrencyConversion(args);

    }

    public static void startCurrencyConversion(String[] args) {
        System.out.println("This is a simple command line program!");
        System.out.println("Your Command Line arguments are:");
        for (String str: args) {
            System.out.println(str);
        }

        try {
            CurrencyConverterRequest request = CurrencyConverterRequest.convertParamsToObject(args);
            CurrencyConvertRequestValidator.validateDecimalPlaces(request.getSourceAmount());
            final List<List<String>> currencyCodes = CsvLoader.loadCsv("src/main/resources/exchange-rates-to-gbp.csv");


        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
