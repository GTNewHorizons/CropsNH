package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropTransformium extends NHCropCard {

    public CropTransformium() {
        super("transformium", new Color(0x2A0040), new Color(0x6D1566));

        this.addDrop(CropsNHItemList.uuaBerry.get(1), 90_00);
        this.addDrop(CropsNHItemList.uumBerry.get(1), 10_00);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());

        this.addLikedBiomes(BiomeDictionary.Type.END, BiomeDictionary.Type.COLD);
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getMinSeedBedTier() {
        return VoltageIndex.EV;
    }

    @Override
    public int getGrowthDuration() {
        return 7200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
