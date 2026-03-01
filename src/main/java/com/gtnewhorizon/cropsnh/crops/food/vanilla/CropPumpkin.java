package com.gtnewhorizon.cropsnh.crops.food.vanilla;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropPumpkin extends CropFood {

    public CropPumpkin() {
        super("pumpkin", new Color(0xCAB16E), new Color(0xC8D4AA));
        this.addDrop(new ItemStack(Blocks.pumpkin, 1), 10_000);
        this.addAlternateSeed(new ItemStack(Items.pumpkin_seeds, 1));
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.WET);
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
