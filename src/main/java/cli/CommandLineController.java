package cli;

import converter.ConversionResult;
import converter.ConversionWithOperationFeeAndIofHandler;
import converter.CurrencyConverter;
import dependencyinjection.ServiceProvider;
import type.Either;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CommandLineController {

    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private final Map<ConversionOption, CurrencyConverter> converters;

    public CommandLineController(ServiceProvider serviceProvider) {
        this.outputHandler = serviceProvider.getRequiredService(OutputHandler.class.getCanonicalName());
        this.inputHandler = serviceProvider.getRequiredService(InputHandler.class);
        this.converters = serviceProvider.getRequiredService("ConverterMap");
    }

    public boolean run() {

        outputHandler.displayInitialInformation();

        Either<BigDecimal, Exception> amountOfRealOrException = inputHandler.readAmountOfBrazilianRealToBeConverted(); //.match(amountOfBrazilianRealToBeConverted -> {
        if (amountOfRealOrException.isLeft()) {
            outputHandler.displayMessageWithErrorStatus("Valor digitado para conversão é invalido!");
            return false;
        }

        BigDecimal amountOfReal = amountOfRealOrException.unsafeGetRight();

        outputHandler.showConversionOptions();
        outputHandler.displayArrow();

        Either<ConversionOption, Exception> conversionOptionOrException = inputHandler.readConversionOption();
        if (conversionOptionOrException.isLeft()) {
            outputHandler.displayMessageWithErrorStatus("Opção de conversão selecionada é inválida. Por favor, selecione uma das opções válidas!");
            return true;
        }

        ConversionOption conversionOption = conversionOptionOrException.unsafeGetRight();
        if (conversionOption == ConversionOption.NONE) {
            return false;
        }

        CurrencyConverter currencyConverter = converters.get(conversionOption);
        ConversionWithOperationFeeAndIofHandler conversionWithOperationFeeAndIofHandler = new ConversionWithOperationFeeAndIofHandler(currencyConverter);

        Optional<ConversionResult> conversionResultOptional = conversionWithOperationFeeAndIofHandler.convertCurrency(amountOfReal);

        if (conversionResultOptional.isEmpty()) {
            outputHandler.displayMessageWithErrorStatus("Quantidade convertida gera um valor negativo. Não fazemos isso aqui!");
            return true;
        }

        outputHandler.displayConversionResults(conversionResultOptional.get());
        return true;
    }
}
