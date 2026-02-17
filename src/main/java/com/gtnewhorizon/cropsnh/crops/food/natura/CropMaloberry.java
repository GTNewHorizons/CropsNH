package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropMaloberry extends CropFood {

    public CropMaloberry() {
        super("maloberry", new Color(0xCE5939), new Color(0xE8B064));

        this.addDrop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 3), 100_00);

        this.addAlternateSeed("cropMaloberry");
        this.addAlternateSeed("cropGooseberry");
        this.addAlternateSeed("seedMaloberry");
        this.addAlternateSeed("seedGooseberry");

        // likes higher latitudes, but doesn't live in the snow too much.
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH);
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
