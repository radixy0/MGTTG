package com.itemis.mgttg.controller;

import com.itemis.mgttg.exceptions.MaterialPriceException;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class LineProcessControllerTest {

    LineProcessController lineProcessController;
    MainController mainController;

    @BeforeAll
    void initialize(){
        mainController=MainController.getInstance();
        lineProcessController = new LineProcessController();
    }

    @BeforeEach
    void reset(){
        mainController.materials.clearMaterials();
        mainController.words.clearWords();
    }
    //Cases: OK,
    @Test
    void processLine_AddValidWord(){
        try {
            mainController.addWord("test1", 'V');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.OK), lineProcessController.processLine("test1 is V"));
    }

    @Test
    void processLine_addValidMaterial(){
        try {
            mainController.addMaterial("iron", 1f);
            mainController.addWord("test1", 'V');
        } catch(MaterialPriceException | WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.OK), lineProcessController.processLine("test1 iron is 5 credits"));
    }

    // TRANSLATION_ANSWER,
    @Test
    void processLine_TranslationRequestForKnownWord(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addWord("test2", 'M');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.TRANSLATION_ANSWER, 1005, "test2 test1"),
                lineProcessController.processLine("how much is test2 test1"));
    }
    @Test
    void processLine_TranslationRequestForInvalidRomans(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addWord("test2", 'M');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.ROMAN_NUMERAL_INVALID),
                lineProcessController.processLine("how much is test1 test2"));
    }
    @Test
    void processLine_TranslationRequestForUnknownWord(){
        assertEquals(new Result(ResultCode.ALIEN_WORD_UNKNOWN, "test1"),
                lineProcessController.processLine("how much is test1"));
    }

    // PRICE_ANSWER,
    @Test
    void processLine_PriceRequestWithValidNumbers(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addWord("test2", 'M');
            mainController.addMaterial("Iron", 1f);
        } catch(MaterialPriceException | WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.PRICE_ANSWER, 1005f, "test2 test1 Iron"),
                lineProcessController.processLine("how many credits is test2 test1 iron"));
    }

    @Test
    void processLine_PriceRequestForUnknownWord(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addMaterial("Iron", 1f);
        } catch(MaterialPriceException | WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.ALIEN_WORD_UNKNOWN, "test2"),
                lineProcessController.processLine("how many credits is test1 test2 iron"));
    }
    @Test
    void processLine_PriceRequestForUnknownMaterial(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addWord("test2", 'M');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.MATERIAL_UNKNOWN),
                lineProcessController.processLine("how many credits is test2 test1 copper"));
    }

    @Test
    void processLine_PriceReqeustForInvalidRomanAmount(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addWord("test2", 'M');
            mainController.addMaterial("Iron", 1f);
        } catch(MaterialPriceException | WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.ROMAN_NUMERAL_INVALID),
                lineProcessController.processLine("how many credits is test1 test2 iron"));
    }

    // LIST_MATERIALS,
    @Test
    void processLine_ListMaterialsOnEmptySet(){
        assertEquals(new Result(ResultCode.LIST_MATERIALS, ""),
                lineProcessController.processLine("list materials"));
    }

    @Test
    void processLine_ListMaterialsOnNonEmptySet(){
        try {
            mainController.addMaterial("test1", 1f);
            mainController.addMaterial("test2", 2f);
        } catch(MaterialPriceException e){
            e.printStackTrace();
        }
        String wantedAnswer = "test2\t--\t2.0\ntest1\t--\t1.0";
        assertEquals(new Result(ResultCode.LIST_MATERIALS, wantedAnswer),
                lineProcessController.processLine("list materials"));
    }

    // LIST_WORDS,
    @Test
    void processLine_ListWordsOnEmptySet(){
        assertEquals(new Result(ResultCode.LIST_WORDS, ""),
                lineProcessController.processLine("list words"));
    }

    @Test
    void processLine_ListWordsOnNonEmptySet(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addWord("test2", 'X');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        String wantedAnswer = "test2\t--\tX\ntest1\t--\tV";
        assertEquals(new Result(ResultCode.LIST_WORDS, wantedAnswer),
                lineProcessController.processLine("list words"));
    }

    // WORD_ALREADY_KNOWN,
    @Test
    void processLine_addSameWordTwiceWithDifferentMeaning(){
        try {
            mainController.addWord("test1", 'V');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.WORD_ALREADY_KNOWN, "test1"),
                lineProcessController.processLine("test1 is X"));
    }

    @Test
    void processLine_addSameWordTwiceWithSameMeaning(){
        try {
            mainController.addWord("test1", 'V');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.OK),
                lineProcessController.processLine("test1 is V"));
    }

    // MATERIAL_ALREADY_KNOWN,
    @Test
    void processLine_addSameMaterialTwiceWithDifferentPrice(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addMaterial("Iron", 1f);
        } catch(MaterialPriceException | WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.MATERIAL_ALREADY_KNOWN, 1f, "iron"),
                lineProcessController.processLine("test1 iron is 6 credits"));
    }

    @Test
    void processLine_addSameMaterialTwiceWithSamePrice(){
        try {
            mainController.addWord("test1", 'V');
            mainController.addMaterial("Iron", 1f);
        } catch(MaterialPriceException | WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.OK),
                lineProcessController.processLine("test1 iron is 5 credits"));
    }


    // ALIEN_WORD_UNKNOWN,
    @Test
    void processLine_UnknownAlienWord(){
        assertEquals(new Result(ResultCode.ALIEN_WORD_UNKNOWN, "unknown"),
                lineProcessController.processLine("how much is unknown"));
    }

    // DIDNT_UNDERSTAND_LINE,
    @Test
    void processLine_DidntUnderstandLine_nonMatchingLine(){
        assertEquals(new Result(ResultCode.DIDNT_UNDERSTAND_LINE),
                lineProcessController.processLine("asd8f9asudf"));
    }

    @Test
    void processLine_DidntUnderstandLine_EmptyLine(){
        assertEquals(new Result(ResultCode.DIDNT_UNDERSTAND_LINE),
                lineProcessController.processLine(""));
    }

    // ROMAN_NUMERAL_INVALID,
    @Test
    void processLine_RomanNumeralInvalid(){
        try {
            mainController.addWord("test1", 'D');
            mainController.addWord("test2", 'M');
        } catch(WordAlreadyExistsException e){
            e.printStackTrace();
        }
        assertEquals(new Result(ResultCode.ROMAN_NUMERAL_INVALID),
                lineProcessController.processLine("how much is test1 test2"));
    }

    // HELP
    @Test
    void processLine_Help() {
        assertEquals(new Result(ResultCode.HELP),
                lineProcessController.processLine("help"));
    }

    @Test
    void processLine_HelpTrailingSpaces(){
        assertEquals(new Result(ResultCode.HELP),
                lineProcessController.processLine("help   "));
    }

    @Test
    void processLine_Exit(){
        assertEquals(new Result(ResultCode.EXIT),
                lineProcessController.processLine("exit"));
    }

}