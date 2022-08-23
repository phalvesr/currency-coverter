package currencyconverter;

import cli.ConversionOption;
import converter.ConversionRequest;
import converter.ConversionWithOperationFeeAndIofHandler;
import converter.currencyconverter.BrazilianRealToChileanPesoCurrencyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrazilianRealToChileanPesoCurrencyConverterTest {

    @Test
    public void convertCurrencyReturnsExpectedValueForBrazilianRealToChileanPesoConversion() {
        ConversionRequest conversionRequest = new ConversionRequest(new BigDecimal("100.00"), ConversionOption.PESO_CHILENO);


        BrazilianRealToChileanPesoCurrencyConverter sut = new BrazilianRealToChileanPesoCurrencyConverter();
        ConversionWithOperationFeeAndIofHandler currencyConverterHandler = new ConversionWithOperationFeeAndIofHandler(sut);

        BigDecimal convertedCurrency = currencyConverterHandler.convertCurrency(conversionRequest)
                .get().getAmountAfterConversionOnDestinationCoin();
        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);


        assertEquals(new BigDecimal("15048.28"), roundedConvertedCurrency);
    }
}
