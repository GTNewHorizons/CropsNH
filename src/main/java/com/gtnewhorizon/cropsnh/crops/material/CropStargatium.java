package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public class CropStargatium extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropStargatium() {
        super("stargatium", new Color(0x333333), new Color(0x555555));

        this.addDrop(CropsNHItemList.stargatiumLeaf.get(1), 75_00);
        this.addDrop(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 1L), 25_00);

        this.addBlockUnderRequirement("naquadah");

        this.addDuplicationCatalyst("dustNaquadah", 1);
        this.addDuplicationCatalyst("dustNaquadahOxideMixture", 1);
        this.addDuplicationCatalyst("dustNaquadria", 1);
        this.addDuplicationCatalyst("dustNaquadriaOxideMixture", 1);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());
        // 404 not found
        this.addLikedBiomes(BiomeDictionary.Type.LUSH, BiomeDictionary.Type.WASTELAND);
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.IV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.IV;
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getGrowthDuration() {
        return 7200;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
