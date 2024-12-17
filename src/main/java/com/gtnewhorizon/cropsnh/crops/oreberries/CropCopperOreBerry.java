package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import tconstruct.world.TinkerWorld;

public class CropCopperOreBerry extends CropOreBerry {

    public CropCopperOreBerry() {
        super("copper", new Color(0xA84D11), new Color(0xF26F18));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 2), 100_00);
        this.addAlternateSeed(new ItemStack(TinkerWorld.oreBerries, 1, 2));
        this.addBlockUnderRequirement("copper");
        this.addGrowthRequirement(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }
}
