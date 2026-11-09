package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;

public class CropEndStoneLily extends CropBaseStoneLily {

    public CropEndStoneLily() {
        super("endStone", new Color(0xABA67E), new Color(0xDFD9A5));

        this.addDrop(MaterialLibAPI.getStack(Materials.Endstone, Shapes.dust, 2), 100_00);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.endStone);

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
