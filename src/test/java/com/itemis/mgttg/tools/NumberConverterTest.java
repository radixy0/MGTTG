package com.itemis.mgttg.tools;

import static org.junit.jupiter.api.Assertions.*;

class NumberConverterTest {

    @org.junit.jupiter.api.Test
    void emptyRomanShouldReturnZero() {
        assertEquals(0, NumberConverter.romanToInt(""));
    }
    @org.junit.jupiter.api.Test
    void MCMXCIVromanShouldEqualNineteenNinetyFour() {
        assertEquals(1994, NumberConverter.romanToInt("MCMXCIV"));
    }

    @org.junit.jupiter.api.Test
    void falseCharInRomanShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    NumberConverter.romanToInt("MCMACIV");
                });
    }

    @org.junit.jupiter.api.Test
    void zeroToRomanShouldReturnEmptyString() {
        assertEquals("", NumberConverter.intToRoman(0));
    }
    @org.junit.jupiter.api.Test
    void negativeToRomanShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    NumberConverter.intToRoman(-1);
                });
    }
    @org.junit.jupiter.api.Test
    void nineteenNinetyFourToRomanShouldEqualMCMXCIV() {
        assertEquals("MCMXCIV", NumberConverter.intToRoman(1994));
    }
}