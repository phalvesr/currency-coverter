package converter.currencyconverter;

import java.math.BigDecimal;

public class RealToArgentinianPeso extends CurrencyConverterBase {
    @Override
    public BigDecimal getBrazilianRealToDestinationCurrencyPriceRatio() {
        return new BigDecimal("26.24");
    }

    @Override
    protected BigDecimal getMinimumOperationalFee() {
        return new BigDecimal("2.50");
    }

    @Override
    protected BigDecimal getOperationalTaxPercentage() {
        return new BigDecimal("0.015");
    }
}
