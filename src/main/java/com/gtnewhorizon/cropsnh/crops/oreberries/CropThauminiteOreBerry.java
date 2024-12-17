package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropThauminiteOreBerry extends CropOreBerry {

    public CropThauminiteOreBerry() {
        super("thauminite", new Color(0x515A9C), new Color(0x7581E0));
        this.addDrop(OreDictHelper.getCopiedOreStack("nuggetThauminite", 1), 100_00);
        this.addBlockUnderRequirement("thauminite");
        this.addGrowthRequirement(new MaxLightLevelRequirement(10));
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
