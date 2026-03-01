package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropPlatina extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropPlatina() {
        super("platina", new Color(0xDADAAB), new Color(0xF7F7C7));

        this.addDrop(CropsNHItemList.platinaLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("platinum");

        this.addDuplicationCatalyst("dustPlatinum", 1);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());
        // stolen from bees
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY);
    }

    @Override
    public int getTier() {
        return 11;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.HV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.HV;
    }

    @Override
    public int getGrowthDuration() {
        return 6600;
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
