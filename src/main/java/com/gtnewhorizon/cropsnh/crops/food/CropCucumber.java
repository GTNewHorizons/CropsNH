package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropCucumber extends CropFood {

    public CropCucumber() {
        super("cucumber", new Color(0x13260D), new Color(0x259038));
        // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
        this.addDrop(OreDictHelper.getCopiedOreStack("cropCucumber", 1), 100_00);
        this.addAlternateSeed("seedCucumber");
        this.addAlternateSeed("cropCucumber");
        // likes warm soils mostly
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.LUSH);
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
