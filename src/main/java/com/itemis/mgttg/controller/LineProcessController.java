package com.itemis.mgttg.controller;

public class LineProcessController {

    /**
     * Processes an input line and returns the answer as String
     * @param input Line to Process
     * @return Result Object
     */
    public Result processLine(String input){
        //Decide what type of line it is, call the corresponding function and return its Result
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a Line that assigns a roman to a word by calling the MainController and adding it,
     * if no exception is thrown
     * @param word the new word to learn
     * @param romanSymbol the Symbol it is to be assigned to
     * @return Result Object
     */
    private Result processRomanAssignmentLine(String word, Character romanSymbol){
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a line that calculates the value of a material, and learns the price of 1 unit of that material
     * @param words The amount of material, represented as words that mean roman numerals
     * @param material The traded material
     * @param value The value of the amount of material
     * @return Result Object
     */
    private Result processValueAssignmentLine(String[] words, String material, float value){
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a request line that translates alien words representing roman numerals to a Number
     * @param words to translate
     * @return Result Object
     */
    private Result processTranslationRequest(String[] words){
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a request line that asks for a price of a material in alien words
     * @param words String array of alien Words representing roman numerlas
     * @param material The requested material
     * @return Result Object
     */
    private Result processPriceRequest(String[] words, String material){
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a request of all known materials and their prices
     * @return A result object containing a formatted List of all Materials and their price
     */
    private Result processListMaterialsLine(){
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a request of all known Words and their meaning
     * @return A result object containing a formatted List of all Words and their meaning
     */
    private Result processListWordsLine(){
        return new Result(ResultCode.OK);
    }

}
