package com.gtnewhorizon.cropsnh.crops.vanilla.food;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFood;

public class CropCocoa extends CropVanillaFood {

    public CropCocoa() {
        super("cocoa", new Color(0x734120), new Color(0x976746));
        this.addDrop(new ItemStack(Items.dye, 1, 3), 10_000);
        this.addAlternateSeed(new ItemStack(Items.dye, 1, 3));
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.cocoa.name";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 1700;
    }

}
