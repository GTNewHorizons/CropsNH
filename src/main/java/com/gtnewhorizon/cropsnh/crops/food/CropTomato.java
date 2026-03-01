package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropTomato extends CropFood {

    public CropTomato() {
        super("tomato", new Color(0x91101F), new Color(0xE3321F));

        this.addDrop(OreDictHelper.getCopiedOreStack("cropTomato", 1), 75_00);
        this.addDrop(CropsNHItemList.maxTomato.get(1), 25_00);

        this.addAlternateSeed("seedTomato");
        this.addAlternateSeed("cropTomato");
        // Likes warm weather, and a good dose of water.
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
