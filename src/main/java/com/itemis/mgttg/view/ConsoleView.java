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

    /**
     * Creates a new ConsoleView with given output stream
     * @param out The output stream to use
     */
    public ConsoleView(PrintStream out){
        this.out=out;
        lineProcessController=new LineProcessController();
    }

    /**
     * Creates a new ConsoleView using System.out as output stream
     */
    public ConsoleView(){
        this.out=System.out;
        lineProcessController = new LineProcessController();
    }

    /**
     * Runs the ConsoleView, accepting user input line by line
     */
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

    /**
     * Reads and processes a file containing input lines, then stops
     * @param filename the file to read
     */
    public void run_fromFile(String filename){
        String[] lines = FileIOController.getLines(filename);
        if(lines == null){
            out.println("The specified file does not seem to exist");
            return;
        }
        for(String line : lines){
            Result result = lineProcessController.processLine(line);
            handleResult(result);
        }
    }

    /**
     * Handles the Result that comes back from a Controller, and outputs Results to output stream
     * @param result the Result object to handle
     */
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
                out.println("I already know the Word " + result.getMessage() + " with a different Value");
                out.println("You can remove the Word by using removeword WORD");
                break;
            case MATERIAL_ALREADY_KNOWN:
                out.println("I already know the Material "
                        + result.getMessage()
                        + " at a different Price of "
                        + cleanFloatIfNoDecimal(result.getAnswer()));
                out.println("You can remove the Material by using removematerial MATERIAL");
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

    /**
     * Outputs Itemis logo and a Greeting message to output stream
     */
    private void play_intro(){
        out.println(Constants.LOGO);
        out.println(Constants.INTRO);
    }

    /**
     * Outputs a goodbye-message to output stream
     */
    private void play_outro(){
        out.println(Constants.OUTRO);
    }

    /**
     * Makes Floats that have no decimals appear without .0 at the end
     * @param num the float to check
     * @return X if the float is of shape X.0, otherwise the whole float
     */
    private String cleanFloatIfNoDecimal(float num){
        String output = ""+num;
        if((int) num == num){
            output = ""+((int) num);
        } 
        return output;
    }
}
