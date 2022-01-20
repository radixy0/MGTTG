package com.itemis.mgttg.tools;

public class RomanNumeralValidator {
    static char[] allowedLetters = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    /**
     * Checks if a Roman Numeral contains illegal letters
     * @param roman
     * @return true, if the numeral contains at least one illegal letter
     */
    public static boolean containsIllegalLetters(String roman) {
        for (int i = 0; i < roman.length(); i++){
            boolean characterAllowed=false;
            for(int j=0; j<allowedLetters.length; j++){
                if(roman.charAt(i) == allowedLetters[j]){
                    characterAllowed=true;
                }
            }
            if(!characterAllowed){return true;}
        }
        return false;
    }

    public static boolean containsIllegalCombinations(String roman){
        return false;
    }
}
