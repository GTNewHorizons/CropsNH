package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.Materials;

public class CropBasaltLily extends CropBaseStoneLily {

    public CropBasaltLily() {
        super("basalt", new Color(0x080808), new Color(0x181818));

        this.addDrop(Materials.Basalt.getDust(9), 100_00);

        this.addBlockUnderRequirement("basalt");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
