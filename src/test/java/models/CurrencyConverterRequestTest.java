package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterRequestTest {

    @ParameterizedTest
    @CsvSource({
            "1.11, GBP, AUS",
            "1.00, GBP, USD"})
    void shouldConvertParamsToObject(String sourceAmount, String sourceCurrencyCode, String targetCurrencyCode) {

        final CurrencyConverterRequest convertedRequest = CurrencyConverterRequest.convertParamsToObject(new String[]{sourceAmount, sourceCurrencyCode, targetCurrencyCode});

        //TODO assert object values
        fail();
    }

    @Test
    void shouldThrowExceptionWhenConvertingParamsToObject() {

        final CurrencyConverterRequest convertedRequest = CurrencyConverterRequest.convertParamsToObject(new String[]{"SHOULDBENUMBER", "GBP", "USD"});

        //TODO assert object values
        fail();
    }

}