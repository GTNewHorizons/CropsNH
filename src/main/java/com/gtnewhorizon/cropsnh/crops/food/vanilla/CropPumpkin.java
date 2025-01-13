package com.gtnewhorizon.cropsnh.crops.food.vanilla;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropPumpkin extends CropFood {

    public CropPumpkin() {
        super("pumpkin", new Color(0xCAB16E), new Color(0xC8D4AA));
        this.addDrop(new ItemStack(Blocks.pumpkin, 1), 10_000);
        this.addAlternateSeed(new ItemStack(Items.pumpkin_seeds, 1));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.pumpkin.name";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
    }

}
