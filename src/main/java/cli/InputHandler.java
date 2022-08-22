package cli;

import type.Either;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }


    public Either<BigDecimal, Exception> readAmountOfBrazilianRealToBeConverted() {
        try {
            return Either.right(scanner.nextBigDecimal());
        } catch (Exception e) {
            scanner.next();
            return Either.left(e);
        }
    }

    public Either<ConversionOption, Exception> readConversionOption() {
        try {
            var inputted = scanner.nextInt();
            return Either.right(ConversionOption.mapFromInteger(inputted));
        } catch (Exception e) {
            scanner.next();
            return Either.left(e);
        }
    }
}
