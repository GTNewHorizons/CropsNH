package com.gtnewhorizon.cropsnh.compatibility.ganysMods;

import net.minecraft.item.ItemSeeds;

import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlantGeneric;

public class CropPlantGanysSurface extends CropPlantGeneric {

    private final String name;

    protected CropPlantGanysSurface(ItemSeeds seed, String name) {
        super(seed);
        this.name = name;
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
        return "cropsnh_journal." + name;
    }
}
