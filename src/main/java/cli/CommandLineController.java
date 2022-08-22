package cli;

import converter.ConversionWithOperationFeeAndIofHandler;
import converter.CurrencyConverter;
import dependencyinjection.ServiceFactory;

import java.util.Map;

public class CommandLineController {

    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private Map<ConversionOption, CurrencyConverter> converters;

    public CommandLineController(ServiceFactory serviceFactory) {
        this.outputHandler = serviceFactory.getRequiredServiceByName(OutputHandler.class.getCanonicalName());
        this.inputHandler = serviceFactory.getRequiredService(InputHandler.class);
        this.converters = serviceFactory.getRequiredServiceByName("ConverterMap");
    }

    public boolean run() {

        outputHandler.displayInitialInformation();
        inputHandler.readAmountOfBrazilianRealToBeConverted().match(amountOfBrazilianRealToBeConverted -> {
        outputHandler.showConversionOptions();
        outputHandler.displayArrow();

        return inputHandler.readConversionOption().match(conversionOption -> {

            CurrencyConverter currencyConverter = converters.get(conversionOption);
            ConversionWithOperationFeeAndIofHandler converter = new ConversionWithOperationFeeAndIofHandler(currencyConverter);
            converter.convertCurrency(amountOfBrazilianRealToBeConverted)
            .ifPresentOrElse(outputHandler::displayConversionResults,
            () -> {
                outputHandler.displayMessageWithErrorStatus("Quantidade convertida gera um valor negativo. Não fazemos isso aqui!");
                return false;
            });
        }, error -> {
            outputHandler.displayMessageWithErrorStatus("Opção de conversão selecionada é inválida. Por favor, selecione uma das opções válidas!");
            return false;
        });
        }, error -> {
            outputHandler.displayMessageWithErrorStatus("Valor digitado para conversão é invalido!");
            return false;
        });
    }
}
