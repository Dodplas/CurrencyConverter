package models;

import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

@Builder(toBuilder = true)
@Getter
public class Request {

    BigDecimal sourceAmount;
    CurrencyCode sourceCurrencyCode;

    BigDecimal baseAmount;

    CurrencyCode targetCurrencyCode;
    BigDecimal targetAmount;

    public static Request convertParamsToObject(String[] args) {
        Request request = null;

        try {
            request = Request.builder()
                              .sourceAmount(new BigDecimal(args[0]))
                              .sourceCurrencyCode(CurrencyCode.builder().code(args[1]).build())
                              .targetCurrencyCode(CurrencyCode.builder().code(args[2]).build())
                              .build();
        } catch (Exception ex) {
            throw new MalformedParametersException("Input values incorrect example 1.22 GBP GBP");
        }

        return request;
    }
}
