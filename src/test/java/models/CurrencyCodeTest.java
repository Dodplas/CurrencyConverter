package models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyCodeTest {

    @Test
    void shouldConvertStringListToCurrencyCodeList() {
        List<String> csvLineOne = List.of("test country 1", "test name 1", "AAA", "1.1111");
        List<String> csvLineTwo = List.of("test country 2", "test name 2", "BBB", "3.45252354");
        List<List<String>> csvLines = List.of(csvLineOne, csvLineTwo);

        final List<CurrencyCode> currencyCodeList = CurrencyCode.toCurrencyCodeList(csvLines);

        assertEquals("test country 1", currencyCodeList.get(0).getCountry());
        assertEquals("test name 1", currencyCodeList.get(0).getName());
        assertEquals("AAA", currencyCodeList.get(0).getCode());
        assertEquals(new BigDecimal("1.1111"), currencyCodeList.get(0).getRate());

        assertEquals("test country 2", currencyCodeList.get(1).getCountry());
        assertEquals("test name 2", currencyCodeList.get(1).getName());
        assertEquals("BBB", currencyCodeList.get(1).getCode());
        assertEquals(new BigDecimal("3.45252354"), currencyCodeList.get(1).getRate());
    }
}