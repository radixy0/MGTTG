package com.itemis.mgttg.model;

import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordsTest {
    @Test
    void addWord_addValidWord() {
        Words words = new Words();
        try{
            words.addWord("blargh", 'V');
        } catch(Exception e){

        }
        assertEquals('V', words.words.get("blargh"));
    }

    @Test
    void addWord_addSameWordTwiceShouldThrowException(){
        Words words = new Words();
        assertThrows(WordAlreadyExistsException.class, () -> {
            words.addWord("blargh", 'V');
            words.addWord("blargh", 'X');
        });
    }
    @Test
    void removeWord_AddAndRemoveWordShouldBeEmptyAfter() {
        Words words = new Words();
        try{
            words.addWord("blargh", 'V');
        } catch(Exception e){

        }
        words.removeWord("blargh");
        assertEquals(true, words.words.isEmpty());
    }

    @Test
    void removeWord_removeWordFromEmptySetShouldBeEmptyAfter(){
        Words words = new Words();
        words.removeWord("blargh");
        assertEquals(true, words.words.isEmpty());
    }
    @Test
    void updateWord_ShouldEqualToNewValue() {
        Words words = new Words();
        try{
            words.addWord("blargh", 'V');
        } catch(Exception e){

        }
        words.updateWord("blargh", 'X');
        assertEquals('X', words.words.get("blargh"));
    }

    @Test
    void getValue() {
        Words words = new Words();
        try{
            words.addWord("blargh", 'V');
        } catch(Exception e){

        }
        assertEquals('V', words.getValue("blargh"));
    }
}