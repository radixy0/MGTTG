package com.itemis.mgttg.knowledge;
import java.util.HashMap;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;

public class Words {
    HashMap<String, Character> words;
    public Words(){
        words = new HashMap<>();
    }
    public void learnNewWord(String word, char value) throws WordAlreadyExistsException{
        if(words.containsKey(word)){
            throw new WordAlreadyExistsException("I already know this word as " + words.get(word), word, words.get(word));
        }
        words.put(word, value);
    }
    public void forgetWord(String word){
        words.remove(word);
    }
    public void changeValue(String word, char value){
        words.put(word, value);
    }
}
