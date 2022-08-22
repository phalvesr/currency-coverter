package converter;

import cli.ConversionOption;

import java.math.BigDecimal;

public class ConversionRequest {
    private final BigDecimal amountOfBrazilianRealToConvert;
    private final ConversionOption destinationCurrency;

    public ConversionRequest(BigDecimal amountOfBrazilianRealToConvert, ConversionOption destinationCurrency) {
        this.amountOfBrazilianRealToConvert = amountOfBrazilianRealToConvert;
        this.destinationCurrency = destinationCurrency;
    }
}
