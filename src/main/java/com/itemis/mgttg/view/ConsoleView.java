package com.itemis.mgttg.view;

import com.itemis.mgttg.controller.FileIOController;
import com.itemis.mgttg.controller.LineProcessController;
import com.itemis.mgttg.controller.Result;

import java.io.Console;
import java.io.PrintStream;

public class ConsoleView {
    PrintStream out;
    LineProcessController lineProcessController;
    boolean finished;

    public ConsoleView(PrintStream out){
        this.out=out;
        lineProcessController=new LineProcessController();
    }
    public ConsoleView(){
        this.out=System.out;
        lineProcessController = new LineProcessController();
    }
    public void run(){
        Console console = System.console();
        play_intro();
        finished=false;
        while(!finished){
            String in = console.readLine("> ");
            if(in.isBlank()){continue;}
            Result result = lineProcessController.processLine(in);
            handleResult(result);
        }
        play_outro();
    }

    private void handleResult(Result result){
        switch(result.getResultCode()){
            case OK:
                break;
            case FROM_FILE:
                String[] lines = FileIOController.getLines(result.getMessage());
                if(lines == null){
                    System.out.println("The specified file does not seem to exist"); break;
                }
                for(String line : lines){
                    if(line.isBlank()){continue;}
                    handleResult(lineProcessController.processLine(line));
                }
                break;
            case TRANSLATION_ANSWER:
                out.println(result.getMessage() + " is " + cleanFloatIfNoDecimal(result.getAnswer()));
                break;
            case PRICE_ANSWER:
                out.println(result.getMessage() + " is " + cleanFloatIfNoDecimal(result.getAnswer()) + " Credits");
                break;
            case LIST_MATERIALS:
                out.println("Here are all the Materials that i know the price of:\n" + result.getMessage());
                break;
            case LIST_WORDS:
                out.println("Here are all the Words I know yet:\n" + result.getMessage());
                break;
            case WORD_ALREADY_KNOWN:
                out.println("I already know the Word " + result.getMessage() + "with a different Value");
                break;
            case MATERIAL_ALREADY_KNOWN:
                out.println("I already know the Material "
                        + result.getMessage()
                        + " at a different Price of "
                        + cleanFloatIfNoDecimal(result.getAnswer()));
                break;
            case ALIEN_WORD_UNKNOWN:
                out.println("Unknown Word: " + result.getMessage());
                break;
            case MATERIAL_UNKNOWN:
                out.println("Unknown Material: " + result.getMessage());
                break;
            case DIDNT_UNDERSTAND_LINE:
                out.println(Constants.INVALID_INPUT);
                break;
            case ROMAN_NUMERAL_INVALID:
                out.println("The entered words do not seem to be a valid roman numeral");
                break;
            case HELP:
                out.println(Constants.HELP);
                break;
            case EASTEREGG:
                out.println(result.getMessage());
                break;
            case EXIT:
                finished=true;
                break;
        }
    }

    private void play_intro(){
        out.println(Constants.LOGO);
        out.println(Constants.INTRO);
    }
    private void play_outro(){
        out.println(Constants.OUTRO);
    }
    private String cleanFloatIfNoDecimal(float num){
        String output = ""+num;
        if((int) num == num){
            output = ""+((int) num);
        } 
        return output;
    }
}
