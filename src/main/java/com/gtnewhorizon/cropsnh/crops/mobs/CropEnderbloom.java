package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Materials;

public class CropEnderbloom extends NHCropCard {

    public CropEnderbloom() {
        super("enderbloom", new Color(0x085245), new Color(0x1B7B6B));
        this.addDrop(Materials.EnderPearl.getDust(1), 62_50);
        this.addDrop(new ItemStack(Items.ender_pearl, 1, 0), 25_00);
        this.addDrop(new ItemStack(Items.ender_eye, 1, 0), 12_50);
    }

    @Override
    public String getCreator() {
        return "RichardG";
    }

    @Override
    public int getTier() {
        return 10;
    }

    @Override
    public int getGrowthDuration() {
        return 3000;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
