package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.VoltageIndex;

public class CropKnightmetalBerry extends CropOreBerry {

    public CropKnightmetalBerry() {
        super("knightmetal", new Color(0x90A68A), new Color(0xD2F0C8));

        this.addDrop(CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.armorShards", 4, 0), 100_00);

        this.addBlockUnderRequirement("knightmetal");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetKnightmetal", 1);

        this.addLikedBiomes(BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DEAD);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 5500;
    }

}
