package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropReactoria extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("stone");

    public CropReactoria() {
        super("reactoria", new Color(0x565301), new Color(0x989309));
        this.addDrop(CropsNHItemList.reactoriaLeaf.get(1), 75_00);
        this.addDrop(CropsNHItemList.reactoriaStem.get(1), 25_00);
        this.addBlockUnderRequirement("uranium");
    }

    @Override
    public int getTier() {
        return 12;
    }

    @Override
    public int getGrowthDuration() {
        return 4800;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
