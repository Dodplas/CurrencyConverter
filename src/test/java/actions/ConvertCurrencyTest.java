package actions;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ConvertCurrencyTest {

    @ParameterizedTest
    @CsvSource({
            "1.11,GBP,AUS",
            "1.00,GBP,GBP"})
    void shouldValidateParametersAreCorrect(String sourceAmount, String sourceCurrencyCode, String targetCurrencyCode) throws Exception {
        ConvertCurrency.doCurrencyConversion(new String[]{sourceAmount, sourceCurrencyCode, targetCurrencyCode});

        fail();
        //TODO how to verify, no exception thrown?
    }


}