package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public abstract class CropVanillaFlower extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropVanillaFlower(String id, Color color1, Color color2) {
        super(id, color1, color2);
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 600;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.FLOWER;
    }
}
