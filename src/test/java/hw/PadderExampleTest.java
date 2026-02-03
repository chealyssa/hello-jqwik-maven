package hw;

import net.jqwik.api.Example;
import static org.junit.jupiter.api.Assertions.*;

public class PadderExampleTest {

    @Example
    void noPaddingNeeded() {
        asertEquals("abc", Padder.padStart("abc", 2, "0"));
    }

    @Example
    void singleCharPad() {
        assertEquals("00abc", Padder.padStart("abc", 5, "0"));
    }

    @Example
    void multiCharPad(){
        assertEquals("010abc", Padder.padStart("abc",6, "01"));
    }

}
