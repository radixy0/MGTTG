package com.itemis.mgttg.controller;
import com.itemis.mgttg.model.*;
import com.itemis.mgttg.exceptions.*;
import com.itemis.mgttg.tools.NumberConverter;

public class MainController {
    Materials materials;
    Words words;
    private static MainController maincontroller_instance = null;
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
     * Calculates the Material price from the Roman amount and the total Value, e.g. XI Iron = 5
     * @param romanAmount roman amount of sold material
     * @param material sold material
     * @param value value of total
     * @return
     */
    public float calculateMaterialPrice(String romanAmount, String material, float value){
        float romanValue = NumberConverter.romanToInt(romanAmount);
        return value / romanValue;
    }

    /**
     * Gets the Price for a material String as float
     * @param material the material to look up
     * @return material price as float, null if unknown
     */
    public float getMaterialPrice(String material){
        return materials.getPrice(material);
    }

    /**
     * Gets the Roman Character for a word
     * @param word the word to look up
     * @return Roman value as Character, null if unknown
     */
    public Character getWordsRomanValue(String word){
        return words.getValue(word);
    }

    /**
     * Adds a new material and its price to the Model
     * @param material Material String to add
     * @param value Price
     * @throws MaterialPriceException if the Material is already known with a different price
     */
    public void addMaterial(String material, float value) throws MaterialPriceException {
        materials.addMaterial(material, value);
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
        words.addWord(word, romanValue);
    }

    /**
     * Updates price for an already known material
     * @param material Material to update
     * @param value  Value
     */
    public void updateMaterial(String material, float value){
        materials.updatePrice(material, value);
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
        words.updateWord(word, romanValue);
    }

    /**
     * Removes a Material from model
     * @param material Material to remove
     */
    public void removeMaterial(String material){
        materials.removeMaterial(material);
    }

    /**
     * Removes a Word from model
     * @param word Word to remove
     */
    public void removeWord(String word){
        words.removeWord(word);
    }
}
