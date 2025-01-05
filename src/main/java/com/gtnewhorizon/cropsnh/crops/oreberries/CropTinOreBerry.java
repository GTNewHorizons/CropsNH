package com.gtnewhorizon.cropsnh.crops.oreberries;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropOreBerry;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;

import tconstruct.world.TinkerWorld;

public class CropTinOreBerry extends CropOreBerry {

    public CropTinOreBerry() {
        super("tin", new Color(0x8F8F8F), new Color(0xDCDCDC));
        this.addDrop(new ItemStack(TinkerWorld.oreBerries, 6, 3), 100_00);
        this.addAlternateSeed(new ItemStack(TinkerWorld.oreBerries, 1, 3));
        this.addBlockUnderRequirement("tin");
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.lowTierOreBerries.register(this);
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
