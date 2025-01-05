package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;

import gregtech.api.enums.Materials;

public class CropFerru extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropFerru() {
        super("ferru", new Color(0xA1A1A1), new Color(0xE6E6E6));
        this.addDrop(Materials.Copper.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("iron"));
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public float getDropChance() {
        return super.getDropChance() / 2.0f;
    }

    @Override
    public int getGrowthDuration() {
        return 2800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
