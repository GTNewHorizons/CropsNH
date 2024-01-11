package com.gtnewhorizon.cropsnh.compatibility.harderwildlife;

import net.minecraft.item.ItemSeeds;

import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlantGeneric;

public class CropPlantHarderWildLife extends CropPlantGeneric {

    private final String name;

    public CropPlantHarderWildLife(ItemSeeds seed, String name) {
        super(seed);
        this.name = name;
    }

    @Override
    protected boolean modSpecificFruits() {
        return false;
    }

    @Override
    public int transformMeta(int growthStage) {
        return growthStage;
    }

    @Override
    public boolean renderAsFlower() {
        return false;
    }

    @Override
    public String getInformation() {
        return "cropsnh_journal.harderWildLife_" + name;
    }
}
