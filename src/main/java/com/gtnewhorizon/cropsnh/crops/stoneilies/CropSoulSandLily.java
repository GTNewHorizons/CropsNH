package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

public class CropSoulSandLily extends CropBaseStoneLily {

    public CropSoulSandLily() {
        super("soulSand", new Color(0x281A12), new Color(0x534034));
        this.addDrop(new ItemStack(Blocks.soul_sand, 2), 100_00);
        this.addBlockUnderRequirement("soulSand");
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 1600;
    }
}
