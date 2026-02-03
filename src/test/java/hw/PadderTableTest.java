package hw;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PadderTableTest {
    static Stream<Arguments> cases() {
        return Stream.of(
            // s = ""
            Arguments.of("", 0, "0", ""),
            Arguments.of("", 1, "0", "0"),
            Arguments.of("", 3, "01", "010"),

            // s = "a"
            Arguments.of("a", 0, "0", "a"),
            Arguments.of("a", 1, "0", "a"),
            Arguments.of("a", 5, "0", "0000a"),
            Arguments.of("a", 5, "01", "0101a"),

            // s = "abc"
            Arguments.of("abc", 2, "0", "abc"),
            Arguments.of("abc", 3, "0", "abc"),
            Arguments.of("abc", 5, "0", "00abc"),
            Arguments.of("abc", 6, "01", "010abc"),

            // pad empty
            Arguments.of("abc", 5, "", "abc")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void tableTests(String s, int l, String p, String expected) {
        assertEquals(expected, Padder.padStart(s, l, p));            
    }

}
