package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import tconstruct.world.TinkerWorld;

public class CropGoldOreBerry extends CropOreBerry {

    public CropGoldOreBerry() {
        super("gold", new Color(0x797900), new Color(0xDEC600));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 1), 100_00);
        this.addAlternateSeeds(new ItemStack(TinkerWorld.oreBerries, 1, 1));
        this.addBlockUnderRequirement("gold");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public Color getPrimarySeedColor() {
        return new Color(255, 255, 30).darker();
    }

    @Override
    public Color getSecondarySeedColor() {
        return new Color(255, 255, 30);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
