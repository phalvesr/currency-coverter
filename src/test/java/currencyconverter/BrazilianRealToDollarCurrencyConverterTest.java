package currencyconverter;

import cli.ConversionOption;
import converter.ConversionRequest;
import converter.ConversionWithOperationFeeAndIofHandler;
import converter.CurrencyConverterHandler;
import converter.currencyconverter.BrazilianRealToDollarCurrencyConverter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class BrazilianRealToDollarCurrencyConverterTest {

    @Test
    public void convertCurrencyReturnsExpectedValueForBrazilianRealToDollarConversion() {
        BrazilianRealToDollarCurrencyConverter brazilianRealToDollarCurrencyConverter = new BrazilianRealToDollarCurrencyConverter();
        CurrencyConverterHandler currencyConverterHandler = new ConversionWithOperationFeeAndIofHandler(brazilianRealToDollarCurrencyConverter);

        ConversionRequest conversionRequest = new ConversionRequest(new BigDecimal("100.00"), ConversionOption.DOLAR);

        BigDecimal convertedCurrency = currencyConverterHandler.convertCurrency(conversionRequest)
                .get().getAmountAfterConversionOnDestinationCoin();
        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);

        assertEquals(new BigDecimal("17.27"), roundedConvertedCurrency);
    }

    @Test
    public void convertCurrencyReturnsDollarAsConversionOptionAfterConversionIsDone() {
        BrazilianRealToDollarCurrencyConverter brazilianRealToDollarCurrencyConverter = new BrazilianRealToDollarCurrencyConverter();
        CurrencyConverterHandler currencyConverterHandler = new ConversionWithOperationFeeAndIofHandler(brazilianRealToDollarCurrencyConverter);

        ConversionRequest conversionRequest = new ConversionRequest(new BigDecimal("100.00"), ConversionOption.DOLAR);

        ConversionOption conversionOption = currencyConverterHandler.convertCurrency(conversionRequest)
                .get().getDestinationCoin();

        assertEquals(ConversionOption.DOLAR, conversionOption);
    }
}
