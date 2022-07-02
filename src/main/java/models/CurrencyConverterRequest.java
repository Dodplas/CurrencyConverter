package models;

import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

@Builder
@Getter
public class CurrencyConverterRequest {

    BigDecimal sourceAmount;
    String sourceCurrencyCode;
    String targetCurrencyCode;

    public static CurrencyConverterRequest convertParamsToObject(String[] args) {
        CurrencyConverterRequest request = null;

        try {
            request = CurrencyConverterRequest.builder()
                              .sourceAmount(new BigDecimal(args[0]))
                              .sourceCurrencyCode(args[1])
                              .targetCurrencyCode(args[2])
                              .build();
        } catch (Exception ex) {
            throw new MalformedParametersException("Input values incorrect example 1.22 GBP GBP");
        }

        return request;
    }
}
