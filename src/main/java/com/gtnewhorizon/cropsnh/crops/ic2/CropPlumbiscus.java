package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;

public class CropPlumbiscus extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropPlumbiscus() {
        // TODO: SEE IF WE KEEP PLUMBISCUS SINCE PLUMBILIA EXISTS
        super("plumbiscus", new Color(0x523B52), new Color(0x6D4E6D));
        this.addDrop(Materials.Lead.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("lead"));
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
