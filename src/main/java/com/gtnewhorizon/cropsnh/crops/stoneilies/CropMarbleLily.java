package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.Materials;

public class CropMarbleLily extends CropBaseStoneLily {

    public CropMarbleLily() {
        super("marble", new Color(0xC8C8C8), new Color(0xF0F0F0));

        this.addDrop(Materials.Marble.getDust(9), 100_00);

        this.addBlockUnderRequirement("marble");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
