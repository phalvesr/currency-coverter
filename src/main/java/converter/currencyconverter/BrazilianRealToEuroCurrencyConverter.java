package converter.currencyconverter;

import java.math.BigDecimal;

public class BrazilianRealToEuroCurrencyConverter extends CurrencyConverterBase {
    @Override
    public BigDecimal getBrazilianRealToDestinationCurrencyPriceRatio() {
        return new BigDecimal("0.20");
    }

    @Override
    protected BigDecimal getMinimumOperationalFee() {
        return new BigDecimal("6.00");
    }


    @Override
    protected BigDecimal getOperationalTaxPercentage() {
        return BigDecimal.ZERO;
    }
}
