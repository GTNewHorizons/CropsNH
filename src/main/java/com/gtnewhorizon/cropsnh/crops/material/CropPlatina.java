package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropPlatina extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropPlatina() {
        super("platina", new Color(0xDADAAB), new Color(0xF7F7C7));
        this.addDrop(CropsNHItemList.platinaLeaf.get(1), 100_00);
        this.addBlockUnderRequirement("platinum");
    }

    @Override
    public int getTier() {
        return 11;
    }

    @Override
    public int getGrowthDuration() {
        return 6600;
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
