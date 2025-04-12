package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropBobsYerUncleRanks extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropBobsYerUncleRanks() {
        super("bobsYerUncleRanks", new Color(0x009529), new Color(0x41F384));
        this.addDrop(CropsNHItemList.bobsYerUncleBerry.get(1), 100_00);
        this.addLikedBiomes(BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS);
        this.addBlockUnderRequirement("emerald");
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
