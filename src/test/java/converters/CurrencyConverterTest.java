package converters;

import models.CurrencyCode;
import models.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import resourceloaders.CsvLoader;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class CurrencyConverterTest {

    MockedStatic<CsvLoader> csvLoader = Mockito.mockStatic(CsvLoader.class);

    @AfterEach
    void setUp() {
        csvLoader.close();
    }

    @Test
    void shouldConvertAmountToTargetCurrencyCode() throws IOException {
        List<String> csvLineOne = List.of("test country 1", "test name 1", "AAA", "1.1111");
        List<String> csvLineTwo = List.of("test country 2", "test name 2", "BBB", "3.45252354");
        List<List<String>> csvLines = List.of(csvLineOne, csvLineTwo);

        Request testRequest = Request.builder()
                                      .sourceAmount(new BigDecimal("1.11"))
                                      .sourceCurrencyCode(CurrencyCode.builder().code("AAA").build())
                                      .targetCurrencyCode(CurrencyCode.builder().code("BBB").build())
                                      .build();

        csvLoader.when(() -> CsvLoader.loadCsv(anyString())).thenReturn(csvLines);

        Request convertedRequest = CurrencyConverter.convertToTargetCurrencyCode(testRequest);

        assertEquals(new BigDecimal("3.84"), convertedRequest.getTargetAmount());
    }

    @Test
    void shouldThrowException_SourceCurrencyCodeDoesNotExist() {
        List<String> csvLineOne = List.of("test country 1", "test name 1", "AAA", "1.1111");
        List<String> csvLineTwo = List.of("test country 2", "test name 2", "BBB", "3.45252354");
        List<List<String>> csvLines = List.of(csvLineOne, csvLineTwo);

        Request testRequest = Request.builder()
                                      .sourceAmount(new BigDecimal("1.11"))
                                      .sourceCurrencyCode(CurrencyCode.builder().code("CCC").build())
                                      .targetCurrencyCode(CurrencyCode.builder().code("BBB").build())
                                      .build();

        csvLoader.when(() -> CsvLoader.loadCsv(anyString())).thenReturn(csvLines);

        final InvalidObjectException exception = assertThrows(InvalidObjectException.class,
                () -> CurrencyConverter.convertToTargetCurrencyCode(testRequest));

        assertEquals("No currency code of CCC was found", exception.getMessage());
    }

    @Test
    void shouldThrowException_TargetCurrencyCodeDoesNotExist() {
        List<String> csvLineOne = List.of("test country 1", "test name 1", "AAA", "1.1111");
        List<String> csvLineTwo = List.of("test country 2", "test name 2", "BBB", "3.45252354");
        List<List<String>> csvLines = List.of(csvLineOne, csvLineTwo);

        Request testRequest = Request.builder()
                                      .sourceAmount(new BigDecimal("1.11"))
                                      .sourceCurrencyCode(CurrencyCode.builder().code("AAA").build())
                                      .targetCurrencyCode(CurrencyCode.builder().code("CCC").build())
                                      .build();

        csvLoader.when(() -> CsvLoader.loadCsv(anyString())).thenReturn(csvLines);

        final InvalidObjectException exception = assertThrows(InvalidObjectException.class,
                () -> CurrencyConverter.convertToTargetCurrencyCode(testRequest));

        assertEquals("No currency code of CCC was found", exception.getMessage());
    }
}