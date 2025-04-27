package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import gregtech.api.enums.Materials;

public class CropArditeOreBerry extends CropOreBerry {

    public CropArditeOreBerry() {
        super("ardite", new Color(0xAD5A00), new Color(0xFA8100));
        this.addDrop(Materials.Ardite.getNuggets(1), 100_00);
        this.addBlockUnderRequirement("ardite");
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }
}
