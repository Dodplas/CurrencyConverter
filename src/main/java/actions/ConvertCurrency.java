package actions;

import converters.CurrencyConverter;
import lombok.extern.slf4j.Slf4j;
import models.Request;
import validators.RequestValidator;

/**
 * Main application class
 */
@Slf4j
public class ConvertCurrency {

    public static void main(String[] args) {
        try {
            Request request = Request.convertParamsToObject(args);
            RequestValidator.validateDecimalPlaces(request.getSourceAmount());
            request = CurrencyConverter.convertToTargetCurrencyCode(request);

            System.out.printf("%.2f, %s, %s",
                    request.getTargetAmount(), request.getTargetCurrencyCode().getCountry(), request.getTargetCurrencyCode().getName());

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
