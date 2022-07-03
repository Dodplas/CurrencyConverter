package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @ParameterizedTest
    @CsvSource({
            "1.11, GBP, AUS",
            "1.00, GBP, USD"})
    void shouldConvertParamsToObject(String sourceAmount, String sourceCurrencyCode, String targetCurrencyCode) {
        Request expectedRequest = Request.builder()
                          .sourceAmount(new BigDecimal(sourceAmount))
                          .sourceCurrencyCode(CurrencyCode.builder().code(sourceCurrencyCode).build())
                          .targetCurrencyCode(CurrencyCode.builder().code(targetCurrencyCode).build())
                          .build();

        final Request convertedRequest = Request.convertParamsToObject(new String[]{sourceAmount, sourceCurrencyCode, targetCurrencyCode});

        assertEquals(expectedRequest.getBaseAmount(), convertedRequest.getBaseAmount());
        assertEquals(expectedRequest.getSourceAmount(), convertedRequest.getSourceAmount());
        assertEquals(expectedRequest.getTargetAmount(), convertedRequest.getTargetAmount());
        assertEquals(expectedRequest.getSourceCurrencyCode().getCode(), convertedRequest.getSourceCurrencyCode().getCode());
        assertEquals(expectedRequest.getTargetCurrencyCode().getCode(), convertedRequest.getTargetCurrencyCode().getCode());
    }

    @Test
    void shouldThrowExceptionWhenConvertingParamsToObject() {

        final MalformedParametersException exception = assertThrows(MalformedParametersException.class,
                () -> Request.convertParamsToObject(new String[]{"SHOULDBENUMBER", "GBP", "USD"}));

        assertEquals("Input values incorrect example 1.22 GBP GBP", exception.getMessage());
    }

}