package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;

public class CropPyrolusium extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropPyrolusium() {
        super("pyrolusium", new Color(0xB51900), new Color(0xCF1E11));
        this.addDrop(CropsNHItemList.pyrolusiumLeaf.get(1), 100_00);
        this.addBlockUnderRequirement("manganese");
    }

    @Override
    public String getFlavourText() {
        return ConfigurationHandler.enableEasterEggs ? "cropsnh_crops.pyrolusium.flavour" : super.getFlavourText();
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getGrowthDuration() {
        return 2400;
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
