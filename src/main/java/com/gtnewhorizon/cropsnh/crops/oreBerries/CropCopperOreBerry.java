package com.gtnewhorizon.cropsnh.crops.oreBerries;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;
import net.minecraft.item.ItemStack;
import tconstruct.world.TinkerWorld;

import java.awt.Color;

public class CropCopperOreBerry extends CropOreBerry {

    public CropCopperOreBerry() {
        super("copper", new Color(0xA84D11), new Color(0xF26F18));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 2), 100_00);
        this.addAlternateSeeds(new ItemStack(TinkerWorld.oreBerries, 1, 2));
        this.addBlockUnderRequirement("copper");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }
}
