package currencyconverter;

import cli.ConversionOption;
import converter.ConversionRequest;
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
        ConversionRequest conversionRequest = new ConversionRequest(new BigDecimal("100.00"), ConversionOption.PESO_ARGENTINO);


        BigDecimal convertedCurrency = currencyConverterHandler.convertCurrency(conversionRequest)
                .get().getAmountAfterConversionOnDestinationCoin();
        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);

        assertEquals(new BigDecimal("2490.17"), roundedConvertedCurrency);
    }
}
