package actions;


import com.sun.tools.javac.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @ParameterizedTest
    @CsvSource({
            "1.11,GBP,AUS",
            "1.00,GBP,GBP"})
    void shouldValidateParametersAreCorrect(String sourceAmount, String sourceCurrencyCode, String targetCurrencyCode) throws Exception {
        CurrencyConverter.startCurrencyConversion(new String[]{sourceAmount, sourceCurrencyCode, targetCurrencyCode});

        fail();
        //TODO how to verify, no exception thrown?
    }


}