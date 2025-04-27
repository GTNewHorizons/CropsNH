package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MinLightLevelGrowthRequirement;

public class CropRedwheat extends NHCropCard {

    public CropRedwheat() {
        super("redwheat", new Color(0x6C4B17), new Color(0xD45555));
        this.addDrop(new ItemStack(Items.redstone, 1, 0), 100_00);
        this.addGrowthRequirement(new MinLightLevelGrowthRequirement(5));
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(12));
        this.addBlockUnderRequirement("redstone");
    }

    @Override
    public String getCreator() {
        return "raa1337";
    }

    @Override
    public float getDropChance() {
        return 0.85f;
    }

    @Override
    public int getGrowthDuration() {
        return 1500;
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getMaxGrowthStage() {
        return 7;
    }
}
