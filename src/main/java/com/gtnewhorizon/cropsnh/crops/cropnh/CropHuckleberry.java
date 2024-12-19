package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropHuckleberry extends NHCropCard {

    public CropHuckleberry() {
        super("huckleberry", new Color(0x0E0116), new Color(0x2A1736));
        this.addDrop(OreDictHelper.getCopiedOreStack("cropHuckleberry", 1), 100_00);
        this.addAlternateSeed("seedHuckleberry");
        this.addAlternateSeed("cropHuckleberry");
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
        return 200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
