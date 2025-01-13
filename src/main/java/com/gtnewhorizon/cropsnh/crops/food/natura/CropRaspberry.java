package com.gtnewhorizon.cropsnh.crops.food.natura;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import mods.natura.common.NContent;

public class CropRaspberry extends CropFood {

    public CropRaspberry() {
        super("raspberry", new Color(0xA23131), new Color(0xC54F4F));
        this.addDrop(new ItemStack(NContent.berryItem, 3, 0), 100_00);
        this.addAlternateSeed("seedRaspberry");
        this.addAlternateSeed("cropRaspberry");
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
