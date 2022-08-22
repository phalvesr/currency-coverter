package converter;

import java.math.BigDecimal;
import java.util.Optional;

public interface CurrencyConverterHandler {
    Optional<ConversionResult> convertCurrency(BigDecimal amountOfBrazilianReal);
}
