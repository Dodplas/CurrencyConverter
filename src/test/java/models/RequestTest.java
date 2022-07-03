package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @ParameterizedTest
    @CsvSource({
            "1.11, GBP, AUS",
            "1.00, GBP, USD"})
    void shouldConvertParamsToObject(String sourceAmount, String sourceCurrencyCode, String targetCurrencyCode) {

        final Request convertedRequest = Request.convertParamsToObject(new String[]{sourceAmount, sourceCurrencyCode, targetCurrencyCode});

        //TODO assert object values
        fail();
    }

    @Test
    void shouldThrowExceptionWhenConvertingParamsToObject() {

        final Request convertedRequest = Request.convertParamsToObject(new String[]{"SHOULDBENUMBER", "GBP", "USD"});

        //TODO assert object values
        fail();
    }

}