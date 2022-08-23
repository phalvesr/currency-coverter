package converter;

import cli.ConversionOption;

import java.math.BigDecimal;

public class ConversionResult {
    private final BigDecimal amountOfConvertedBrazilianReals;
    private final BigDecimal iofFee;
    private final BigDecimal totalOperationFee;
    private final BigDecimal amountAfterConversionInDestinationCoin;
    private final ConversionOption destinationCoin;

    public ConversionResult(
            BigDecimal amountOfConvertedBrazilianReals,
            BigDecimal iofFee,
            BigDecimal totalOperationFee,
            BigDecimal amountAfterConversionInDestinationCoin,
            ConversionOption destinationCoin) {
        this.amountOfConvertedBrazilianReals = amountOfConvertedBrazilianReals;
        this.iofFee = iofFee;
        this.totalOperationFee = totalOperationFee;
        this.amountAfterConversionInDestinationCoin = amountAfterConversionInDestinationCoin;
        this.destinationCoin = destinationCoin;
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

    @Override
    public String toString() {
        return String.format("Conversão: { valor em real: R$ %s, taxa IOF: R$ %s, total taxa operacional: R$ %s, " +
                "quantidade liquida na moeda destino: %s %s }", amountOfConvertedBrazilianReals, iofFee,
                totalOperationFee, ConversionOption.getMonetaryRepresentation(destinationCoin), amountAfterConversionInDestinationCoin);
    }

    public ConversionOption getDestinationCoin() {
        return destinationCoin;
    }
}
