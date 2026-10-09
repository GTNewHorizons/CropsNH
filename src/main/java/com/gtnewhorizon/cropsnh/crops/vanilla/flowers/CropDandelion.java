package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;
import com.gtnewhorizon.cropsnh.reference.Constants;

public class CropDandelion extends CropVanillaFlower {

    public CropDandelion() {
        super("dandelion", new Color(0x8E6B13), new Color(0xE7E769));

        this.addDrop(new ItemStack(Items.dye, 1, 11), Constants.FLOWER_DYE_DROP_CHANCE);
        this.addDrop(new ItemStack(Blocks.yellow_flower, 1, 0), Constants.FLOWER_ITEM_DROP_CHANCE);

        this.addAlternateSeed(new ItemStack(Blocks.yellow_flower, 1, 0));

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower1.dandelion.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
