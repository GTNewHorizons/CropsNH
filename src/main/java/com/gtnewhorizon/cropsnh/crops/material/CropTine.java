package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropTine extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropTine() {
        super("tine", new Color(0x7D7D7D), new Color(0xACACAC));

        this.addDrop(CropsNHItemList.tineTwig.get(1), 100_00);

        this.addBlockUnderRequirement("tin");

        this.addDuplicationCatalyst("dustTin", 1);
        this.addDuplicationCatalyst("dustCassiterite", 1);
        this.addDuplicationCatalyst("dustCassiteriteSand", 1);

        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }

    @Override
    public String getCreator() {
        return "Gregorius Techneticies";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
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
