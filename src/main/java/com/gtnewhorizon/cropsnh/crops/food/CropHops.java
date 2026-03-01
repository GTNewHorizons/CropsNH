package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropHops extends CropFood {

    public CropHops() {
        super("hops", new Color(0x6B5C0E), new Color(0x86BB00));
        this.addDrop(CropsNHItemList.hops.get(1), 100_00);
        this.addAlternateSeed(CropsNHItemList.hops.get(1));
        // likes warm areas, growing most effectively around the 48th latitude
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
    }

    @Override
    public int getGrowthDuration() {
        return 2400;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getMaxGrowthStage() {
        return 6;
    }
}
