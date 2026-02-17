package com.gtnewhorizon.cropsnh.crops.food.bop;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropBoPBerry extends CropFood {

    public CropBoPBerry() {
        super("bopBerry", new Color(0xB33636), new Color(0xFF4C4C));

        ItemStack bopBerry = CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 3, 0);
        this.addDrop(bopBerry.copy(), 100_00);

        this.addAlternateSeed(CropsNHUtils.copyStackWithSize(bopBerry, 1));

        this.addLikedBiomes(BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE);
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
