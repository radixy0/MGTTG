package com.itemis.mgttg.controller;

import com.itemis.mgttg.exceptions.MaterialPriceException;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainControllerTest {
    MainController mainController;

    @BeforeEach
    void initialize(){
        mainController = MainController.getInstance();

        mainController.words.clearWords();
        mainController.materials.clearMaterials();

        try {
            mainController.words.addWord("TEST1", 'X');
            mainController.words.addWord("TEST2", 'A');
            mainController.words.addWord("TEST3", 'I');

            mainController.materials.addMaterial("IRON", 1f);
            mainController.materials.addMaterial("BRONZE", 2f);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void getInstance_shouldReturnSameObjectTwice() {
        assertTrue(MainController.getInstance() == MainController.getInstance());
    }

    @Test
    void convertAlienWordsToInt_unknownWordShouldReturnNegativeOne() {
        String[] testArr = {"unknownWord"};
        assertEquals(-1, mainController.convertAlienWordsToInt(testArr));
    }

    @Test
    void convertAlienWordsToInt_invalidRomanShouldReturnNegativeTwo(){
        String[] testArr = {"test2"};
        assertEquals(-2, mainController.convertAlienWordsToInt(testArr));
    }

    @Test
    void convertAlienWordsToInt_emptyListShouldReturnZero(){
        String[] testArr = {};
        assertEquals(0, mainController.convertAlienWordsToInt(testArr));
    }

    @Test
    void convertAlienWordsToInt_validWords(){
        String[] testArr = {"test1", "test3"};
        assertEquals(11, mainController.convertAlienWordsToInt(testArr));
    }

    @Test
    void calculateMaterialPrice_validNumbers() {
        assertEquals(15f, mainController.calculateMaterialPrice("IV", 60f));
    }

    @Test
    void calculateMaterialPrice_invalidRomanNumeralShouldReturnNegativeOne(){
        assertEquals(-1f, mainController.calculateMaterialPrice("A", 50f));
    }

    @Test
    void calculateMaterialPriceInt_zeroAmountShouldReturnZero() {
        assertEquals(0, mainController.calculateMaterialPrice(0, 1231f));
    }

    @Test
    void calculateMaterialPriceInt_validNumbers() {
        assertEquals(15f, mainController.calculateMaterialPrice(4, 60f));
    }

    @Test
    void getMaterialPrice_existingMaterialShouldReturnPrice() {
        assertEquals(2f, mainController.getMaterialPrice("bronze"));
    }

    @Test
    void getMaterialPrice_nonExistingMaterialShouldReturnNull(){
        assertNull(mainController.getMaterialPrice("titanium"));
    }

    @Test
    void getWordsRomanValue_validWordShouldReturnValue() {
        assertEquals('X', mainController.getWordsRomanValue("test1"));
    }

    @Test
    void getWordsRomanValue_unknownWordShouldReturnNull(){
        assertNull(mainController.getWordsRomanValue("unknownWord"));
    }

    @Test
    void addMaterial_newMaterialShouldBeAdded() {
        try{
            mainController.addMaterial("Silver", 50);
        } catch(MaterialPriceException e){
            e.printStackTrace();
        }
        assertEquals(50, mainController.getMaterialPrice("Silver"));
    }

    @Test
    void addMaterial_alreadyKnownMaterialShouldThrowException(){
        try{
            mainController.addMaterial("Gold", 100);

        } catch (MaterialPriceException e){
            e.printStackTrace();
        }
        assertThrows(MaterialPriceException.class, () -> {
           mainController.addMaterial("Gold", 101);
        });
    }

    @Test
    void addMaterial_alreadyKnownMaterialWithSamePriceShouldNotThrowException(){
        boolean exceptionThrown=false;
        try{
            mainController.addMaterial("Diamond", 110);
        } catch (MaterialPriceException e){
            e.printStackTrace();
        }
        try{
            mainController.addMaterial("Diamond", 110);
        } catch(MaterialPriceException e){
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }

    @Test
    void addWord_validWordShouldBeAdded() {
        try{
            mainController.addWord("Test4", 'C');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals('C', mainController.getWordsRomanValue("Test4"));
    }

    @Test
    void addWord_alreadyKnownWordShouldThrowException(){
        try{
            mainController.addWord("Test5", 'L');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertThrows(WordAlreadyExistsException.class, () -> {
           mainController.addWord("Test5", 'C');
        });
    }

    @Test
    void addWord_alreadyKnownWordWithSameValueShouldNotThrowException(){
        boolean exceptionThrown = false;
        try{
            mainController.addWord("test6", 'L');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        try{
            mainController.addWord("test6", 'L');
        } catch(WordAlreadyExistsException e){
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }

    @Test
    void updateMaterial_existingMaterialShouldBeUpdated() {
        mainController.updateMaterial("Iron", 2);
        assertEquals(2f, mainController.getMaterialPrice("Iron"));
    }

    @Test
    void updateMaterial_newMaterialShouldBeCreated(){
        mainController.updateMaterial("Ruby", 1000);
        assertEquals(1000f, mainController.getMaterialPrice("Ruby"));
    }


    @Test
    void updateWord_existingWordShouldBeUpdated() {
        try{
            mainController.addWord("test7", 'I');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        mainController.updateWord("test7", 'M');
        assertEquals('M', mainController.getWordsRomanValue("test7"));
    }

    @Test
    void updateWord_newWordShouldBeCreated(){
        mainController.updateWord("test8", 'M');
        assertEquals('M', mainController.getWordsRomanValue("test8"));
    }

    @Test
    void removeMaterial_existingMaterialShouldBeRemoved() {
        mainController.removeMaterial("Iron");
        assertNull(mainController.getMaterialPrice("Iron"));
    }

    @Test
    void removeMaterial_unknownMaterialShoudNotThrowException(){
        mainController.removeMaterial("unknownMaterial");
    }

    @Test
    void removeWord_knownWordShouldBeRemoved() {
        mainController.removeWord("test1");
        assertNull(mainController.getWordsRomanValue("test1"));
    }

    @Test
    void removeWord_unknownWordShouldNotThrowException(){
        mainController.removeWord("unknownWord123");
    }

}