package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import gregtech.api.enums.Materials;

public class CropThaumiumOreBerry extends CropOreBerry {

    public CropThaumiumOreBerry() {
        super("thaumium", new Color(0x67458A), new Color(0x9664C8));
        this.addDrop(Materials.Thaumium.getNuggets(1), 100_00);
        this.addBlockUnderRequirement("thaumium");
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 3000;
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
