package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropStrawberry extends CropFood {

    public CropStrawberry() {
        super("strawberry", new Color(0x8F0205), new Color(0xCE0609));
        this.addDrop(OreDictHelper.getCopiedOreStack("cropStrawberry", 1), 100_00);
        this.addAlternateSeed("seedStrawberry");
        this.addAlternateSeed("cropStrawberry");
        // not very fussy about it's soil
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
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
