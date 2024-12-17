package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import tconstruct.world.TinkerWorld;

public class CropAluminiumOreBerry extends CropOreBerry {

    public CropAluminiumOreBerry() {
        super("aluminium", new Color(0x5687A3), new Color(0x80C8F0));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 4), 100_00);
        this.addAlternateSeed(new ItemStack(TinkerWorld.oreBerries, 1, 4));
        this.addBlockUnderRequirement("aluminium");
        this.addGrowthRequirement(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }
}
