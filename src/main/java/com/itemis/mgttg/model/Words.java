package com.itemis.mgttg.model;
import java.util.ArrayList;
import java.util.HashMap;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import com.itemis.mgttg.tools.Pair;
public class Words {

    HashMap<String, Character> words;
    public Words(){
        words = new HashMap<>();
    }

    /**
     * Adds a new Word and its roman Character to internal HashMap
     * @param word Word to add
     * @param value roman Character
     * @throws WordAlreadyExistsException
     */
    public void addWord(String word, char value) throws WordAlreadyExistsException{
        if(words.containsKey(word) && words.get(word) != value){
            throw new WordAlreadyExistsException("I already know this word as " + words.get(word), word, words.get(word));
        }
        words.put(word, value);
    }

    /**
     * Removes a Word from internal HashMap
     * @param word Word to remove
     */
    public void removeWord(String word){
        words.remove(word);
    }

    /**
     * Updates an already known words roman Value
     * @param word Word to update
     * @param value new value
     */
    public void updateWord(String word, char value){
        words.put(word, value);
    }

    /**
     * gets Value of a known word
     * @param word word to look up
     * @return the words value as float, null if the word is unknown
     */
    public Character getValue(String word){
        return words.get(word);
    }

    /**
     * Clears list of known words
     */
    public void clearWords(){
        words.clear();
    }

    /**
     * Gets a List of all known words and their value
     * @return An ArrayList of Pairs of <String, Character>
     */
    public ArrayList<Pair<String, Character>> getAllWords(){
        ArrayList<Pair<String, Character>> output = new ArrayList<>();
        words.forEach((k,v) -> {
            output.add(new Pair<String, Character>(k,v));
        });
        return output;
    }
}
