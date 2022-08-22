package type;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EitherTest {

    @Test
    public void rightCreatesAnEitherObjectWithLeftEmptyAndValueSavedOnRight() {
        Either<String, String> sut = Either.right("All good pal!");


        assertEquals("All good pal!", sut.unsafeGetRight());
        assertFalse(sut.isLeft());
        assertTrue(sut.isRight());
        assertNull(sut.unsafeGetLeft());
    }

    @Test
    public void leftCreatesAnEitherObjectWithEmptyRightAndValueSavedOnLeft() {
        Either<String, String> sut = Either.left("This is not good though");


        assertEquals("This is not good though", sut.unsafeGetLeft());
        assertTrue(sut.isLeft());
        assertFalse(sut.isRight());
        assertNull(sut.unsafeGetRight());
    }

    @Test
    public void matchShouldExecuteRightConsumerWhenEitherStateIsRight() {
        Consumer<String> rightConsumer = mock(Consumer.class);
        Consumer<String> leftConsumer = mock(Consumer.class);


        Either<String, String> sut = Either.right("Nothing to see here");
        sut.match(rightConsumer, leftConsumer);


        verify(rightConsumer, times(1)).accept("Nothing to see here");
        verify(leftConsumer, never()).accept(anyString());
    }

    @Test
    public void matchShouldExecuteLeftConsumerWhenEitherStateIsLeft() {
        Consumer<String> rightConsumer = mock(Consumer.class);
        Consumer<String> leftConsumer = mock(Consumer.class);


        Either<String, String> sut = Either.left("Error Error");
        sut.match(rightConsumer, leftConsumer);


        verify(rightConsumer, never()).accept(anyString());
        verify(leftConsumer, times(1)).accept("Error Error");
    }
}
