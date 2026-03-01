package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import gregtech.api.enums.Materials;
import gregtech.api.enums.VoltageIndex;

public class CropCobaltOreBerry extends CropOreBerry {

    public CropCobaltOreBerry() {
        super("cobalt", new Color(0x3737AD), new Color(0x5050FA));

        this.addDrop(Materials.Cobalt.getNuggets(1), 100_00);

        this.addBlockUnderRequirement("cobalt");

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addDuplicationCatalyst("nuggetCobalt", 1);

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.DRY);
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
