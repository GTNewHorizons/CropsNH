package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropRaspberry extends CropFood {

    public CropRaspberry() {
        super("raspberry", new Color(0xA23131), new Color(0xC54F4F));

        this.addDrop(CropsNHUtils.getModItem(ModUtils.Natura, "berry", 3, 0), 100_00);

        this.addAlternateSeed("seedRaspberry");
        this.addAlternateSeed("cropRaspberry");

        // likes a well drained soil
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.DRY);
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
