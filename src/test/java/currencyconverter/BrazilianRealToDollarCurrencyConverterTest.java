package currencyconverter;

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

        BigDecimal convertedCurrency = currencyConverterHandler.convertCurrency(new BigDecimal("100.00"))
                .get().getAmountAfterConversionOnDestinationCoin();
        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);

        assertEquals(new BigDecimal("17.27"), roundedConvertedCurrency);
    }
}
