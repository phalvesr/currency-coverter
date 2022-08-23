package converter;

import java.util.Optional;

public interface CurrencyConverterHandler {
    Optional<ConversionResult> convertCurrency(ConversionRequest conversionRequest);
}
