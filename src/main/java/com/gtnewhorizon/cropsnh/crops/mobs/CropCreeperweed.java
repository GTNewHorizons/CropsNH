package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropCreeperweed extends NHCropCard {

    public CropCreeperweed() {
        super("creeperweed", new Color(0x396B37), new Color(0x51984D));

        this.addDrop(new ItemStack(Items.gunpowder, 1, 0), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public String getCreator() {
        return "General Spaz";
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
