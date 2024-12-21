package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropStrawberry extends NHCropCard {

    public CropStrawberry() {
        super("strawberry", new Color(0x8F0205), new Color(0xCE0609));
        this.addDrop(OreDictHelper.getCopiedOreStack("cropStrawberry", 1), 100_00);
        this.addAlternateSeed("seedStrawberry");
        this.addAlternateSeed("cropStrawberry");
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
