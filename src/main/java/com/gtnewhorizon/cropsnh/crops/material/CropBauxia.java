package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropBauxia extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropBauxia() {
        super("bauxia", new Color(0x460D00), new Color(0xC88E76));

        this.addDrop(CropsNHItemList.bauxiaLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("aluminiumBauxite");

        this.addDuplicationCatalyst("dustAluminium", 1);
        this.addDuplicationCatalyst("dustBauxite", 2);

        // tundra
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY);
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
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
