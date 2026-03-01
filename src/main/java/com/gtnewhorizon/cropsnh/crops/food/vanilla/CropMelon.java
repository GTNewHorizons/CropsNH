package com.gtnewhorizon.cropsnh.crops.food.vanilla;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;

public class CropMelon extends CropFood {

    public CropMelon() {
        super("melon", new Color(0x2A220B), new Color(0x615535));
        this.addAlternateSeed(new ItemStack(Items.melon_seeds, 1));
        this.addDrop(new ItemStack(Items.melon, 4), 6666);
        this.addDrop(new ItemStack(Blocks.melon_block, 1), 3333);
        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.melon.name";
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.grains;
    }
}
