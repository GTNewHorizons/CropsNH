package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public class CropEndStoneLily extends CropBaseStoneLily {

    public CropEndStoneLily() {
        super("endStone", new Color(0xABA67E), new Color(0xDFD9A5));

        this.addDrop(Materials.Endstone.getDust(2), 100_00);

        this.addBlockUnderRequirement("endStone");

        this.addDuplicationCatalyst("dustEndstone", 1);

        this.addLikedBiomes(BiomeDictionary.Type.END, BiomeDictionary.Type.DRY, BiomeDictionary.Type.COLD);
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 850;
    }
}
