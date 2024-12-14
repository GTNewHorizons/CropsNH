package com.gtnewhorizon.cropsnh.crops.oreBerries;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;
import net.minecraft.item.ItemStack;
import tconstruct.world.TinkerWorld;

import java.awt.Color;

public class CropIronOreBerry extends CropOreBerry {

    public CropIronOreBerry() {
        super("iron", new Color(0x7D7D7D), new Color(0xC8C8C8));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 0), 100_00);
        this.addAlternateSeeds(new ItemStack(TinkerWorld.oreBerries, 1, 0));
        this.addBlockUnderRequirement("iron");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
