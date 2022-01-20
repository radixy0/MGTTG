package com.itemis.mgttg.tools;
import java.util.HashMap;

public class NumberConverter {

    //TODO: find better way of going roman to int, so both methods can be static

    static HashMap<Character, Integer> intToRomanMap = new HashMap<>();
    static{
        intToRomanMap.put('I',1);
        intToRomanMap.put('V',5);
        intToRomanMap.put('X',10);
        intToRomanMap.put('L',50);
        intToRomanMap.put('C',100);
        intToRomanMap.put('D',500);
        intToRomanMap.put('M',1000);
    }

    /**
     * Converts Roman numeral String to Int
     * @param roman - Roman numeral String
     * @return integer representation
     */
    public int romanToInt(String roman){
        int sum=0;

        if(roman.isEmpty()){return 0;}

        for(int i=0; i<roman.length(); i++){
            char current = roman.charAt(i);
            if(!(intToRomanMap.containsKey(current))){
                throw new RuntimeException("Unknown Character in Roman Numeral");
            }
            if(i!=roman.length()-1){
                char next = roman.charAt(i+1);
                if(intToRomanMap.get(current) < intToRomanMap.get(next)){
                    sum-=intToRomanMap.get(current);
                } else {
                    sum+=intToRomanMap.get(current);
                }
            } else {
                sum+=intToRomanMap.get(current);
            }
        }
        return sum;
    }

    /**
     * Converts Integer value to Roman numeral String
     * @param num integer to convert
     * @return Roman numeral String
     */
    public String intToRoman(int num){
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
