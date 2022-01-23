package com.itemis.mgttg.model;

import com.itemis.mgttg.exceptions.MaterialPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialsTest {
    @Test
    void addMaterial_addValidMaterial() {
        Materials mats = new Materials();
        try {
            mats.addMaterial("Iron", 15f);
        }
        catch(Exception e){

        }
        assertEquals(15, mats.materials.get("Iron"));
    }

    @Test
    void addMaterial_addSameMaterialTwiceShouldThrowException(){
        Materials mats = new Materials();
        assertThrows(MaterialPriceException.class, () -> {
           mats.addMaterial("Iron", 15f);
           mats.addMaterial("Iron", 10f);
        });
    }

    @Test
    void removeMineral_addAndRemoveMaterialShouldBeEmptyAfter() {
        Materials mats = new Materials();
        try{
            mats.addMaterial("Iron", 15f);
        } catch (Exception e){

        }
        mats.removeMaterial("Iron");
        assertEquals(true, mats.materials.isEmpty());

    }

    @Test
    void removeMineral_removeMaterialFromEmptySetShouldBeEmptyAfter(){
        Materials mats = new Materials();
        mats.removeMaterial("Iron");
        assertEquals(true, mats.materials.isEmpty());
    }

    @Test
    void updatePrice_ShouldEqualToNewPrice() {
        Materials mats = new Materials();
        try{
            mats.addMaterial("Iron", 15f);
            mats.updatePrice("Iron", 10f);
        } catch (Exception e){

        }
        assertEquals(10f, mats.materials.get("Iron"));
    }

    @Test
    void getPrice() {
        Materials mats = new Materials();
        try{
            mats.addMaterial("Iron", 15f);
        } catch (Exception e){

        }
        assertEquals(15f, mats.getPrice("Iron"));
    }

    @Test
    void clearMaterials_shouldBeEmptyAfter() {
        Materials mats = new Materials();
        try{
            mats.addMaterial("Iron", 5f);
        } catch(Exception e){
            e.printStackTrace();
        }
        mats.clearMaterials();
        assertEquals(true, mats.materials.isEmpty());
    }
}