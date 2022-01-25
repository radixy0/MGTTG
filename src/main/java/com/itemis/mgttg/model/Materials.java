package com.itemis.mgttg.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.itemis.mgttg.exceptions.MaterialPriceException;
import com.itemis.mgttg.tools.Pair;

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
    public Float getPrice(String material){
        return materials.get(material);
    }

    /**
     * Clears list of Materials
     */
    public void clearMaterials(){
        materials.clear();
    }

    /**
     * Gets a List of all known Materials and their Price
     * @return An ArrayList of Pairs of <String, Float>
     */
    public ArrayList<Pair<String, Float>> getAllMaterials(){
        ArrayList<Pair<String, Float>> output = new ArrayList<>();
        materials.forEach((k,v) -> {
            output.add(new Pair<String, Float>(k,v));
        });
        return output;
    }
}
