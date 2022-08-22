package cli;

import converter.ConversionResult;
import dependencyinjection.ServiceProvider;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OutputHandlerTest {

    @Test
    public void displayInitialInformationPrintsMessageRequestingForTheAmountOfBrazilianRealsThatShouldBeConverted() {

        PrintStream printStream = mock(PrintStream.class);
        ServiceProvider serviceProvider = mock(ServiceProvider.class);
        when(serviceProvider.getRequiredService(PrintStream.class)).thenReturn(printStream);

        OutputHandler sut = new OutputHandler(serviceProvider);
        sut.displayInitialInformation();


        verify(printStream, times(1)).format("Digite o valor em reais (R$): ");
    }

    @Test
    public void showConversionOptionsPrintsOneLineForEachPossibleConversionPresentOnConversionOptionEnum() {

        PrintStream printStream = mock(PrintStream.class);

        ServiceProvider serviceProvider = mock(ServiceProvider.class);
        when(serviceProvider.getRequiredService(PrintStream.class)).thenReturn(printStream);

        List<String> conversionOptions = Arrays.stream(ConversionOption.class.getDeclaredFields())
                .filter(f -> !(f.getName().equalsIgnoreCase("$VALUES")))
                .map(f -> ConversionOption.valueOf(f.getName()).stringValue())
                .toList();


        OutputHandler sut = new OutputHandler(serviceProvider);
        sut.showConversionOptions();


        assertEquals(4, conversionOptions.size());
        verify(printStream, times(1)).format("   %d. %s%n", 1, conversionOptions.get(0));
        verify(printStream, times(1)).format("   %d. %s%n", 2, conversionOptions.get(1));
        verify(printStream, times(1)).format("   %d. %s%n", 3, conversionOptions.get(2));
        verify(printStream, times(1)).format("   %d. %s%n", 4, conversionOptions.get(3));
    }

    @Test
    public void displayConversionResultsPrintsFormattedMessageToTheConsoleContainingTheValueConvertedTheIofAndTheOperationalFee() {

        final Locale outputLocale = Locale.forLanguageTag("pt-BR");

        PrintStream printStream = mock(PrintStream.class);
        ServiceProvider serviceProvider = mock(ServiceProvider.class);
        when(serviceProvider.getRequiredService(PrintStream.class)).thenReturn(printStream);


        final BigDecimal amountOfConvertedBrazilianReal = new BigDecimal ("100");
        final BigDecimal iofFee = new BigDecimal("1.10");
        final BigDecimal totalOperationFee = new BigDecimal("12.50");
        final BigDecimal amountAfterConversion = new BigDecimal("15048.28");

        ConversionResult conversionResult = mock(ConversionResult.class);
        when(conversionResult.getAmountOfConvertedBrazilianReals()).thenReturn(amountOfConvertedBrazilianReal);
        when(conversionResult.getIofFee()).thenReturn(iofFee);
        when(conversionResult.getTotalOperationFee()).thenReturn(totalOperationFee);
        when(conversionResult.getAmountAfterConversionOnDestinationCoin()).thenReturn(amountAfterConversion);


        var sut = new OutputHandler(serviceProvider);
        sut.displayConversionResults(conversionResult);

        verify(printStream, times(1)).format(outputLocale, "Valor em reais    -> R$ %s%n",
                amountOfConvertedBrazilianReal.setScale(2, RoundingMode.DOWN));
        verify(printStream, times(1)).format(
                outputLocale, "IOF               -> R$ %s%n", iofFee.setScale(2, RoundingMode.DOWN));
        verify(printStream, times(1)).format(
                outputLocale, "Taxa de Operacão  -> R$ %s%n", totalOperationFee.setScale(2, RoundingMode.DOWN));
        verify(printStream, times(1)).format("%s%n", "-".repeat(30));
        verify(printStream, times(1)).format(
                outputLocale, "Total convertido  -> %s%n", amountAfterConversion.setScale(2, RoundingMode.DOWN));
        verify(printStream, times(2)).println();
    }

    @Test
    public void displayArrowDisplaysArrowOnTheSameLineThatTheUserUsesToSelectConvesionOption() {

        PrintStream printStream = mock(PrintStream.class);
        ServiceProvider serviceProvider = mock(ServiceProvider.class);
        when(serviceProvider.getRequiredService(PrintStream.class)).thenReturn(printStream);


        OutputHandler sut = new OutputHandler(serviceProvider);
        sut.displayArrow();


        verify(printStream).format("-> ");
    }

    @Test
    public void displayMessageWithErrorStatusDisplaysToTheUserAMessageInRedMeaningAnInputError() {
        PrintStream printStream = mock(PrintStream.class);
        ServiceProvider serviceProvider = mock(ServiceProvider.class);
        when(serviceProvider.getRequiredService(PrintStream.class)).thenReturn(printStream);

        final String invalidOption = "things did not went well :/";


        OutputHandler sut = new OutputHandler(serviceProvider);
        sut.displayMessageWithErrorStatus(invalidOption);

        verify(printStream, times(1)).format("%s%s%s%n",
                ConsoleColors.RED, invalidOption, ConsoleColors.RESET);
    }
}
