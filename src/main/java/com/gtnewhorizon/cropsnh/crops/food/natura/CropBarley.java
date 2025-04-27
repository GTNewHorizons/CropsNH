package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropBarley extends CropFood {

    public CropBarley() {
        super("barley", new Color(0x285221), new Color(0x84C63D));
        this.addDrop(OreDictHelper.getCopiedOreStack("cropBarley", 1), 100_00);
        this.addAlternateSeed("seedBarley");
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 675;
    }

    @Override
    protected String getBaseTexturePath() {
        return "natura:barley_";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
