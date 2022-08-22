package converter.currencyconverter;

import converter.CurrencyConverterHandler;

import java.math.BigDecimal;

public class BrazilianRealToDollarCurrencyConverter extends CurrencyConverterBase {
    @Override
    public BigDecimal getBrazilianRealToDestinationCurrencyPriceRatio() {
        return new BigDecimal("0.19");
    }

    @Override
    protected BigDecimal getMinimumOperationalFee() {
        return new BigDecimal("5");
    }

    @Override
    protected BigDecimal getOperationalTaxPercentage() {
        return new BigDecimal("0.03");
    }
}
