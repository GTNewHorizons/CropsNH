package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropGodOfThunder extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropGodOfThunder() {
        super("godOfThunder", new Color(0x001E00), new Color(0x0C290C));

        this.addDrop(CropsNHItemList.thunderLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("thorium");

        this.addDuplicationCatalyst("dustThorium", 1);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());
        // these plants are the result of one of Zeus's drunken rampages
        this.addLikedBiomes(BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPARSE);
    }

    @Override
    public int getTier() {
        return 9;
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
        return 3600;
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
