package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public abstract class CropBaseStoneLily extends NHCropCard {

    private static ISoilList soilTypes = SoilRegistry.instance.get("stone");
    private final String materialName;

    public CropBaseStoneLily(String materialName, Color color1, Color color2) {
        super(materialName + "Lily", color1, color2);
        this.materialName = materialName;
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 1;
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
    protected String getBaseTexturePath() {
        return "cropsnh:crops/stoneLilies/" + this.materialName + "/";
    }
}
