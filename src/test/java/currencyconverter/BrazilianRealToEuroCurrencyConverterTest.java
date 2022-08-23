package currencyconverter;

import cli.ConversionOption;
import converter.ConversionRequest;
import converter.ConversionWithOperationFeeAndIofHandler;
import converter.CurrencyConverterHandler;
import converter.currencyconverter.BrazilianRealToDollarCurrencyConverter;
import converter.currencyconverter.BrazilianRealToEuroCurrencyConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrazilianRealToEuroCurrencyConverterTest {

    @ParameterizedTest
    @MethodSource
    public void convertCurrencyReturnsExpectedValueForBrazilianRealToEuroConversion(
            String amountInputtedToConvertInRealString,
            String expectedOutputString) {
        BrazilianRealToEuroCurrencyConverter currencyConverter = new BrazilianRealToEuroCurrencyConverter();
        CurrencyConverterHandler ConversionWithOperationFeeAndIofHandler = new ConversionWithOperationFeeAndIofHandler(currencyConverter);



        ConversionRequest sut = new ConversionRequest(new BigDecimal(amountInputtedToConvertInRealString), ConversionOption.EURO);
        BigDecimal convertedCurrency = ConversionWithOperationFeeAndIofHandler.convertCurrency(sut)
                .get().getAmountAfterConversionOnDestinationCoin();

        BigDecimal roundedConvertedCurrency = convertedCurrency.setScale(2, RoundingMode.DOWN);


        assertEquals(new BigDecimal(expectedOutputString), roundedConvertedCurrency);
    }

    private static Stream<Arguments> convertCurrencyReturnsExpectedValueForBrazilianRealToEuroConversion() {
        return Stream.of(
                Arguments.of("100.0", "18.58"),
                Arguments.of("200.00", "38.36"),
                Arguments.of("350.00", "68.03")
        );
    }

    @Test
    public void convertCurrencyReturnsEuroAsConversionOptionAfterConversionIsDone() {
        BrazilianRealToDollarCurrencyConverter brazilianRealToDollarCurrencyConverter = new BrazilianRealToDollarCurrencyConverter();
        CurrencyConverterHandler currencyConverterHandler = new ConversionWithOperationFeeAndIofHandler(brazilianRealToDollarCurrencyConverter);

        ConversionRequest conversionRequest = new ConversionRequest(new BigDecimal("100.00"), ConversionOption.EURO);


        ConversionOption conversionOption = currencyConverterHandler.convertCurrency(conversionRequest)
                .get().getDestinationCoin();


        assertEquals(ConversionOption.EURO, conversionOption);
    }
}
