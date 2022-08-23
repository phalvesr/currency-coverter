package cli;

public enum ConversionOption {
    NONE {
        @Override
        public String stringValue() {
            return "Sair";
        }

        @Override
        public int intValue() {
            return 0;
        }
    },
    EURO {
        @Override
        public String stringValue() {
            return "Euro";
        }

        @Override
        public int intValue() {
            return 1;
        }
    },
    DOLAR {
        @Override
        public String stringValue() {
            return "Dólar";
        }

        @Override
        public int intValue() {
            return 2;
        }
    },
    PESO_ARGENTINO  {
        @Override
        public String stringValue() {
            return "Peso Argentino";
        }

        @Override
        public int intValue() {
            return 3;
        }
    },
    PESO_CHILENO  {
        @Override
        public String stringValue() {
            return "Peso Chileno";
        }

        @Override
        public int intValue() {
            return 4;
        }
    };

    public abstract String stringValue();
    public abstract int intValue();
    public static String getMonetaryRepresentation(ConversionOption option) {
        return switch (option) {
            case NONE -> throw new IllegalArgumentException(option.toString());
            case EURO -> "EUR";
            case DOLAR -> "US$";
            case PESO_ARGENTINO -> "ARS";
            case PESO_CHILENO -> "CLP";
        };
    }

    public static ConversionOption mapFromInteger(int value) {
        return ConversionOption.values()[value];
    }
}
