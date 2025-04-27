package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;

public class CropKnightmetalBerry extends CropOreBerry {

    public CropKnightmetalBerry() {
        super("knightmetal", new Color(0x90A68A), new Color(0xD2F0C8));
        this.addDrop(new ItemStack(twilightforest.item.TFItems.armorShard, 4), 100_00);
        this.addBlockUnderRequirement("knightmetal");
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
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
