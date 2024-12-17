package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.ItemList;

public class CropAurelia extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropAurelia() {
        super("aurelia", new Color(0xABA600), new Color(0xEFE913));
        this.addDrop(ItemList.Crop_Drop_Aurelia.get(1L), 10_000);
        this.addGrowthRequirement(BlockUnderRequirement.get("gold"));
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getGrowthDuration() {
        return 3700;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
