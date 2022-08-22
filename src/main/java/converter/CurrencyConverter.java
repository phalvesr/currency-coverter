package converter;

import java.math.BigDecimal;

public interface CurrencyConverter {
    BigDecimal getOperationalFee(BigDecimal amountOfBrazilianReal);
    BigDecimal getIofPercentage();
    BigDecimal getBrazilianRealToDestinationCurrencyPriceRatio();
}
