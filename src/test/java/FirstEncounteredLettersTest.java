import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FirstEncounteredLettersTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("abdvxv", 2, new char[]{'a', 'v'}),
                Arguments.of("atttbdvvxaxxa", 3, new char[]{'b', 'v', 'a'}),

                Arguments.of("aabbccddeeff", 3, new char[]{' ', 'a', ' '}),
                Arguments.of("zzzyyyxxxwww", 4, new char[]{' ', ' ', 'z', ' '}),
                Arguments.of("abcabcabc", 4, new char[]{' ', ' ', 'a', ' '}),
                Arguments.of("mississippi", 4, new char[]{'m', 'p', ' ', 'i'}),

                Arguments.of("", 3, new char[]{' ', ' ', ' '}),
                Arguments.of("a", 1, new char[]{'a'}),
                Arguments.of("aaa", 2, new char[]{' ', ' '}),
                Arguments.of("abcdef", 6, new char[]{'a', ' ', ' ', ' ', ' ', ' '}), // Все уникальные
                Arguments.of("abc", 0, new char[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testGetFirstEncounteredLetters(String input, int n, char[] expected) {
        char[] result = LetterCounter.getFirstEncounteredLetters(input, n);
        assertArrayEquals(expected, result,
                String.format("For input '%s' with n=%d", input, n));
    }

    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            LetterCounter.getFirstEncounteredLetters(null, 3);
        });
    }

    @Test
    void testNegativeN() {
        assertThrows(NegativeArraySizeException.class, () -> {
            LetterCounter.getFirstEncounteredLetters("abc", -1);
        });
    }
}