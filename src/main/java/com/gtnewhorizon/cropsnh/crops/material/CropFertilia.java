package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.enums.materials.Materials;
import gregtech.api.util.GTOreDictUnificator;

public class CropFertilia extends NHCropCard {

    public CropFertilia() {
        super("fertilia", new Color(0x554516), new Color(0x6E6038));

        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1), 62_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1), 12_50);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1), 12_50);
        this.addDrop(CropsNHItemList.fertilizer.get(1), 12_50);

        this.addDuplicationCatalyst(CropsNHItemList.fertilizer.get(1));

        this.addLikedBiomes(BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET, BiomeDictionary.Type.HOT);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
