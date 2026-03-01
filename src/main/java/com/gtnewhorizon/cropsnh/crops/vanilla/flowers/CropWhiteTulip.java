package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropWhiteTulip extends CropVanillaFlower {

    public CropWhiteTulip() {
        super("whiteTulip", new Color(0x3D3D4C), new Color(0xD9D9D9));

        this.addDrop(new ItemStack(Items.dye, 1, 7), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 6));

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.tulipWhite.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
