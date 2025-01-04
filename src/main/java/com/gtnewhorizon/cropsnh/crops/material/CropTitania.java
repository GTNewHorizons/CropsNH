package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;

public class CropTitania extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropTitania() {
        super("titania", new Color(0x815E8D), new Color(0xDCA0F0));
        this.addDrop(CropsNHItemList.titaniaLeaf.get(1), 100_00);
        this.addBlockUnderRequirement("titanium");
    }

    @Override
    public int getTier() {
        return 9;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
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
