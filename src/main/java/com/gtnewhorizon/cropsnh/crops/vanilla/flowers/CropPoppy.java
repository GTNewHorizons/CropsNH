package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropPoppy extends CropVanillaFlower {

    public CropPoppy() {
        super("poppy", new Color(0x3A0102), new Color(0xBA050B));

        this.addDrop(new ItemStack(Items.dye, 1, 1), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 0));
        // half tempted to add dead, but that just feels like a bad taste joke.
        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.poppy.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
