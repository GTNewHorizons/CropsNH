package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropRedTulip extends CropVanillaFlower {

    public CropRedTulip() {
        super("redTulip", new Color(0x7A0707), new Color(0xD24343));

        this.addDrop(new ItemStack(Items.dye, 1, 1), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 4));

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.tulipRed.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
