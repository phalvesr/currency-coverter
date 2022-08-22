package currencyconverter;

import converter.ConversionWithOperationFeeAndIofHandler;
import converter.currencyconverter.BrazilianRealToChileanPesoCurrencyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrazilianRealToChileanPesoCurrencyConverterTest {

    @Test
    public void convertCurrencyReturnsExpectedValueForBrazilianRealToChileanPesoConversion() {
        BrazilianRealToChileanPesoCurrencyConverter brazilianRealToChileanPesoCurrencyConverter =
                new BrazilianRealToChileanPesoCurrencyConverter();
        ConversionWithOperationFeeAndIofHandler currencyConverterHandler =
                new ConversionWithOperationFeeAndIofHandler(brazilianRealToChileanPesoCurrencyConverter);

        BigDecimal convertedCurrency = currencyConverterHandler.convertCurrency(new BigDecimal("100.00"))
                .get().getAmountAfterConversionOnDestinationCoin();
        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);

        assertEquals(new BigDecimal("15048.28"), roundedConvertedCurrency);
    }
}
