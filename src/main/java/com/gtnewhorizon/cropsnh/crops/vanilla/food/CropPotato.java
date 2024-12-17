package com.gtnewhorizon.cropsnh.crops.vanilla.food;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFood;

public class CropPotato extends CropVanillaFood {

    public CropPotato() {
        super("potato", new Color(0xC0A51B), new Color(0xFBE6BC));
        this.addDrop(new ItemStack(Items.potato, 1), 10_000);
        this.addAlternateSeed(new ItemStack(Items.potato, 1));
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "item.potato.name";
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    @Override
    protected String getBaseTexturePath() {
        return "potatoes_stage_";
    }

}
