import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursiveMergeTest {

    private static class StringMerger extends RecursiveMerge<String> {
        @Override
        public String merge(String first, String second) {
            return first + second;
        }
    }

    private static class IntegerMerger extends RecursiveMerge<Integer> {
        @Override
        public Integer merge(Integer first, Integer second) {
            return first + second;
        }
    }

    private static RecursiveMerge<String> createStringMerger() {
        return new StringMerger();
    }

    private static RecursiveMerge<Integer> createIntegerMerger() {
        return new IntegerMerger();
    }

    private static Stream<Arguments> integerMergeProvider() {
        return Stream.of(
                Arguments.of(Collections.singletonList(1), 1),
                Arguments.of(Arrays.asList(1, 2), 3),
                Arguments.of(Arrays.asList(1, 2, 3), 6),
                Arguments.of(Arrays.asList(5, 5, 5, 5), 20),
                Arguments.of(Arrays.asList(-1, 1, -2, 2), 0),
                Arguments.of(Arrays.asList(100, 200, 300), 600)
        );
    }

    private static Stream<Arguments> stringMergeProvider() {
        return Stream.of(
                Arguments.of(Collections.singletonList("a"), "a"),
                Arguments.of(Arrays.asList("a", "b"), "ab"),
                Arguments.of(Arrays.asList("Hello", " ", "World"), "Hello World"),
                Arguments.of(Arrays.asList("1", " ", "2", " ", "3"), "1 2 3"),
                Arguments.of(Arrays.asList("1", "2", "3"), "123"),
                Arguments.of(Arrays.asList("", "test", ""), "test")
        );
    }

    @ParameterizedTest
    @MethodSource("integerMergeProvider")
    void testIntegerMerge(List<Integer> values, int expected) {
        assertEquals(expected, createIntegerMerger().merge(values));
    }

    @ParameterizedTest
    @MethodSource("stringMergeProvider")
    void testStringMerge(List<String> values, String expected) {
        assertEquals(expected, createStringMerger().merge(values));
    }

    @Test
    void testNullList() {
        assertThrows(IllegalArgumentException.class, () -> createIntegerMerger().merge(null));
    }

    @Test
    void testEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> createIntegerMerger().merge(Collections.emptyList()));
    }

    @Test
    void testNullValuesInList() {
        assertThrows(NullPointerException.class, () -> createStringMerger().merge(Arrays.asList("a", null, "b")));
    }
}
