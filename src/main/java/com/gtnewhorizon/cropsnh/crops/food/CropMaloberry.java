package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import mods.natura.common.NContent;

public class CropMaloberry extends CropFood {

    public CropMaloberry() {
        super("maloberry", new Color(0xCE5939), new Color(0xE8B064));
        this.addDrop(new ItemStack(NContent.berryItem, 3, 3), 100_00);
        this.addAlternateSeed("cropMaloberry");
        this.addAlternateSeed("cropGooseberry");
        this.addAlternateSeed("seedMaloberry");
        this.addAlternateSeed("seedGooseberry");
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
