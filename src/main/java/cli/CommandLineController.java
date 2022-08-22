package cli;

import converter.ConversionRequest;
import converter.ConversionWithOperationFeeAndIofHandler;
import converter.CurrencyConverter;
import dependencyinjection.ServiceProvider;

import java.util.Map;

public class CommandLineController {

    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private Map<ConversionOption, CurrencyConverter> converters;

    public CommandLineController(ServiceProvider serviceProvider) {
        this.outputHandler = serviceProvider.getRequiredServiceByName(OutputHandler.class.getCanonicalName());
        this.inputHandler = serviceProvider.getRequiredService(InputHandler.class);
        this.converters = serviceProvider.getRequiredServiceByName("ConverterMap");
    }

    public void run() {

        outputHandler.displayInitialInformation();
        inputHandler.readAmountOfBrazilianRealToBeConverted().match(amountOfBrazilianRealToBeConverted -> {
        outputHandler.showConversionOptions();
        outputHandler.displayArrow();
        inputHandler.readConversionOption().match(conversionOption -> {
            CurrencyConverter currencyConverter = converters.get(conversionOption);
            ConversionWithOperationFeeAndIofHandler converter = new ConversionWithOperationFeeAndIofHandler(currencyConverter);
            var a = new ConversionRequest(amountOfBrazilianRealToBeConverted, conversionOption);
            converter.convertCurrency(amountOfBrazilianRealToBeConverted)
            .ifPresentOrElse(outputHandler::displayConversionResults,
            () -> outputHandler.displayMessageWithErrorStatus("Quantidade convertida gera um valor negativo. Não fazemos isso aqui!"));
        }, error -> outputHandler.displayMessageWithErrorStatus("Opção de conversão selecionada é inválida. Por favor, selecione uma das opções válidas!"));
        }, error -> outputHandler.displayMessageWithErrorStatus("Valor digitado para conversão é invalido!"));
    }
}
