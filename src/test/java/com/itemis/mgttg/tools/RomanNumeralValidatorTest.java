package com.itemis.mgttg.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralValidatorTest {

    //valid, empty, invalid
    @Test
    void containsIllegalCharacters_ValidNumeralShouldReturnFalse() {
        assertFalse(RomanNumeralValidator.containsIllegalLetters("MCMXCIV"));
    }
    @Test
    void containsIllegalCharacters_EmptyNumeralShouldReturnFalse() {
        assertFalse(RomanNumeralValidator.containsIllegalLetters(""));
    }
    @Test
    void containsIllegalCharacters_InvalidNumeralShouldReturnTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalLetters("MCMACIV"));
    }

    //D L and V cannot be repeated
    @Test
    void containsIllegalCombinations_RepeatedDShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("DD"));
    }
    @Test
    void containsIllegalCombinations_RepeatedLShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("LL"));
    }
    @Test
    void containsIllegalCombinations_RepeatedVShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("VV"));
    }
    //D L and V cannot be substracted
    @Test
    void containsIllegalCombinations_SubstractedDShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("DM"));
    }
    @Test
    void containsIllegalCombinations_SubstractedLShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("LM"));
    }
    @Test
    void containsIllegalCombinations_SubstractedVShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("VM"));
    }
    //I only subtracted from V and X
    @Test
    void containsIllegalCombinations_InvalidISubstractionShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("IC"));
    }
    //X only subtracted from L and C
    @Test
    void containsIllegalCombinations_InvalidXSubstractionShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("XV"));
    }
    //C only substracted from D and M
    @Test
    void containsIllegalCombinations_InvalidCSubstractionShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("CX"));
    }
    //only one small symbol from a large symbol
    @Test
    void containsIllegalCombinations_TwoSmallSymbolsBeforeLargeShouldBeTrue() {
        assertTrue(RomanNumeralValidator.containsIllegalCombinations("IIV"));
    }
    //empty string
    @Test
    void containsIllegalCombinations_EmptyStringShouldBeFalse() {
        assertFalse(RomanNumeralValidator.containsIllegalCombinations(""));
    }
    //valid string
    @Test
    void containsIllegalCombinations_ValidStringShouldBeFalse() {
        assertFalse(RomanNumeralValidator.containsIllegalCombinations("MCMXCIV"));
    }

}