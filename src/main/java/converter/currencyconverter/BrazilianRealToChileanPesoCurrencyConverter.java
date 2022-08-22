package converter.currencyconverter;

import java.math.BigDecimal;

public class BrazilianRealToChileanPesoCurrencyConverter extends CurrencyConverterBase {
    @Override
    public BigDecimal getBrazilianRealToDestinationCurrencyPriceRatio() {
        return new BigDecimal("174.17");
    }

    @Override
    protected BigDecimal getMinimumOperationalFee() {
        return new BigDecimal("10.00");
    }

    @Override
    protected BigDecimal getOperationalTaxPercentage() {
        return new BigDecimal("0.025");
    }
}
