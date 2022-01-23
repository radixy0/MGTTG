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
     * Adds a new Material to internal HashMap
     * @param material Material to add
     * @param value Value of the Material
     * @throws MaterialPriceException if the Material is already known with a different value
     */
    public void addMaterial(String material, Float value) throws MaterialPriceException {
        if(materials.containsKey(material) && !Objects.equals(materials.get(material), value)){
            throw new MaterialPriceException("The Material " + material + " already seems to have a different Value of "+value+".", material, value);
        }
        materials.put(material, value);
    }

    /**
     * Removes Material from the internal HashMap
     * @param material Material to remove
     */
    public void removeMaterial(String material){
        materials.remove(material);
    }

    /**
     * Updates Price of a Material
     * @param material Material to Update
     * @param value New Price
     */
    public void updatePrice(String material, float value){
        materials.put(material, value);
    }

    /**
     * Gets Price of a Material
     * @param material
     * @return Price as float, null if the material is unknown
     */
    public float getPrice(String material){
        return materials.get(material);
    }
}
