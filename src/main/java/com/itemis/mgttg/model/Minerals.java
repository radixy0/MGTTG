package com.itemis.mgttg.model;
import java.util.HashMap;
import java.util.Objects;

import com.itemis.mgttg.exceptions.MineralPriceException;
//TODO comment for doc
public class Minerals {

    HashMap<String, Float> minerals;

    public Minerals(){
        minerals = new HashMap<>();
    }

    /**
     * Adds a new Mineral to internal HashMap
     * @param mineral Mineral to add
     * @param value Value of the Mineral
     * @throws MineralPriceException if the Mineral is already known with a different value
     */
    public void addMineral(String mineral, Float value) throws MineralPriceException{
        if(minerals.containsKey(mineral) && !Objects.equals(minerals.get(mineral), value)){
            throw new MineralPriceException("The Mineral " + mineral + " already seems to have a different Value of "+value+".", mineral, value);
        }
        minerals.put(mineral, value);
    }

    /**
     * Removes Mineral from the internal HashMap
     * @param mineral Mineral to remove
     */
    public void removeMineral(String mineral){
        minerals.remove(mineral);
    }

    /**
     * Updates Price of a Mineral
     * @param mineral Mineral to Update
     * @param value New Price
     */
    public void updatePrice(String mineral, float value){
        minerals.put(mineral, value);
    }

    /**
     * Gets Price of a Mineral
     * @param mineral
     * @return Price as float, null if the mineral is unknown
     */
    public float getPrice(String mineral){
        return minerals.get(mineral);
    }
}
