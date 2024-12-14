package com.gtnewhorizon.cropsnh.crops.oreBerries;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MaxLightLevelRequirement;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import net.minecraft.item.ItemStack;
import tconstruct.world.TinkerWorld;

import java.awt.Color;

public class CropKnightmetalBerries extends CropOreBerry {

    public CropKnightmetalBerries() {
        super("knightmetal", new Color(0x90A68A), new Color(0xD2F0C8));
        this.addDrop(new ItemStack(twilightforest.item.TFItems.armorShard, 4), 100_00);
        this.addBlockUnderRequirement("knightmetal");
        this.addGrowthRequirements(new MaxLightLevelRequirement(10));
    }

    @Override
    public int getTier() {
        return 8;
    }

    @Override
    public int getGrowthDuration() {
        return 5500;
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

}
