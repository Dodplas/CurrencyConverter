package validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConvertRequestValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "1.11",
            "1.00"})
    void shouldValidateSourceAmountHasCorrectDecimalPlaces(String sourceAmountString) {
        final BigDecimal sourceAmount = new BigDecimal(sourceAmountString);

        assertDoesNotThrow(() -> CurrencyConvertRequestValidator.validateDecimalPlaces(sourceAmount));
    }

    @ParameterizedTest
    @CsvSource({
            "1.111",
            "1.1111123334",
            "1"})
    void shouldValidateSourceAmountHasIncorrectDecimalPlaces(String sourceAmountString) {
        final BigDecimal sourceAmount = new BigDecimal(sourceAmountString);

        final MalformedParametersException exception = assertThrows(MalformedParametersException.class,
                () -> {
                    CurrencyConvertRequestValidator.validateDecimalPlaces(sourceAmount);
                });

        assertEquals(String.format("Incorrect source amount: %s %s", sourceAmount, "value must be to 2 decimal places"), exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "GBP,AUS",
            "GBP,GBP"})
    void shouldValidateCurrencyCodesAreCorrect(String sourceCurrency, String targetCurrency) {
        assertDoesNotThrow(() -> CurrencyConvertRequestValidator.validateCurrencyCodes(sourceCurrency, targetCurrency));
    }

    @ParameterizedTest
    @CsvSource({
            "NOTACURR,AUS",
            "GBP,NOTACURR",
            "1.22,AUS",
            "GBP,1.33",
            "1.22,1.33"})
    void shouldValidateCurrencyCodesAreIncorrect(String sourceCurrency, String targetCurrency) {
        final MalformedParametersException exception = assertThrows(MalformedParametersException.class,
                () -> {
                    CurrencyConvertRequestValidator.validateCurrencyCodes(sourceCurrency, targetCurrency);
                });

        assertEquals("Input values incorrect example 1.22 GBP GBP", exception.getMessage());
    }
}