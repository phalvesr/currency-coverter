package memory;

import converter.ConversionResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversionMemory {

    private static ConversionMemory singleInstance = null;
    private List<ConversionResult> conversionResults;

    private ConversionMemory() {
        conversionResults = new ArrayList<>();
    }

    public static ConversionMemory getInstance() {
        if (singleInstance == null) {
            singleInstance = new ConversionMemory();
            return singleInstance;
        }

        return singleInstance;
    }

    /**
     * Returns an unmodifiableList of conversion results. Beware to use the 'add' method of the class
     * when adding an entrance to the list, avoiding exceptions at runtime
     * */
    public List<ConversionResult> getConversions() {
        return Collections.unmodifiableList(conversionResults);
    }

    public void add(ConversionResult conversion) {
        this.conversionResults.add(conversion);
    }
}
