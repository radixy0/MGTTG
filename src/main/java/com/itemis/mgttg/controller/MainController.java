package com.itemis.mgttg.controller;
import com.itemis.mgttg.model.*;
import com.itemis.mgttg.exceptions.*;
import com.itemis.mgttg.tools.NumberConverter;
import com.itemis.mgttg.tools.Pair;
import com.itemis.mgttg.tools.RomanNumeralValidator;

import java.util.ArrayList;
import java.util.Locale;

public class MainController {
    Materials materials;
    Words words;
    private static MainController maincontroller_instance = null;

    /**
     * Creates a new Instance
     */
    public MainController(){
        materials = new Materials();
        words = new Words();
    }

    public static MainController getInstance(){
        if(maincontroller_instance == null){
            maincontroller_instance = new MainController();
        }
        return maincontroller_instance;
    }

    /**
     * Calculates int value of an array of alien words, which represent roman numerals
     * @param words Array of words to convert
     * @return -1, if a word is unknown. -2, if the represented roman numeral is invalid. >0 otherwise
     */
    public int convertAlienWordsToInt(String[] words){
        //get corresponding romans
        char[] romansArray = new char[words.length];
        for(int i=0; i<words.length; i++){
            Character romanValue = getWordsRomanValue(words[i]);
            if(romanValue != null){
                romansArray[i] = romanValue;
            } else {
                return -1;
            }
        }
        String romans = new String(romansArray);
        if(RomanNumeralValidator.containsIllegalCombinations(romans)){
            return -2;
        }
        if(RomanNumeralValidator.containsIllegalLetters(romans)){
            return -2;
        }
        return NumberConverter.romanToInt(romans);
    }

    /**
     * Calculates the Material price from the Roman amount and the total Value, e.g. XI Iron = 5
     * @param romanAmount roman amount of sold material
     * @param value value of total
     * @return -1 if the roman number is invalid, otherwise the price for 1 unit of the material as float
     */
    public float calculateMaterialPrice(String romanAmount, float value){
        if(RomanNumeralValidator.containsIllegalCombinations(romanAmount)){
            return -1;
        }
        if(RomanNumeralValidator.containsIllegalLetters(romanAmount)){
            return -1;
        }
        float romanValue = NumberConverter.romanToInt(romanAmount.toUpperCase());
        return value / romanValue;
    }

    /**
     * Calculates the material price from an int amount and the total value, e.g. 6 Iron = 5
     * @param amount integer amount of sold material units
     * @param value value of total
     * @return The price for 1 unit of the material as float, or 0 if amount is 0
     */
    public float calculateMaterialPrice(int amount, float value){
        if(amount == 0){
            return 0;
        }
        return value / amount;
    }

    /**
     * Gets the Price for a material String as Float
     * @param material the material to look up
     * @return material price as float, -1 if unknown
     */
    public Float getMaterialPrice(String material){
        return materials.getPrice(material.toLowerCase());
    }

    /**
     * Gets the Roman Character for a word
     * @param word the word to look up
     * @return Roman value as Character, null if unknown
     */
    public Character getWordsRomanValue(String word){
        return words.getValue(word.toLowerCase());
    }

    /**
     * Adds a new material and its price to the Model
     * @param material Material String to add
     * @param value Price
     * @throws MaterialPriceException if the Material is already known with a different price
     */
    public void addMaterial(String material, float value) throws MaterialPriceException {
        materials.addMaterial(material.toLowerCase(), value);
    }

    /**
     * Adds a new material and its price to the Model
     * @param material Material String to add
     * @param value Price
     * @throws MaterialPriceException if the Material is already known with a different price
     */
    public void addMaterial(String material, int value) throws MaterialPriceException {
        addMaterial(material, (float) value);
    }

    /**
     * Adds a new word and its roman value to the model
     * @param word Word to add
     * @param romanValue Value
     * @throws WordAlreadyExistsException if the word already exists for a different roman Letter
     */
    public void addWord(String word, char romanValue) throws WordAlreadyExistsException{
        words.addWord(word.toLowerCase(), Character.toUpperCase(romanValue));
    }

    /**
     * Updates price for an already known material
     * @param material Material to update
     * @param value  Value
     */
    public void updateMaterial(String material, float value){
        materials.updatePrice(material.toLowerCase(), value);
    }

    /**
     * Updates price for an already known material
     * @param material Material to update
     * @param value  Value
     */
    public void updateMaterial(String material, int value){
        updateMaterial(material, (float) value);
    }

    /**
     * Updates value of an already known word
     * @param word Word to update
     * @param romanValue value
     */
    public void updateWord(String word, char romanValue){
        words.updateWord(word.toLowerCase(), Character.toUpperCase(romanValue));
    }

    /**
     * Removes a Material from model
     * @param material Material to remove
     */
    public void removeMaterial(String material){
        materials.removeMaterial(material.toLowerCase());
    }

    /**
     * Removes a Word from model
     * @param word Word to remove
     */
    public void removeWord(String word){
        words.removeWord(word.toLowerCase());
    }

    /**
     * Gets a List of Pairs of all known Materials and their price
     * @return ArrayList of Pair<String, Float> Objects representing materials
     */
    public ArrayList<Pair<String, Float>> getAllMaterials(){
        return materials.getAllMaterials();
    }

    /**
     * Gets a List of Pairs of all known words and their value
     * @return ArrayList of Pair<String, Character> Objects representing words
     */
    public ArrayList<Pair<String, Character>> getAllWords(){
        return words.getAllWords();
    }

    public String getUnknownWordsFromList(String[] words){
        ArrayList<Pair<String, Character>> knownWords = getAllWords();
        String unknownWords = "";
        for(String word : words){
            boolean found=false;
            for(Pair<String, Character> knownWord : knownWords){
                if(knownWord.getLeft().equals(word)){
                    found=true;
                }
            }
            if(!found){
                unknownWords+=word+" ";
            }
        }
        //formatting
        unknownWords=unknownWords.trim().replaceAll(" ", ", ");
        return unknownWords;
    }
}
