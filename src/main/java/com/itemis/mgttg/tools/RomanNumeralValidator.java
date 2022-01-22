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

    /**
     * Checks if a roman Numeral contains illegal combinations of characters
     * @param roman
     * @return true, if the numeral contains any illegal pattern
     */
    public static boolean containsIllegalCombinations(String roman){
        //TODO implement
        //check for repeated D, L or V
        if(roman.contains("DD") || roman.contains("LL") || roman.contains("VV")){
            return true;
        }
        //check for 4x repetition of I, X, C, M
        if(roman.contains("IIII") || roman.contains("XXXX") || roman.contains("CCCC") || roman.contains("MMMM")){
            return true;
        }
        //check if I is ever substracted by other than V or X
        if(roman.contains("IL") || roman.contains("IC") || roman.contains("ID") || roman.contains("IM")){
            return true;
        }
        //check if X is ever substracted by other than L or C
        if(roman.contains("XD") || roman.contains("XL")){
            return true;
        }
        //check if V, L, D are ever substracted
        //check if multiple small values are substracted
        for(int i=0; i<roman.length()-1; i++){
            char current = roman.charAt(i);
            char next = roman.charAt(i+1);
            if(current == next){
                //multiple small values substracted, if character after next is bigger than current and next
                if(i+2 < roman.length()){
                    char toCheck = roman.charAt(i+2);
                    if(NumberConverter.romanChartoIntDigit(toCheck) > NumberConverter.romanChartoIntDigit(current)){
                        return true;
                    }
                }
            }
            if(current == 'V'){
                switch(next){
                    case 'X':
                    case 'L':
                    case 'C':
                    case 'D':
                    case 'M':
                        return true;
                }
            }
            else if (current == 'L'){
                switch(next){
                    case 'C':
                    case 'D':
                    case 'M':
                        return true;
                }
            }
            else if (current == 'D'){
                switch(next){
                    case 'M': return true;
                }
            }
        }
        return false;
    }
}
