package actions;

import converters.CurrencyConverter;
import models.CurrencyCode;
import models.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import validators.RequestValidator;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ConvertCurrencyTest {

    MockedStatic<Request> requestMock = Mockito.mockStatic(Request.class);
    MockedStatic<RequestValidator> requestValidatorMock = Mockito.mockStatic(RequestValidator.class);
    MockedStatic<CurrencyConverter> currencyConverterMock = Mockito.mockStatic(CurrencyConverter.class);

    @AfterEach
    void setUp() {
        requestMock.close();
        requestValidatorMock.close();
        currencyConverterMock.close();
    }

    @ParameterizedTest
    @CsvSource({
            "1.11,GBP,AUD",
            "1.00,GBP,GBP"})
    void shouldValidateParametersAreCorrect(String sourceAmount, String sourceCurrencyCode, String targetCurrencyCode) {
        requestMock.when(Request::builder).thenCallRealMethod();

        Request request = Request.builder()
                          .sourceAmount(new BigDecimal(sourceAmount))
                          .sourceCurrencyCode(CurrencyCode.builder()
                                                      .code(sourceCurrencyCode).build())
                          .targetCurrencyCode(CurrencyCode.builder()
                                                      .country("test country")
                                                      .name("test name")
                                                      .code(targetCurrencyCode).build())
                          .build();


        requestMock.when(() -> Request.convertParamsToObject(any())).thenReturn(request);
        currencyConverterMock.when(() -> CurrencyConverter.convertToTargetCurrencyCode(any(Request.class))).thenReturn(request);

        assertDoesNotThrow(() -> ConvertCurrency.main(new String[]{sourceAmount, sourceCurrencyCode, targetCurrencyCode}));

        requestMock.verify(() -> Request.convertParamsToObject(any()), times(1));
        requestValidatorMock.verify(() -> RequestValidator.validateDecimalPlaces(any()), times(1));
        currencyConverterMock.verify(() -> CurrencyConverter.convertToTargetCurrencyCode(any(Request.class)), times(1));
    }

    @Test
    void shouldCatchExceptionsForTerminalOutput() {
        MalformedParametersException exception = Mockito.mock(MalformedParametersException.class);
        requestMock.when(() -> Request.convertParamsToObject(any())).thenThrow(exception);


        ConvertCurrency.main(new String[]{"1.11", "GBP", "AUS"});

        verify(exception, times(1)).getMessage();
    }

}