package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropPlumbilia extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropPlumbilia() {
        super("plumbilia", new Color(0x503950), new Color(0x6D4E6D));

        this.addDrop(CropsNHItemList.plumbiliaLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("lead");

        this.addDuplicationCatalyst("dustLead", 1);
        // I was going to choose plums as a basis, but the stuff will literally grow everywhere
        // it's actually one of the first domesticated crops in human history.
        this.addLikedBiomes(BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.PLAINS);
    }

    @Override
    public String getCreator() {
        return "KingLemming";
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
