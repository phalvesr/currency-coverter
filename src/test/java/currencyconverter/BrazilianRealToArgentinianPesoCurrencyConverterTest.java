package currencyconverter;

import converter.ConversionWithOperationFeeAndIofHandler;
import converter.currencyconverter.BrazilianRealToArgentinianPesoCurrencyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrazilianRealToArgentinianPesoCurrencyConverterTest {
    @Test
    public void convertCurrencyReturnsExpectedValueForBrazilianRealToArgentinianPesoConversion() {
        BrazilianRealToArgentinianPesoCurrencyConverter brazilianRealToArgentinianPesoCurrencyConverter =
                new BrazilianRealToArgentinianPesoCurrencyConverter();
        ConversionWithOperationFeeAndIofHandler currencyConverterHandler =
                new ConversionWithOperationFeeAndIofHandler(brazilianRealToArgentinianPesoCurrencyConverter);

        BigDecimal convertedCurrency = currencyConverterHandler.convertCurrency(new BigDecimal("100.00"))
                .get().getAmountAfterConversionOnDestinationCoin();
        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);

        assertEquals(new BigDecimal("2490.17"), roundedConvertedCurrency);
    }
}
