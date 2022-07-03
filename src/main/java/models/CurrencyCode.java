package models;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Model to hold the currency code information
 */
@Builder(toBuilder = true)
@Getter
public class CurrencyCode {
    private final String country;
    private final String name;
    private final String code;
    private final BigDecimal rate;

    /**
     * converts the currency codes from it's initial format into a more useable object
     * @param currencyCodes the currency codes as strings
     * @return a list of CurrencyCode objects
     */
    public static List<CurrencyCode> toCurrencyCodeList(List<List<String>> currencyCodes) {
        return currencyCodes.stream().map(currency -> new CurrencyCodeBuilder()
                                                              .country(currency.get(0))
                                                              .name(currency.get(1))
                                                              .code(currency.get(2))
                                                              .rate(new BigDecimal(currency.get(3)))
                                                              .build()).collect(Collectors.toList());
    }
}
