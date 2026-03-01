package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.Materials;

public class CropBlackGraniteLily extends CropBaseStoneLily {

    public CropBlackGraniteLily() {
        super("blackGranite", new Color(0, 0, 0), new Color(10, 10, 10));
        this.addDrop(Materials.GraniteBlack.getDust(9), 100_00);
        this.addBlockUnderRequirement("blackGranite");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
