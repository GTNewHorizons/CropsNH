package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import mods.natura.common.NContent;

public class CropBlueberry extends CropFood {

    public CropBlueberry() {
        super("blueberry", new Color(0x354085), new Color(0x5262B5));
        this.addDrop(new ItemStack(NContent.berryItem, 3, 1), 100_00);
        this.addAlternateSeed("cropBlueberry");
        this.addAlternateSeed("seedBlueberry");
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
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
