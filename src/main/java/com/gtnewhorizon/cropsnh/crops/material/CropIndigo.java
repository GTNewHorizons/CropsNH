package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropIndigo extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropIndigo() {
        super("indigo", new Color(0x6446BD), new Color(0xA285EF));

        this.addDrop(CropsNHItemList.indigoBlossom.get(1), 100_00);

        this.addAlternateSeed(CropsNHItemList.indigoBlossom.get(1));

        // mostly rain forest.
        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH);
    }

    @Override
    public String getCreator() {
        return "Eloraam";
    }

    @Override
    public int getTier() {
        return 2;
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
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
