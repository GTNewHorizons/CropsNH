package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.ItemList;

public class CropHops extends NHCropCard {

    public CropHops() {
        super("hops", new Color(0x6B5C0E), new Color(0x86BB00));
        this.addDrop(ItemList.IC2_Hops.get(1L), 100_00);
        this.addAlternateSeed(ItemList.IC2_Hops.get(1L));
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
    }

    @Override
    public int getGrowthDuration() {
        return 2400;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getMaxGrowthStage() {
        return 7;
    }
}
