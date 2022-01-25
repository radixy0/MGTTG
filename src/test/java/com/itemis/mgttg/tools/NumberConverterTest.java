package com.itemis.mgttg.tools;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumberConverterTest {

    @Test
    void emptyRomanShouldReturnZero() {
        assertEquals(0, NumberConverter.romanToInt(""));
    }
    @Test
    void MCMXCIVromanShouldEqualNineteenNinetyFour() {
        assertEquals(1994, NumberConverter.romanToInt("MCMXCIV"));
    }

    @Test
    void falseCharInRomanShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    NumberConverter.romanToInt("MCMACIV");
                });
    }

    @Test
    void zeroToRomanShouldReturnEmptyString() {
        assertEquals("", NumberConverter.intToRoman(0));
    }
    @Test
    void negativeToRomanShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    NumberConverter.intToRoman(-1);
                });
    }
    @Test
    void nineteenNinetyFourToRomanShouldEqualMCMXCIV() {
        assertEquals("MCMXCIV", NumberConverter.intToRoman(1994));
    }

    @Test
    void romanChartoIntDigit_unknownCharShouldReturnNull() {
        assertNull(NumberConverter.romanChartoIntDigit('A'));
    }

    @Test
    void romanCharToIntDigit_knownCharShouldReturnDigit(){
        assertEquals(5, NumberConverter.romanChartoIntDigit('V'));
    }
}