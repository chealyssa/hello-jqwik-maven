package hw;

import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PadderPropertiesTest {

    @Property
    void resultAlwaysEndsWithOriginalString(
        @ForAll("strings") String s,
        @ForAll("lengths") int l,
        @ForAll("pads") String p
    ) {
        String r = Padder.padStart(s, l, p);
        assertTrue(r.endsWith(s));
    }

    @Property
    void lengthMatchesPadStartRules(
        @ForAll("strings") String s,
        @ForAll("lengths") int l,
        @ForAll("pads") String p
    ) {
        String r = Padder.padStart(s, l, p);

        if (l <= s.length()) {
            assertEquals(s, r);
            return;
        }

        if (p != null && p.isEmpty()) {
            assertEquals(s, r);
            return;
        }

        assertEquals(l, r.length());
    }

    @Property
    void paddingPrefixIsRepetitionOfPadString(
        @ForAll("strings") String s,
        @ForAll("lengths") int l,
        @ForAll("pads") String p
    ) {
        if (l <= s.length()) return;
        if (p != null && p.isEmpty()) return;

        String pad = (p == null) ? " " : p;
        String r = Padder.padStart(s, l, p);

        int needed = l - s.length();
        String prefix = r.substring(0, needed);

        String expected = buildRepeatedPrefix(pad, needed);
        assertEquals(expected, prefix);
    }

    private static String buildRepeatedPrefix(String pad, int needed) {
        StringBuilder sb = new StringBuilder(needed);
        while (sb.length() < needed) {
            int remaining = needed - sb.length();
            if (remaining >= pad.length()) {
                sb.append(pad);
            } else {
                sb.append(pad, 0, remaining);
            }
        }
        return sb.toString();
    }

    @Provide
    Arbitrary<String> strings() {
        return Arbitraries.strings().ascii().ofMinLength(0).ofMaxLength(50);
    }

    @Provide
    Arbitrary<Integer> lengths() {
        return Arbitraries.integers().between(0, 100);
    }

    @Provide
    Arbitrary<String> pads() {
        return Arbitraries.oneOf(
            Arbitraries.just(""),
            Arbitraries.just("0"),
            Arbitraries.just("01"),
            Arbitraries.strings().ascii().ofMinLength(1).ofMaxLength(10)
        );
    }
}

