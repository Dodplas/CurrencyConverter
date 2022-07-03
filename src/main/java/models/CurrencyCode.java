package models;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Getter
public class CurrencyCode {
    String country;
    String name;
    String code;
    BigDecimal rate;

    public static List<CurrencyCode> toCurrencyCodeList(List<List<String>> currencyCodes) {
        return currencyCodes.stream().map(currency -> new CurrencyCodeBuilder()
                                                              .country(currency.get(0))
                                                              .name(currency.get(1))
                                                              .code(currency.get(2))
                                                              .rate(new BigDecimal(currency.get(3)))
                                                              .build()).collect(Collectors.toList());
    }
}
