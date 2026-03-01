package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropHuckleberry extends CropFood {

    public CropHuckleberry() {
        super("huckleberry", new Color(0x0E0116), new Color(0x2A1736));

        this.addDrop(CropsNHItemList.huckleBerry.get(1), 100_00);

        this.addAlternateSeed("seedHuckleberry");
        this.addAlternateSeed("cropHuckleberry");
        // mostly appears along the appalachia and neighbouring flats in Canada and the US
        this.addLikedBiomes(
            BiomeDictionary.Type.MOUNTAIN,
            BiomeDictionary.Type.HILLS,
            BiomeDictionary.Type.PLAINS,
            BiomeDictionary.Type.COLD);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
