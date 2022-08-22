package type;

import java.util.function.Consumer;
import java.util.function.Function;

public final class Either<TRight, TLeft> {

    private final boolean isLeft;
    private final TRight rightValue;
    private final TLeft leftValue;

    private Either(TRight rightValue, TLeft leftValue, boolean isLeft) {
        this.rightValue = rightValue;
        this.leftValue = leftValue;
        this.isLeft = isLeft;
    }

    public static<TRight, TLeft> Either<TRight, TLeft> right(TRight value) {
        return new Either<>(value, null, false);
    }

    public static<TRight, TLeft> Either<TRight, TLeft> left(TLeft value) {
        return new Either<>(null, value, true);
    }

    public boolean isLeft() {
        return this.isLeft;
    }

    public boolean isRight() {
        return !this.isLeft;
    }

    public TRight unsafeGetRight() {
        return this.rightValue;
    }

    public TLeft unsafeGetLeft() {
        return this.leftValue;
    }

    public boolean match(Function<TRight, Boolean> rightConsumer, Function<TLeft, Boolean> leftConsumer) {
        if (isLeft) {
            return leftConsumer.apply(leftValue);
        } else {
            return rightConsumer.apply(rightValue);
        }
    }
}
