package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public abstract class CropOreBerry extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    private final String materialName;

    public CropOreBerry(String materialName, Color color1, Color color2) {
        super(materialName + "OreBerry", color1, color2);
        this.materialName = materialName;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.oreberry;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 6000;
    }

    @Override
    public float getEntityDamage() {
        return 1.0F;
    }

    @Override
    protected String getBaseTexturePath() {
        return "cropsnh:crops/oreBerries/" + this.materialName + "/";
    }
}
