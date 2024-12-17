package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public abstract class CropMushroom extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("mushroom");

    public CropMushroom(String id, Color color1, Color color2) {
        super(id, color1, color2);
        this.addGrowthRequirement(new MaxLightLevelRequirement(9));
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }
}
