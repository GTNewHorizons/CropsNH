package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.Materials;

public class CropNetherStoneLily extends CropBaseStoneLily {

    public CropNetherStoneLily() {
        super("netherStone", new Color(0x911717), new Color(0xC21F1F));

        this.addDrop(Materials.Netherrack.getDust(9), 100_00);

        this.addBlockUnderRequirement("netherrack");

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
    }
}
