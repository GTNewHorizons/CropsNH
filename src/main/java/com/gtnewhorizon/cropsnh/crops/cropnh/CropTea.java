package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;

public class CropTea extends NHCropCard {

    public CropTea() {
        super("tea", new Color(0x7C8C58), new Color(0xD8C97D));
        this.addDrop(CropsNHItemList.teaLeaf.get(1), 100_00);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 2400;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
