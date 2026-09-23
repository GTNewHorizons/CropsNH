package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropCobaltOreBerry extends CropOreBerry {

    public CropCobaltOreBerry() {
        super("cobalt", new Color(0x3737AD), new Color(0x5050FA));

        this.addDrop(MaterialLibAPI.getStack(Materials.Cobalt, Shapes.nugget, 1), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.cobalt);

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
