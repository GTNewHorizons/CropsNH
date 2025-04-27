package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropGodOfThunder extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropGodOfThunder() {
        super("godOfThunder", new Color(0x001E00), new Color(0x0C290C));
        this.addDrop(CropsNHItemList.thunderLeaf.get(1), 100_00);
        this.addBlockUnderRequirement("thorium");
    }

    @Override
    public int getTier() {
        return 9;
    }

    @Override
    public int getGrowthDuration() {
        return 3600;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
