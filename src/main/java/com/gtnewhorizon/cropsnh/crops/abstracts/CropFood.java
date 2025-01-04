package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.farming.growthrequirements.MinLightLevelRequirement;

public abstract class CropFood extends NHCropCard {

    public CropFood(String id, Color color1, Color color2) {
        super(id, color1, color2);
        this.addGrowthRequirement(new MinLightLevelRequirement(9));
    }
}
