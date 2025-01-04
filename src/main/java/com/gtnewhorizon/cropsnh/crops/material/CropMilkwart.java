package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;

public class CropMilkwart extends NHCropCard {

    public CropMilkwart() {
        super("milkwart", new Color(0xA8A8A8), new Color(0xECECEC));
        this.addDrop(CropsNHItemList.milkwart.get(1), 100_00);
    }

    @Override
    public String getCreator() {
        return "Mr. Brain";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getGrowthDuration() {
        return 2400;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

}
