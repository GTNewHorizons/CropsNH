package com.gtnewhorizon.cropsnh.crops.oreBerries;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;
import net.minecraft.item.ItemStack;

import java.awt.Color;

import tconstruct.world.TinkerWorld;

public class CropAluminiumOreBerry extends CropOreBerry {

    public CropAluminiumOreBerry() {
        super("aluminium", new Color(0x5687A3), new Color(0x80C8F0));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 4), 100_00);
        this.addAlternateSeeds(new ItemStack(TinkerWorld.oreBerries, 1, 4));
        this.addBlockUnderRequirement("aluminium");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }
}
