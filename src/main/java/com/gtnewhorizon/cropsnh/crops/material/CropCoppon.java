package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropCoppon extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropCoppon() {
        super("coppon", new Color(0xBE7000), new Color(0xD88000));
        this.addDrop(CropsNHItemList.copponFiber.get(1), 100_00);
        this.addBlockUnderRequirement("copper");
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
        return 1200;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

}
