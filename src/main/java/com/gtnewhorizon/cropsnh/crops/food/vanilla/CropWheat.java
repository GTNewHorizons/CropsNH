package com.gtnewhorizon.cropsnh.crops.food.vanilla;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropWheat extends CropFood {

    public CropWheat() {
        super("wheat", new Color(0xB7BB3F), new Color(0x00E210));
        this.addDrop(new ItemStack(Items.wheat, 1), 10_000);
        this.addAlternateSeed(new ItemStack(Items.wheat_seeds, 1));
        // not much to say here
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "item.wheat.name";
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 7;
    }

    @Override
    protected String getBaseTexturePath() {
        return "wheat_stage_";
    }

}
