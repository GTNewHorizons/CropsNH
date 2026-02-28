package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropCanola extends NHCropCard {

    public CropCanola() {
        super("canola", new Color(0x627F40), new Color(0x6E8E48));

        this.addDrop(CropsNHItemList.canolaFlower.get(1), 100_00);

        // canada.jpg
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.PLAINS);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
