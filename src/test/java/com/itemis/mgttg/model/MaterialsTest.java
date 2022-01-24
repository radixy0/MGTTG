package com.itemis.mgttg.model;

import com.itemis.mgttg.exceptions.MaterialPriceException;
import com.itemis.mgttg.exceptions.WordAlreadyExistsException;
import com.itemis.mgttg.tools.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void getAllMaterials_AllPairsShouldBeEqual() {
        Materials mats = new Materials();
        Pair<String, Float> p1 = new Pair("test1", 1f);
        Pair<String, Float> p2 = new Pair("test2", 2f);
        Pair<String, Float> p3 = new Pair("test3", 3f);
        try{
            mats.addMaterial(p1.getLeft(), p1.getRight());
            mats.addMaterial(p2.getLeft(), p2.getRight());
            mats.addMaterial(p3.getLeft(), p3.getRight());
        } catch (MaterialPriceException e){
            e.printStackTrace();
        }
        ArrayList<Pair<String, Float>> toTest = mats.getAllMaterials();
        for(Pair<String, Float> p : toTest){
            boolean equalsToSomething = false;
            if(p.equals(p1)){equalsToSomething=true;}
            if(p.equals(p2)){equalsToSomething=true;}
            if(p.equals(p3)){equalsToSomething=true;}
            assertTrue(equalsToSomething);
        }
    }
}