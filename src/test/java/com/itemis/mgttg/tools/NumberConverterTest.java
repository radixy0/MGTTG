package com.itemis.mgttg.tools;

import static org.junit.jupiter.api.Assertions.*;

class NumberConverterTest {
    NumberConverter converter = new NumberConverter();

    //empty string, random number, false char
    @org.junit.jupiter.api.Test
    void emptyRomanShouldReturnZero() {
        assertEquals(0, converter.romanToInt(""));
    }
    @org.junit.jupiter.api.Test
    void MCMXCIVromanShouldEqualNineteenNinetyFour() {
        assertEquals(1994, converter.romanToInt("MCMXCIV"));
    }

    @org.junit.jupiter.api.Test
    void falseCharInRomanShouldThrowException() {
        assertThrows(RuntimeException.class,
                () -> {
                    converter.romanToInt("MCMACIV");
                });
    }

    @org.junit.jupiter.api.Test
    void romanToInt() {

    }

    @org.junit.jupiter.api.Test
    void intToRoman() {
    }
}