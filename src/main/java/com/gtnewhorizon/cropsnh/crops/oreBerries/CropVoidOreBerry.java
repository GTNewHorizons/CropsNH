package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import gregtech.api.enums.Materials;

public class CropVoidOreBerry extends CropOreBerry {

    public CropVoidOreBerry() {
        super("void", new Color(0x130426), new Color(0x1C0639));
        this.addDrop(Materials.Void.getNuggets(1), 100_00);
        this.addAlternateSeeds(thaumcraft.api.ItemApi.getItem("itemResource", 17));
        this.addBlockUnderRequirement("void");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 4500;
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
