package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Materials;

public class CropCoffee extends NHCropCard {

    public CropCoffee() {
        super("coffee", new Color(0x3D1E00), new Color(0x964F05));
        this.addDrop(Materials.Coffee.getDust(1), 10_000);
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
