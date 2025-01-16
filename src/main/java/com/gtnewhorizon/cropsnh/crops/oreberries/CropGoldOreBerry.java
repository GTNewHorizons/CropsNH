package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

import tconstruct.world.TinkerWorld;

public class CropGoldOreBerry extends CropOreBerry {

    public CropGoldOreBerry() {
        super("gold", new Color(0xB3B315), new Color(0xFFFF1E));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 1), 100_00);
        this.addAlternateSeed(new ItemStack(TinkerWorld.oreBerries, 1, 1));
        this.addBlockUnderRequirement("gold");
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
