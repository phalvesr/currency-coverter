package converter;

import java.math.BigDecimal;

public class ConversionResult {
    private final BigDecimal amountOfConvertedBrazilianReals;
    private final BigDecimal iofFee;
    private final BigDecimal totalOperationFee;
    private final BigDecimal amountAfterConversionInDestinationCoin;

    public ConversionResult(
            BigDecimal amountOfConvertedBrazilianReals,
            BigDecimal iofFee,
            BigDecimal totalOperationFee,
            BigDecimal amountAfterConversionInDestinationCoin) {
        this.amountOfConvertedBrazilianReals = amountOfConvertedBrazilianReals;
        this.iofFee = iofFee;
        this.totalOperationFee = totalOperationFee;
        this.amountAfterConversionInDestinationCoin = amountAfterConversionInDestinationCoin;
    }

    public BigDecimal getAmountOfConvertedBrazilianReals() {
        return amountOfConvertedBrazilianReals;
    }

    public BigDecimal getIofFee() {
        return iofFee;
    }

    public BigDecimal getTotalOperationFee() {
        return totalOperationFee;
    }

    public BigDecimal getAmountAfterConversionOnDestinationCoin() {
        return amountAfterConversionInDestinationCoin;
    }
}
