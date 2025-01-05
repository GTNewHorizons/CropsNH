package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import gregtech.api.enums.Materials;

public class CropCoffee extends CropFood {

    public CropCoffee() {
        super("coffee", new Color(0x3D1E00), new Color(0x964F05));
        this.addDrop(Materials.Coffee.getDust(1), 10_000);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
    }

    @Override
    public String getCreator() {
        return "Snoochy";
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 2800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
