package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropEggPlant extends CropFood {

    public CropEggPlant() {
        super("eggPlant", new Color(0xB3A57D), new Color(0xDFCE9B));
        // TODO: MOVE ITEM DROP TO CROPS NH OR SOMETHING
        this.addDrop(new ItemStack(Items.egg, 1, 0), 60_00);
        this.addDrop(new ItemStack(Items.feather, 1, 0), 30_00);
        this.addDrop(new ItemStack(Items.chicken, 1, 0), 10_00);
        this.addAlternateSeed(new ItemStack(Items.egg, 1, 0));
    }

    @Override
    public String getCreator() {
        return "Link";
    }

    @Override
    public String getFlavourText() {
        return "cropsnh_crops.eggPlant.flavour";
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

}
