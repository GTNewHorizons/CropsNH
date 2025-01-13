package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import gregtech.api.enums.ItemList;

public class CropCucumber extends CropFood {

    public CropCucumber() {
        super("cucumber", new Color(0x13260D), new Color(0x259038));
        // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
        this.addDrop(ItemList.Crop_Drop_Cucumber.get(1L), 100_00);
        this.addAlternateSeed("seedCucumber");
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
