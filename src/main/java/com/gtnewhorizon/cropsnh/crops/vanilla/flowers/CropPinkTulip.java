package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropPinkTulip extends CropVanillaFlower {

    public CropPinkTulip() {
        super("pinkTulip", new Color(0x80365C), new Color(0xF7B4D6));

        this.addDrop(new ItemStack(Items.dye, 1, 9), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 7));

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.WET);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.tulipPink.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
