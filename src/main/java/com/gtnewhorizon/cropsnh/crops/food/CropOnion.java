package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

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
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
