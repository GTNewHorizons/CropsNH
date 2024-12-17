package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.Materials;

public class CropStagnium extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropStagnium() {
        // TODO: SEE IF WE KEEP STAGNIUM SINCE TINE EXISTS
        super("stagnium", new Color(0x6C6C6C), new Color(0x7F7F7F));
        this.addDrop(Materials.Tin.getDustTiny(1), 100_00);
        this.addGrowthRequirement(BlockUnderRequirement.get("tin"));
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
