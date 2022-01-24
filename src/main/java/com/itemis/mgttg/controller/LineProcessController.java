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
        String regex;
        Pattern pattern;
        Matcher matcher;

        //'WORD is CHAR'
        regex = "("+REGEX_WORD+") +is +(\\W)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processRomanAssignmentLine(matcher.group(1), matcher.group(2).charAt(0));
        }

        //'WORD+ WORD is INT Credits'
        regex = "(" + REGEX_WORD + ")+ +(" + REGEX_WORD + ") is +(" + REGEX_NUMBER + ") +credits";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processValueAssignmentLine(
                    matcher.group(1).split(" "),
                    matcher.group(2),
                    Float.parseFloat(matcher.group(3)));
        }

        //'how much is WORD+'
        regex = "how much is *("+REGEX_WORD+" )+ +\\??";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processTranslationRequest(matcher.group(1).split(" "));
        }

        //'how many Credits is WORD+ WORD'
        regex = "how many credits is *("+REGEX_WORD+" )+ +("+REGEX_WORD+")";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        if(matcher.matches()){
            return processPriceRequest(matcher.group(1).split(" "), matcher.group(2));
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
            //TODO maybe return which word in Result message string
            return new Result(ResultCode.ALIEN_WORD_UNKNOWN);
        }
        if(valueOfAlienWords == -2){
            return new Result(ResultCode.ROMAN_NUMERAL_INVALID);
        }
        //calculate value for 1 unit of material
        float materialPrice = MainController.getInstance().calculateMaterialPrice(valueOfAlienWords, valueOfTotal);
        try {
            MainController.getInstance().addMaterial(material, materialPrice);
        } catch(MaterialPriceException e){
            return new Result(ResultCode.MATERIAL_ALREADY_KNOWN, materialPrice, material);
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
            //TODO maybe return which word in Result message string
            return new Result(ResultCode.ALIEN_WORD_UNKNOWN);
        }
        if(valueOfAlienWords == -2){
            return new Result(ResultCode.ROMAN_NUMERAL_INVALID);
        }

        return new Result(ResultCode.OK, valueOfAlienWords);
    }

    /**
     * Processes a request line that asks for a price of a material in alien words
     * @param words String array of alien Words representing roman numerlas
     * @param material The requested material
     * @return Result Object
     */
    private Result processPriceRequest(String[] words, String material){
        int valueOfAlienWords = MainController.getInstance().convertAlienWordsToInt(words);
        float priceOfMaterial = MainController.getInstance().getMaterialPrice(material);

        if(valueOfAlienWords == -1){
            //TODO maybe return which word in Result message string
            return new Result(ResultCode.ALIEN_WORD_UNKNOWN);
        }
        if(valueOfAlienWords == -2){
            return new Result(ResultCode.ROMAN_NUMERAL_INVALID);
        }

        float answer = priceOfMaterial * (float) valueOfAlienWords;
        return new Result(ResultCode.OK, answer);
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
}
