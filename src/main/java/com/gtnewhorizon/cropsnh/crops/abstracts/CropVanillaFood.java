package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.farming.growthrequirements.MinLightLevelRequirement;

public abstract class CropVanillaFood extends NHCropCard {

    public CropVanillaFood(String id, Color color1, Color color2) {
        super(id, color1, color2);
        this.addGrowthRequirements(new MinLightLevelRequirement(12));
    }
}
