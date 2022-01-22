package com.itemis.mgttg.controller;
import com.itemis.mgttg.model.*;
import com.itemis.mgttg.exceptions.*;

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
     * Gets the Price for a mineral String as float
     * @param mineral the mineral to look up
     * @return mineral price as float, null if unknown
     */
    public float getMineralPrice(String mineral){
        return materials.getPrice(mineral);
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
     * Adds a new mineral and its price to the Model
     * @param mineral Mineral String to add
     * @param value Price
     * @throws MaterialPriceException if the Mineral is already known with a different price
     */
    public void addMineral (String mineral, float value) throws MaterialPriceException {
        materials.addMaterial(mineral, value);
    }

    /**
     * Adds a new mineral and its price to the Model
     * @param mineral Mineral String to add
     * @param value Price
     * @throws MaterialPriceException if the Mineral is already known with a different price
     */
    public void addMineral(String mineral, int value) throws MaterialPriceException {
        addMineral(mineral, (float) value);
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
     * Updates price for an already known mineral
     * @param mineral Mineral to update
     * @param value  Value
     */
    public void updateMineral(String mineral, float value){
        materials.updatePrice(mineral, value);
    }

    /**
     * Updates price for an already known mineral
     * @param mineral Mineral to update
     * @param value  Value
     */
    public void updateMineral(String mineral, int value){
        updateMineral(mineral, (float) value);
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
     * Removes a Mineral from model
     * @param mineral Mineral to remove
     */
    public void removeMineral(String mineral){
        materials.removeMaterial(mineral);
    }

    /**
     * Removes a Word from model
     * @param word Word to remove
     */
    public void removeWord(String word){
        words.removeWord(word);
    }
}
