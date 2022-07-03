package converters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import models.CurrencyCode;
import models.Request;
import resourceloaders.CsvLoader;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * Class contains the logic to perform the currency conversions
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyConverter {

    /**
     * Performs the currency conversion and loading in of the exchange rates file
     * @param request the user request for currency conversion
     * @return request with the post-conversion information
     * @throws IOException can be thrown when loading in the csv file
     */
    public static Request convertToTargetCurrencyCode(Request request) throws IOException {
        final List<List<String>> currencyCodes = CsvLoader.loadCsv("src/main/resources/exchange-rates-to-gbp.csv");
        final List<CurrencyCode> currencyCodesList = CurrencyCode.toCurrencyCodeList(currencyCodes);

        return getRequestCurrencyInformation(currencyCodesList, request);
    }

    private static Request getRequestCurrencyInformation(List<CurrencyCode> currencyCodesList, Request request) throws InvalidObjectException {
        return Request.builder()
                          .sourceCurrencyCode(
                                  getCurrencyCodeDetailsForCurrencyCode(currencyCodesList, request.getSourceCurrencyCode().getCode()))
                          .targetCurrencyCode(
                                  getCurrencyCodeDetailsForCurrencyCode(currencyCodesList, request.getTargetCurrencyCode().getCode()))
                          .baseAmount(
                                  getBaseCurrencyAmount(currencyCodesList, request)
                          )
                          .targetAmount(
                                  getTargetCurrencyAmount(currencyCodesList, request)
                          )
                          .build();
    }

    private static BigDecimal getBaseCurrencyAmount(List<CurrencyCode> currencyCodes, final Request request) throws InvalidObjectException {
        return request.getSourceAmount()
                       .divide(
                               getCurrencyCodeDetailsForCurrencyCode(currencyCodes, request.getSourceCurrencyCode().getCode())
                                       .getRate(), 3, RoundingMode.UP
                       );
    }

    private static BigDecimal getTargetCurrencyAmount(List<CurrencyCode> currencyCodes, final Request request) throws InvalidObjectException {
        return request.getSourceAmount()
                       .multiply(
                               getCurrencyCodeDetailsForCurrencyCode(currencyCodes, request.getTargetCurrencyCode().getCode())
                                       .getRate(), new MathContext(3, RoundingMode.UP));
    }

    private static CurrencyCode getCurrencyCodeDetailsForCurrencyCode(List<CurrencyCode> currencyCodes, String currencyCode) throws InvalidObjectException {
        Optional<CurrencyCode> currencyCodeDetails = currencyCodes.stream()
                       .filter(code -> code.getCode().equals(currencyCode))
                       .findFirst();

        if(currencyCodeDetails.isEmpty()) {
            throw new InvalidObjectException(String.format("No currency code of %s was found", currencyCode));
        }

        return currencyCodeDetails.get();
    }
}
