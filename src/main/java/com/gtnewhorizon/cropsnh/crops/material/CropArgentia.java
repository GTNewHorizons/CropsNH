package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropArgentia extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropArgentia() {
        super("argentia", new Color(0xB2BEC9), new Color(0xFDFEFE));

        this.addDrop(CropsNHItemList.argentiaLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("silver");

        this.addDuplicationCatalyst("dustSilver", 1);

        // argentina
        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "Eloraam";
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
        return 1400;
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
