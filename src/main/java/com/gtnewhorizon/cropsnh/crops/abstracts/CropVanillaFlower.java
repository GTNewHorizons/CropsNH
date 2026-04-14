package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;

public abstract class CropVanillaFlower extends NHCropCard {

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
        return CropsNHSoilTypes.dirtGrass;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }
}
