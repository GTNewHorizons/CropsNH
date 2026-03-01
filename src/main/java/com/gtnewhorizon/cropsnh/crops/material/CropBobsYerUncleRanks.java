package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import gregtech.api.enums.VoltageIndex;

public class CropBobsYerUncleRanks extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropBobsYerUncleRanks() {
        super("bobsYerUncleRanks", new Color(0x009529), new Color(0x41F384));

        this.addDrop(CropsNHItemList.bobsYerUncleBerry.get(1), 100_00);

        this.addBlockUnderRequirement("emerald");

        this.addDuplicationCatalyst("gemEmerald", 1);
        this.addDuplicationCatalyst("dustEmerald", 1);

        // emerald generation
        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
    }

    @Override
    public String getCreator() {
        return "GenerikB";
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
        return 3000;
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
