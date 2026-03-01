package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public class CropThaumiumOreBerry extends CropOreBerry {

    public CropThaumiumOreBerry() {
        super("thaumium", new Color(0x67458A), new Color(0x9664C8));

        this.addDrop(Materials.Thaumium.getNuggets(1), 100_00);

        this.addBlockUnderRequirement("thaumium");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetThaumium", 1);

        // eerie
        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 3000;
    }

}
