package com.itemis.mgttg.exceptions;

public class WordAlreadyExistsException extends Exception{
    String message;
    String word;
    char value;
    public WordAlreadyExistsException(String message, String word, char value){
        super(message);
        this.message=message;
        this.word=word;
        this.value=value;
    }
}
