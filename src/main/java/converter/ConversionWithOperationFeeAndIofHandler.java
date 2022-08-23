package converter;

import java.math.BigDecimal;
import java.util.Optional;

public class ConversionWithOperationFeeAndIofHandler implements CurrencyConverterHandler {

    private final CurrencyConverter currencyConverter;

    public ConversionWithOperationFeeAndIofHandler(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    @Override
    public final Optional<ConversionResult> convertCurrency(ConversionRequest conversionRequest) {

        BigDecimal amountOfBrazilianReal = conversionRequest.getAmountOfBrazilianRealToConvert();

        BigDecimal operationalFee = currencyConverter.getOperationalFee(amountOfBrazilianReal);
        BigDecimal iofFee = getIofFee(amountOfBrazilianReal);

        BigDecimal discounts = operationalFee.add(iofFee);

        BigDecimal amountOfBrazilianRealWithDiscounts = amountOfBrazilianReal.subtract(discounts);
        BigDecimal amountAfterConversionOnDestinationCoin = amountOfBrazilianRealWithDiscounts.multiply(currencyConverter.getBrazilianRealToDestinationCurrencyPriceRatio());

        if (amountAfterConversionOnDestinationCoin.compareTo(BigDecimal.ZERO) < 0) {
            return Optional.empty();
        }

        return Optional.of(
                new ConversionResult(amountOfBrazilianReal, iofFee, operationalFee,
                        amountAfterConversionOnDestinationCoin, conversionRequest.getDestinationCurrency())
        );
    }


    private BigDecimal getOperationalFee(BigDecimal amountOfBrazilianReal) {
        return amountOfBrazilianReal.subtract(getMinimumDiscountedPerOperationInBrazilianReal());
    }

    private BigDecimal getIofFee(BigDecimal amountOfBrazilianReal) {
        return currencyConverter.getIofPercentage().multiply(amountOfBrazilianReal);
    }

    private BigDecimal getMinimumDiscountedPerOperationInBrazilianReal() {
        return BigDecimal.ZERO;
    }
}
