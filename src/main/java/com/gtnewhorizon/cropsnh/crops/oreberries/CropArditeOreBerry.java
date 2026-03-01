package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public class CropArditeOreBerry extends CropOreBerry {

    public CropArditeOreBerry() {
        super("ardite", new Color(0xAD5A00), new Color(0xFA8100));

        this.addDrop(Materials.Ardite.getNuggets(6), 100_00);

        this.addBlockUnderRequirement("ardite");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetArdite", 1);

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }
}
