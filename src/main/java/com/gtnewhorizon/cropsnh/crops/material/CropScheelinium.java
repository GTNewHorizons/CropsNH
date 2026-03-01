package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MachineOnlyGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropScheelinium extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropScheelinium() {
        super("scheelinium", new Color(0x313CA3), new Color(0x232C9E));

        this.addDrop(CropsNHItemList.scheeliniumLeaf.get(1), 100_00);

        this.addBlockUnderRequirement("tungsten");

        this.addDuplicationCatalyst("dustTungsten", 1);
        this.addDuplicationCatalyst("dustTungstate", 1);
        this.addDuplicationCatalyst("dustScheelite", 1);

        this.addGrowthRequirement(new MachineOnlyGrowthRequirement());

        this.addLikedBiomes(BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.COLD);
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
        return 2400;
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
