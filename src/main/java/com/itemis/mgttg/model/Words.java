package com.itemis.mgttg.model;
import java.util.HashMap;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
//TODO Comment for doc
public class Words {
    HashMap<String, Character> words;
    public Words(){
        words = new HashMap<>();
    }
    public void addWord(String word, char value) throws WordAlreadyExistsException{
        if(words.containsKey(word) && words.get(word) != value){
            throw new WordAlreadyExistsException("I already know this word as " + words.get(word), word, words.get(word));
        }
        words.put(word, value);
    }
    public void removeWord(String word){
        words.remove(word);
    }
    public void updateWord(String word, char value){
        words.put(word, value);
    }
    public char getValue(String word){
        return words.get(word);
    }
}
