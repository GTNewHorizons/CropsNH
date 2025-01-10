package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import gregtech.api.enums.ItemList;

public class CropOnion extends CropFood {

    public CropOnion() {
        super("onion", new Color(0x4E1B3F), new Color(0x753C64));
        // TODO: MOVE ONION ITEM TO CROPS NH
        this.addDrop(ItemList.Crop_Drop_Onion.get(1), 100_00);
        this.addAlternateSeed("seedOnion");
        this.addAlternateSeed("cropOnion");
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
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
