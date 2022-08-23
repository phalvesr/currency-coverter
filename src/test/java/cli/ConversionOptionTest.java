package cli;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversionOptionTest {

    private final List<Field> conventionOptionsUserDeclaredFields;
    private final int FIELDS_DECLARED_ON_CONVENTION_OPTION_ENUM = 5;

    public ConversionOptionTest() {
        this.conventionOptionsUserDeclaredFields = Arrays.stream(ConversionOption.class.getDeclaredFields())
                .filter(f -> !(f.getName().equalsIgnoreCase("$VALUES")))
                .toList();
    }

    @Test
    public void assureAllEnumFieldsAreMapped() {

        assertEquals(FIELDS_DECLARED_ON_CONVENTION_OPTION_ENUM, conventionOptionsUserDeclaredFields.size());
        assertAll("Fields",
            () -> assertEquals("NONE", conventionOptionsUserDeclaredFields.get(0).getName()),
            () -> assertEquals("EURO", conventionOptionsUserDeclaredFields.get(1).getName()),
            () -> assertEquals("DOLAR", conventionOptionsUserDeclaredFields.get(2).getName()),
            () -> assertEquals("PESO_ARGENTINO", conventionOptionsUserDeclaredFields.get(3).getName()),
            () -> assertEquals("PESO_CHILENO", conventionOptionsUserDeclaredFields.get(4).getName())
        );
    }

    @Test
    public void stringValueAssureStringRepresentationForFieldToBeAsExpected() {

        assertEquals(FIELDS_DECLARED_ON_CONVENTION_OPTION_ENUM, conventionOptionsUserDeclaredFields.size());
        assertAll(
            () -> assertEquals("Sair", ConversionOption.valueOf(conventionOptionsUserDeclaredFields.get(0).getName()).stringValue()),
            () -> assertEquals("Euro", ConversionOption.valueOf(conventionOptionsUserDeclaredFields.get(1).getName()).stringValue()),
            () -> assertEquals("Dólar", ConversionOption.valueOf(conventionOptionsUserDeclaredFields.get(2).getName()).stringValue()),
            () -> assertEquals("Peso Argentino", ConversionOption.valueOf(conventionOptionsUserDeclaredFields.get(3).getName()).stringValue()),
            () -> assertEquals("Peso Chileno", ConversionOption.valueOf(conventionOptionsUserDeclaredFields.get(4).getName()).stringValue())
        );
    }
}
