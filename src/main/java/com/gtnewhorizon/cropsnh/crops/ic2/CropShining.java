package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;

public class CropShining extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropShining() {
        // TODO: SEE IF WE KEEP PLUMBISCUS SINCE PLUMBILIA EXISTS
        super("shining", new Color(0xB3B3D0), new Color(0xD0D0F1));
        this.addDrop(Materials.Silver.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("silver"));
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
    public float getDropChance() {
        return super.getDropChance();
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
