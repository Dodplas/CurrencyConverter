package resourceloaders;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvLoaderTest {

    @Test
    void shouldLoadCurrencyCodes() throws IOException {
        final List<List<String>> currencyCodes = CsvLoader.loadCsv("src/test/resources/exchange-rates-to-gbp.csv");

        assertEquals("test country 1", currencyCodes.get(0).get(0));
        assertEquals("test name 1", currencyCodes.get(0).get(1));
        assertEquals("AAA", currencyCodes.get(0).get(2));
        assertEquals("1.1111", currencyCodes.get(0).get(3));

        assertEquals("test country 2", currencyCodes.get(1).get(0));
        assertEquals("test name 2", currencyCodes.get(1).get(1));
        assertEquals("BBB", currencyCodes.get(1).get(2));
        assertEquals("3.45252354", currencyCodes.get(1).get(3));
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        final FileNotFoundException exception = assertThrows(FileNotFoundException.class,
                () -> CsvLoader.loadCsv("src/test/resources/does-not-exist.csv"));

        assertEquals("File: src/test/resources/does-not-exist.csv not found", exception.getMessage());
    }
}