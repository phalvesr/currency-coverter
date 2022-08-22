package dependencyinjection;

import cli.ConversionOption;
import cli.InputHandler;
import cli.OutputHandler;
import converter.CurrencyConverter;
import converter.currencyconverter.RealToArgentinianPeso;
import converter.currencyconverter.RealToChileanPeso;
import converter.currencyconverter.RealToDollar;
import converter.currencyconverter.RealToEuro;
import exception.ServiceNotFoundException;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServiceFactory {
    private final static HashMap<String, Object> serviceCollection;
    static {
        serviceCollection = new HashMap<>();
        registerServices();
    }

    private static void registerServices() {
        serviceCollection.put(PrintStream.class.getCanonicalName(), new PrintStream(System.out, true, StandardCharsets.UTF_8));
        serviceCollection.put(InputHandler.class.getCanonicalName(), new InputHandler());
        serviceCollection.put(OutputHandler.class.getCanonicalName(), new OutputHandler(new ServiceFactory()));
        serviceCollection.put("ConverterMap", getDictionaryLinkingConversionOptionsWithConverters());
    }

    private static Map<ConversionOption, CurrencyConverter> getDictionaryLinkingConversionOptionsWithConverters() {
        return (new HashMap<>(){{
            put(ConversionOption.EURO, new RealToEuro());
            put(ConversionOption.DOLAR, new RealToDollar());
            put(ConversionOption.PESO_ARGENTINO, new RealToArgentinianPeso());
            put(ConversionOption.PESO_CHILENO, new RealToChileanPeso());
        }});
    }

    /**
     * Returns a required service from (this simple and crude!!) DI container
     * @throws ServiceNotFoundException if no service with the name is registered at compile time
     * */
    public <T> T getRequiredService(Class<T> clazz) {
        Optional<Object> potentialService = ServiceFactory.serviceCollection.values().stream()
                .filter(o -> o.getClass().getCanonicalName().equals(clazz.getCanonicalName()))
                .findFirst();

        if (potentialService.isEmpty()) {
            throw new ServiceNotFoundException(String.format("No service found for the %s type", clazz.getCanonicalName()));
        }

        return (T) potentialService.get();
    }


    public <T> T getRequiredServiceByName(String name) {
        Object requestedService = serviceCollection.get(name);

        if (requestedService == null) {
            throw new ServiceNotFoundException(String.format("No service found for the %s name", name));
        }

        return (T)requestedService;
    }
}
