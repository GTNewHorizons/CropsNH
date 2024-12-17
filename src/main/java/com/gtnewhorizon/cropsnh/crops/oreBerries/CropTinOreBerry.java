package com.gtnewhorizon.cropsnh.crops.oreBerries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;

import tconstruct.world.TinkerWorld;

public class CropTinOreBerry extends CropOreBerry {

    public CropTinOreBerry() {
        super("tin", new Color(0x8F8F8F), new Color(0xDCDCDC));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 3), 100_00);
        this.addAlternateSeeds(new ItemStack(TinkerWorld.oreBerries, 1, 3));
        this.addBlockUnderRequirement("tin");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
