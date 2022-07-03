package validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "1.11",
            "1.00"})
    void shouldValidateSourceAmountHasCorrectDecimalPlaces(String sourceAmountString) {
        final BigDecimal sourceAmount = new BigDecimal(sourceAmountString);

        assertDoesNotThrow(() -> RequestValidator.validateDecimalPlaces(sourceAmount));
    }

    @ParameterizedTest
    @CsvSource({
            "1.111",
            "1.1111123334",
            "1"})
    void shouldValidateSourceAmountHasIncorrectDecimalPlaces(String sourceAmountString) {
        final BigDecimal sourceAmount = new BigDecimal(sourceAmountString);

        final MalformedParametersException exception = assertThrows(MalformedParametersException.class,
                () -> RequestValidator.validateDecimalPlaces(sourceAmount));

        assertEquals(String.format("Incorrect source amount: %s %s", sourceAmount, "value must be to 2 decimal places"), exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "GBP,AUS",
            "GBP,GBP"})
    void shouldValidateCurrencyCodesAreValidFormat(String sourceCurrency, String targetCurrency) {
        assertDoesNotThrow(() -> RequestValidator.validateCurrencyCodes(sourceCurrency, targetCurrency));
    }

    @ParameterizedTest
    @CsvSource({
            ",AUS",
            "GBP,",
            " ,AUS",
            "GBP, ",
            "1.22,AUS",
            "GBP,1.33",
            "1.22,1.33"})
    void shouldValidateCurrencyCodesAreInvalidFormat(String sourceCurrency, String targetCurrency) {
        final MalformedParametersException exception = assertThrows(MalformedParametersException.class,
                () -> RequestValidator.validateCurrencyCodes(sourceCurrency, targetCurrency));

        assertEquals(String.format("Incorrect currency code/s: %s and %s value must be a valid currency code, e.g. AUD",
                sourceCurrency, targetCurrency),
                exception.getMessage());
    }
}