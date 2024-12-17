package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;

public class CropCyprium extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropCyprium() {
        // TODO: CHECK IF WE WANT TO ADD A COPPER LEAF
        super("cyprium", new Color(0xB26003), new Color(0xD77106));
        this.addDrop(Materials.Copper.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("copper"));
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
