package com.itemis.mgttg.model;
import java.util.HashMap;
import java.util.Objects;

import com.itemis.mgttg.exceptions.MaterialPriceException;
//TODO comment for doc
public class Materials {

    HashMap<String, Float> materials;

    public Materials(){
        materials = new HashMap<>();
    }

    /**
     * Adds a new Mineral to internal HashMap
     * @param mineral Mineral to add
     * @param value Value of the Mineral
     * @throws MaterialPriceException if the Mineral is already known with a different value
     */
    public void addMaterial(String mineral, Float value) throws MaterialPriceException {
        if(materials.containsKey(mineral) && !Objects.equals(materials.get(mineral), value)){
            throw new MaterialPriceException("The Mineral " + mineral + " already seems to have a different Value of "+value+".", mineral, value);
        }
        materials.put(mineral, value);
    }

    /**
     * Removes Mineral from the internal HashMap
     * @param mineral Mineral to remove
     */
    public void removeMaterial(String mineral){
        materials.remove(mineral);
    }

    /**
     * Updates Price of a Mineral
     * @param mineral Mineral to Update
     * @param value New Price
     */
    public void updatePrice(String mineral, float value){
        materials.put(mineral, value);
    }

    /**
     * Gets Price of a Mineral
     * @param mineral
     * @return Price as float, null if the mineral is unknown
     */
    public float getPrice(String mineral){
        return materials.get(mineral);
    }
}
