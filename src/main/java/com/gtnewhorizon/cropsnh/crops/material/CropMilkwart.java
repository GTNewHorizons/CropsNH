package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

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
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

}
