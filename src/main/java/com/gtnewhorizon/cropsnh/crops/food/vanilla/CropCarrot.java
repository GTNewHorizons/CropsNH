package com.gtnewhorizon.cropsnh.crops.food.vanilla;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropCarrot extends CropFood {

    public CropCarrot() {
        super("carrot", new Color(0xE58700), new Color(0xEEAE7D));
        this.addDrop(new ItemStack(Items.carrot, 1), 10_000);
        this.addAlternateSeed(new ItemStack(Items.carrot, 1));
        // prefers sandier soils
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "item.carrots.name";
    }

    @Override
    public int getTier() {
        return 2;
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
        return "carrots_stage_";
    }

}
