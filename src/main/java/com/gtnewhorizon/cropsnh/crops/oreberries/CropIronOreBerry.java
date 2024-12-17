package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import tconstruct.world.TinkerWorld;

public class CropIronOreBerry extends CropOreBerry {

    public CropIronOreBerry() {
        super("iron", new Color(0x7D7D7D), new Color(0xC8C8C8));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 0), 100_00);
        this.addAlternateSeed(new ItemStack(TinkerWorld.oreBerries, 1, 0));
        this.addBlockUnderRequirement("iron");
        this.addGrowthRequirement(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
