package actions;



import converters.CurrencyConverter;
import lombok.extern.slf4j.Slf4j;
import models.Request;
import resourceLoaders.CsvLoader;
import validators.RequestValidator;

import java.util.List;

@Slf4j
public class ConvertCurrency {

    public static void main(String[] args) {
        doCurrencyConversion(args);
    }

    public static void doCurrencyConversion(String[] args) {
        try {
            Request request = Request.convertParamsToObject(args);
            RequestValidator.validateDecimalPlaces(request.getSourceAmount());
            request = CurrencyConverter.convertToTargetCurrencyCode(request);

            System.out.printf("%s, %s, %s",
                    request.getTargetAmount(), request.getTargetCurrencyCode().getCountry(), request.getTargetCurrencyCode().getName());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
