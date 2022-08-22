package converter.currencyconverter;

import converter.CurrencyConverter;

import java.math.BigDecimal;

public abstract class CurrencyConverterBase implements CurrencyConverter {
    @Override
    public BigDecimal getOperationalFee(BigDecimal amountOfBrazilianReal) {

        return getMinimumOperationalFee().add(getOperationalTaxPercentage().multiply(amountOfBrazilianReal));
    }

    @Override
    public BigDecimal getIofPercentage() {
        return new BigDecimal("0.011");
    }

    @Override
    public abstract BigDecimal getBrazilianRealToDestinationCurrencyPriceRatio();
    protected abstract BigDecimal getMinimumOperationalFee();
    protected abstract BigDecimal getOperationalTaxPercentage();
}
