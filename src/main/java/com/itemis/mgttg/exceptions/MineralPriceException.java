package com.itemis.mgttg.exceptions;

public class MineralPriceException extends Exception{
    String message;
    String mineral;
    float value;
    public MineralPriceException(String message, String mineral, float value){
        super(message);
        this.message=message;
        this.mineral=mineral;
        this.value=value;
    }
}
