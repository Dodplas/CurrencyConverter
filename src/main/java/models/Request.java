package models;

import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.MalformedParametersException;
import java.math.BigDecimal;

/**
 * Holds the information about the request in progress
 */
@Builder(toBuilder = true)
@Getter
public class Request {

    private final BigDecimal sourceAmount;
    private final CurrencyCode sourceCurrencyCode;

    // SourceAmount converted to GBP
    private final BigDecimal baseAmount;

    private final CurrencyCode targetCurrencyCode;
    private final BigDecimal targetAmount;

    /**
     * Converts initial user request parameters into a more easily usable object
     * @param args parameters passed in by the user
     * @return Returns the parameters values in the form of a Request object
     */
    public static Request convertParamsToObject(String[] args) {
        Request request;

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
