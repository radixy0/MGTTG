package com.itemis.mgttg.model;

import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import com.itemis.mgttg.tools.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void clearWords_shouldBeEmptyAfter() {
        Words words = new Words();
        try{
            words.addWord("blargh", 'V');
        } catch(Exception e){
            e.printStackTrace();
        }
        words.clearWords();
        assertTrue(words.words.isEmpty());
    }

    @Test
    void getAllWords_AllPairsShouldBeEqual() {
        Words words = new Words();
        Pair<String, Character> p1 = new Pair("test1", 'I');
        Pair<String, Character> p2 = new Pair("test2", 'V');
        Pair<String, Character> p3 = new Pair("test3", 'X');
        try{
            words.addWord(p1.getLeft(), p1.getRight());
            words.addWord(p2.getLeft(), p2.getRight());
            words.addWord(p3.getLeft(), p3.getRight());
        } catch (WordAlreadyExistsException e){
            e.printStackTrace();
        }
        ArrayList<Pair<String, Character>> toTest = words.getAllWords();
        for(Pair<String, Character> p : toTest){
            boolean equalsToSomething = false;
            if(p.equals(p1)){equalsToSomething=true;}
            if(p.equals(p2)){equalsToSomething=true;}
            if(p.equals(p3)){equalsToSomething=true;}
            assertTrue(equalsToSomething);
        }
    }
}