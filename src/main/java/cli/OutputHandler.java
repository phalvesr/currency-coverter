package cli;

import converter.ConversionResult;
import dependencyinjection.ServiceProvider;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OutputHandler {

    private static List<String> conversionOptions;

    static {
        conversionOptions = Arrays.stream(ConversionOption.class.getDeclaredFields())
                .filter(f -> !(f.getName().equalsIgnoreCase("$VALUES")))
                .map(field -> ConversionOption.valueOf(field.getName()).stringValue())
                .toList();
    }

    private final PrintStream printStream;

    public OutputHandler(ServiceProvider serviceProvider) {
        this.printStream = serviceProvider.getRequiredService(PrintStream.class);
    }

    public void displayMessageRequestingValueToConvert() {
        printStream.format("Digite o valor em reais (R$): ");
    }

    public void showConversionOptions() {
        Arrays.stream(ConversionOption.values())
                .forEach(o -> printStream.format("   %d. %s%n", o.intValue(), o.stringValue()));
    }

    // TODO: Add the currency symbol to the output function when displaying
    //  amount after conversion
    public void displayConversionResults(ConversionResult conversionResult) {
        Locale locale = Locale.forLanguageTag("pt-BR");

        printStream.println();
        printStream.format(locale, "Valor em reais    -> R$ %s%n",
                getFormatBigDecimalToDisplay(conversionResult.getAmountOfConvertedBrazilianReals()));
        printStream.format(locale, "IOF               -> R$ %s%n",
                getFormatBigDecimalToDisplay(conversionResult.getIofFee()));
        printStream.format(locale, "Taxa de Operacão  -> R$ %s%n",
                getFormatBigDecimalToDisplay(conversionResult.getTotalOperationFee()));
        printStream.format("%s%n", "-".repeat(30));
        printStream.format(locale, "Total convertido  -> %s %s%n", ConversionOption.getMonetaryRepresentation(conversionResult.getDestinationCoin()),
                getFormatBigDecimalToDisplay(conversionResult.getAmountAfterConversionOnDestinationCoin()));
        printStream.println();
    }

    public void displayArrow() {
        printStream.format("-> ");
    }

    public void displayMessageWithErrorStatus(String message) {
        printStream.format("%s%s%s%n", ConsoleColors.RED, message, ConsoleColors.RESET);
    }

    public void displayGreeting() {
        printStream.format("%s%n%n", "Olá! bem-vindo ao conversor de moedas");
    }

    private BigDecimal getFormatBigDecimalToDisplay(BigDecimal value) {
        return value.setScale(2, RoundingMode.DOWN);
    }

    public void requestConversionOptionsToUser() {
        printStream.format("%s%n", "Por favor, selecione uma das opções abaixo:");
    }
}
