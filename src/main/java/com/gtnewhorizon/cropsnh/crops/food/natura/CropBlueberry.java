package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropBlueberry extends CropFood {

    public CropBlueberry() {
        super("blueberry", new Color(0x354085), new Color(0x5262B5));

        this.addDrop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 1), 100_00);

        this.addAlternateSeed("cropBlueberry");
        this.addAlternateSeed("seedBlueberry");
        // wild blueberries can often be found forests, usually with dry or gravely soils.
        this.addLikedBiomes(BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH);
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
