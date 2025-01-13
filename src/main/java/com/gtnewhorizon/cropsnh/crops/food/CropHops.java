package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropHops extends CropFood {

    public CropHops() {
        super("hops", new Color(0x6B5C0E), new Color(0x86BB00));
        this.addDrop(CropsNHItemList.hops.get(1), 100_00);
        this.addAlternateSeed(CropsNHItemList.hops.get(1));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
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
        return 7;
    }
}
