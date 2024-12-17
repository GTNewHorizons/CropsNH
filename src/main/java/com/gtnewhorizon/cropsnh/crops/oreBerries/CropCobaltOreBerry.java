package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import gregtech.api.enums.Materials;

public class CropCobaltOreBerry extends CropOreBerry {

    public CropCobaltOreBerry() {
        super("cobalt", new Color(0x3737AD), new Color(0x5050FA));
        this.addDrop(Materials.Cobalt.getNuggets(1), 100_00);
        this.addBlockUnderRequirement("cobalt");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }
}
