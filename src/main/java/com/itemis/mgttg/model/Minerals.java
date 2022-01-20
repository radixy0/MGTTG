package com.itemis.mgttg.model;
import java.util.HashMap;
import com.itemis.mgttg.exceptions.MineralPriceException;
//TODO comment for doc
public class Minerals {
    HashMap<String, Float> minerals;
    public Minerals(){
        minerals = new HashMap<>();
    }
    public void add(String mineral, Float value) throws MineralPriceException{
        if(minerals.containsKey(mineral)){
            throw new MineralPriceException("The Mineral " + mineral + " already seems to have a different Value of "+value+".", mineral, value);
        }
        minerals.put(mineral, value);
    }
    public void remove(String mineral){
        minerals.remove(mineral);
    }
    public void updatePrice(String mineral, float value){
        minerals.put(mineral, value);
    }
    public float getPrice(String mineral){
        return minerals.get(mineral);
    }
}
