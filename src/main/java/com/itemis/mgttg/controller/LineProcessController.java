package com.itemis.mgttg.controller;

import com.itemis.mgttg.exceptions.MaterialPriceException;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import com.itemis.mgttg.tools.Pair;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineProcessController {

    final String REGEX_WORD="\\w+";
    final String REGEX_NUMBER="\\d*\\.?\\d*";

    /**
     * Processes an input line and returns the answer as String
     * @param input Line to Process
     * @return Result Object
     */
    public Result processLine(String input){
        //Decide what type of line it is, call the corresponding function and return its Result
        input=input.toLowerCase().trim();
        //patterns:
        //'list materials'
        if(input.matches("list materials")){
            return processListMaterials();
        }

        //'list words'
        if(input.matches("list words")){
            return processListWords();
        }
        //'help'
        if(input.matches("help")){
            return new Result(ResultCode.HELP);
        }
        //'exit'
        if(input.matches("exit")){
            return new Result(ResultCode.EXIT);
        }

        //Easter egg
        if(input.matches("the answer to life, the universe, and everything")){
            return new Result(ResultCode.EASTEREGG, "42");
        }

        String regex;
        Pattern pattern;
        Matcher matcher;

        regex="file +(.+)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        //File IO
        if(matcher.matches()){
            return new Result(ResultCode.FROM_FILE, matcher.group(1));
        }

        //removeword WORD
        regex="removeword +(\\w+)($|\\W.*)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processRemoveWord(matcher.group(1));
        }

        //removematerial WORD
        regex="removematerial +(\\w+)($|\\W.*)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processRemoveMaterial(matcher.group(1));
        }

        //'WORD is CHAR'
        regex = "("+REGEX_WORD+") +is +(\\w)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processRomanAssignmentLine(matcher.group(1), matcher.group(2).charAt(0));
        }

        //'WORD+ WORD is INT Credits'
        regex = "((" + REGEX_WORD + " )+)(" + REGEX_WORD + ") is +(" + REGEX_NUMBER + ") +credits";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processValueAssignmentLine(
                    matcher.group(1).split(" "),
                    matcher.group(matcher.groupCount()-1),
                    Float.parseFloat(matcher.group(matcher.groupCount())));
        }

        //'how much is WORD+'
        regex = "how much is +(("+REGEX_WORD+"( |$))+) *\\??";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processTranslationRequest(matcher.group(1).split(" "));
        }

        //'how many Credits is WORD+ WORD'
        regex = "how many credits is +(("+REGEX_WORD+" )+)("+REGEX_WORD+")($| *\\?)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processPriceRequest(matcher.group(1).split(" "), matcher.group(matcher.groupCount()-1));
        }


        //not understandable
        return new Result(ResultCode.DIDNT_UNDERSTAND_LINE);
    }

    /**
     * Processes a Line that assigns a roman to a word by calling the MainController and adding it,
     * if no exception is thrown
     * @param word the new word to learn
     * @param romanSymbol the Symbol it is to be assigned to
     * @return Result Object
     */
    private Result processRomanAssignmentLine(String word, Character romanSymbol){
        romanSymbol = Character.toUpperCase(romanSymbol);
        try{
            MainController.getInstance().addWord(word, romanSymbol);
        } catch(WordAlreadyExistsException e) {
            return new Result(ResultCode.WORD_ALREADY_KNOWN, word);
        }
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a line that calculates the value of a material, and learns the price of 1 unit of that material
     * @param words The amount of material, represented as words that mean roman numerals
     * @param material The traded material
     * @param valueOfTotal The value of the amount of material
     * @return Result Object
     */
    private Result processValueAssignmentLine(String[] words, String material, float valueOfTotal){
        //get alien word value
        int valueOfAlienWords = MainController.getInstance().convertAlienWordsToInt(words);
        if(valueOfAlienWords == -1){
            return new Result(ResultCode.ALIEN_WORD_UNKNOWN, MainController.getInstance().getUnknownWordsFromList(words));
        }
        if(valueOfAlienWords == -2){
            return new Result(ResultCode.ROMAN_NUMERAL_INVALID);
        }
        //calculate value for 1 unit of material
        float materialPrice = MainController.getInstance().calculateMaterialPrice(valueOfAlienWords, valueOfTotal);
        try {
            MainController.getInstance().addMaterial(material, materialPrice);
        } catch(MaterialPriceException e){
            float oldValue = MainController.getInstance().getMaterialPrice(material);
            return new Result(ResultCode.MATERIAL_ALREADY_KNOWN, oldValue, material);
        }
        return new Result(ResultCode.OK);
    }

    /**
     * Processes a request line that translates alien words representing roman numerals to a Number
     * @param words to translate
     * @return Result Object
     */
    private Result processTranslationRequest(String[] words){
        int valueOfAlienWords = MainController.getInstance().convertAlienWordsToInt(words);
        if(valueOfAlienWords == -1){
            return new Result(ResultCode.ALIEN_WORD_UNKNOWN, MainController.getInstance().getUnknownWordsFromList(words));
        }
        if(valueOfAlienWords == -2){
            return new Result(ResultCode.ROMAN_NUMERAL_INVALID);
        }

        String input_formatted = "";
        for(String word : words){
            input_formatted += word + " ";
        }
        input_formatted = input_formatted.trim();

        return new Result(ResultCode.TRANSLATION_ANSWER, valueOfAlienWords, input_formatted);
    }

    /**
     * Processes a request line that asks for a price of a material in alien words
     * @param words String array of alien Words representing roman numerlas
     * @param material The requested material
     * @return Result Object
     */
    private Result processPriceRequest(String[] words, String material){
        int valueOfAlienWords = MainController.getInstance().convertAlienWordsToInt(words);
        Float priceOfMaterial = MainController.getInstance().getMaterialPrice(material);

        if(valueOfAlienWords == -1){
            return new Result(ResultCode.ALIEN_WORD_UNKNOWN, MainController.getInstance().getUnknownWordsFromList(words));
        }
        if(valueOfAlienWords == -2){
            return new Result(ResultCode.ROMAN_NUMERAL_INVALID);
        }
        if(priceOfMaterial == null){
            return new Result(ResultCode.MATERIAL_UNKNOWN, material);
        }

        float answer = priceOfMaterial * (float) valueOfAlienWords;
        String input_formatted = "";
        for(String word : words){
            input_formatted += word + " ";
        }
        input_formatted += material.substring(0, 1).toUpperCase() + material.substring(1);;
        input_formatted = input_formatted.trim();
        return new Result(ResultCode.PRICE_ANSWER, answer, input_formatted);
    }

    /**
     * Processes a request of all known materials and their prices
     * @return A result object containing a formatted List of all Materials and their price
     */
    private Result processListMaterials(){
        ArrayList<Pair<String, Float>> knownMaterials = MainController.getInstance().getAllMaterials();
        String answer = "";
        for(int i=0; i<knownMaterials.size(); i++){
            answer+=knownMaterials.get(i).getLeft()+"\t--\t"+knownMaterials.get(i).getRight();
            if(i != knownMaterials.size()-1){
                answer+="\n";
            }
        }
        return new Result(ResultCode.LIST_MATERIALS, answer);
    }

    /**
     * Processes a request of all known Words and their meaning
     * @return A result object containing a formatted List of all Words and their meaning
     */
    private Result processListWords(){
        ArrayList<Pair<String, Character>> knownWords = MainController.getInstance().getAllWords();
        String answer = "";
        for(int i=0; i<knownWords.size(); i++){
            answer+=knownWords.get(i).getLeft()+"\t--\t"+knownWords.get(i).getRight();
            if(i != knownWords.size()-1){
                answer+="\n";
            }
        }
        return new Result(ResultCode.LIST_WORDS, answer);
    }

    /**
     * Removes an already known word, does nothing if the word isn't known yet
     * @param word The word to remove
     * @return Result with code OK
     */
    private Result processRemoveWord(String word){
        MainController.getInstance().removeWord(word);
        return new Result(ResultCode.OK);
    }

    /**
     * Removes an already known Material, does nothing if the material isn't known yet
     * @param material The material to remove
     * @return Result with code OK if the material was removed
     */
    private Result processRemoveMaterial(String material){
        MainController.getInstance().removeMaterial(material);
        return new Result(ResultCode.OK);
    }
}
