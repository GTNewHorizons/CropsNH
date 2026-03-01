package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.Materials;

public class CropStoneLily extends CropBaseStoneLily {

    public CropStoneLily() {
        super("stone", new Color(0x919191), new Color(0xCDCDCD));

        this.addDrop(Materials.Stone.getDust(9), 100_00);

        this.addBlockUnderRequirement("stone");

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }
}
