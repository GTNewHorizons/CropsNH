package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.util.GTOreDictUnificator;

public class CropArditeOreBerry extends CropOreBerry {

    public CropArditeOreBerry() {
        super("ardite", new Color(0xAD5A00), new Color(0xFA8100));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.nugget, Materials.Ardite, 1), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.ardite);

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
