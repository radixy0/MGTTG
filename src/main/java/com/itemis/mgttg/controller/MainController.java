package com.itemis.mgttg.controller;
import com.itemis.mgttg.model.*;

public class MainController {
    Minerals minerals;
    Words words;
    private static MainController maincontroller_instance = null;
    public MainController(){
        minerals = new Minerals();
        words = new Words();
    }
    public static MainController getInstance(){
        if(maincontroller_instance == null){
            maincontroller_instance = new MainController();
        }
        return maincontroller_instance;
    }
    public int getMineralPrice(String mineral){
        return 0;
    }
    public Character getWordsRomanValue(String word){
        return null;
    }
    public void addMineral(){

    }
    public void addWord(){

    }
    public void updateMineral(){

    }
    public void updateWord(){

    }
    public void removeMineral(){

    }
    public void removeWord(){

    }
}
