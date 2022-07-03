package validators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

/**
 * Holds the logic to validate the initial user request
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestValidator {

    /**
     * validates that the sourceAmount the user passes in has only 2 decimal places
     * @param sourceAmount the amount to convert passed in by the user
     */
    public static void validateDecimalPlaces(BigDecimal sourceAmount) {
        if (sourceAmount.scale() != 2) {
            throw new MalformedParametersException(String.format("Incorrect source amount: %s %s", sourceAmount, "value must be to 2 decimal places" ));
        }
    }

    /**
     * validates that the currency codes are not empty/null and not a number
     * @param sourceCurrencyCode currency code to convert from
     * @param targetCurrencyCode currency code to convert to
     */
    public static void validateCurrencyCodes(String sourceCurrencyCode, String targetCurrencyCode) {
        if (StringUtils.isEmpty(sourceCurrencyCode) || StringUtils.isEmpty(targetCurrencyCode)
                    || !StringUtils.isAlpha(sourceCurrencyCode) || !StringUtils.isAlpha(targetCurrencyCode)) {

            throw new MalformedParametersException(String.format("Incorrect currency code/s: %s and %s %s",
                    sourceCurrencyCode, targetCurrencyCode, "value must be a valid currency code, e.g. AUD" ));
        }
    }
}
