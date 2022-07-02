package validators;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

public class CurrencyConvertRequestValidator {

    public static void validateDecimalPlaces(BigDecimal sourceAmount) {
        if (sourceAmount.scale() != 2) {
            throw new MalformedParametersException(String.format("Incorrect source amount: %s %s", sourceAmount, "value must be to 2 decimal places" ));
        }
    }

    public static void validateCurrencyCodes(String sourceCurrencyCode, String targetCurrencyCode) {
        if (sourceCurrencyCode == "" || targetCurrencyCode == "") {
            throw new MalformedParametersException(String.format("Incorrect currency code/s: %s %s %s",
                    sourceCurrencyCode, targetCurrencyCode, "value must be a valid currency code" ));
        }
    }
}
