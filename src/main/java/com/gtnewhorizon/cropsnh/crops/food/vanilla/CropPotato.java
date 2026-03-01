package com.gtnewhorizon.cropsnh.crops.food.vanilla;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropPotato extends CropFood {

    public CropPotato() {
        super("potato", new Color(0xC0A51B), new Color(0xFBE6BC));
        this.addDrop(new ItemStack(Items.potato, 1), 10_000);
        this.addAlternateSeed(new ItemStack(Items.potato, 1));
        // tends to be a staple crop for more northern/cold countries.
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);
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
