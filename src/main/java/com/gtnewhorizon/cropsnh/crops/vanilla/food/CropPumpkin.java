package com.gtnewhorizon.cropsnh.crops.vanilla.food;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFood;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.MinLightLevelRequirement;

public class CropPumpkin extends CropVanillaFood {

    public CropPumpkin() {
        super("pumpkin", new Color(0xCAB16E), new Color(0xC8D4AA));
        this.addDrop(new ItemStack(Blocks.pumpkin, 1), 10_000);
        this.addAlternateSeeds(new ItemStack(Items.pumpkin_seeds, 1));
        this.addGrowthRequirements(new MinLightLevelRequirement(12));
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.pumpkin.name";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
    }

}
