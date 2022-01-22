package com.itemis.mgttg.exceptions;

public class MaterialPriceException extends Exception{
    String message;
    String mineral;
    float value;
    public MaterialPriceException(String message, String mineral, float value){
        super(message);
        this.message=message;
        this.mineral=mineral;
        this.value=value;
    }
}
