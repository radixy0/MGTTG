package com.itemis.mgttg.tools;
import java.util.HashMap;

public class NumberConverter {

    //TODO: find prettier way of converting roman to int

    static HashMap<Character, Integer> romanToIntMap = new HashMap<>();
    static{
        romanToIntMap.put('I',1);
        romanToIntMap.put('V',5);
        romanToIntMap.put('X',10);
        romanToIntMap.put('L',50);
        romanToIntMap.put('C',100);
        romanToIntMap.put('D',500);
        romanToIntMap.put('M',1000);
    }

    /**
     * Converts valid Roman numeral String to Int
     * @param roman - Roman numeral String
     * @return integer representation
     */
    public static int romanToInt(String roman){
        int sum=0;
        //validate input
        if(roman.isEmpty()){return 0;}
        if(RomanNumeralValidator.containsIllegalLetters(roman)){
            throw new IllegalArgumentException("Roman numeral seems to contain illegal letters");
        }
        for(int i=0; i<roman.length(); i++){
            char current = roman.charAt(i);
            if(i!=roman.length()-1){
                char next = roman.charAt(i+1);
                if(romanToIntMap.get(current) < romanToIntMap.get(next)){
                    sum-= romanToIntMap.get(current);
                } else {
                    sum+= romanToIntMap.get(current);
                }
            } else {
                sum+= romanToIntMap.get(current);
            }
        }
        return sum;
    }

    /**
     * Converts Integer value to Roman numeral String
     * @param num integer to convert
     * @return Roman numeral String
     */
    public static String intToRoman(int num){
        return "I".repeat(num)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }
}
