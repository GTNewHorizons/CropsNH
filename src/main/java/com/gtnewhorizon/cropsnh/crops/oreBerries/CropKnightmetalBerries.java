package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

public class CropKnightmetalBerries extends CropOreBerry {

    public CropKnightmetalBerries() {
        super("knightmetal", new Color(0x90A68A), new Color(0xD2F0C8));
        this.addDrop(new ItemStack(twilightforest.item.TFItems.armorShard, 4), 100_00);
        this.addBlockUnderRequirement("knightmetal");
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.lowTierOreBerries.register(this);
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
