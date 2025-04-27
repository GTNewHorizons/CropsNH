package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

import gregtech.api.enums.ItemList;

public class CropTomato extends CropFood {

    public CropTomato() {
        super("tomato", new Color(0x91101F), new Color(0xE3321F));
        // TODO: MOVE TOMATOS TO CROPS NH
        this.addDrop(ItemList.Crop_Drop_Tomato.get(1), 75_00);
        this.addDrop(ItemList.Crop_Drop_MTomato.get(1), 25_00);
        this.addAlternateSeed("seedTomato");
        this.addAlternateSeed("cropTomato");
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
