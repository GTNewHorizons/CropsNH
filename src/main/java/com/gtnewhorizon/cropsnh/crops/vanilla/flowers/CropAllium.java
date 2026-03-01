package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropAllium extends CropVanillaFlower {

    public CropAllium() {
        super("allium", new Color(0x6B2166), new Color(0xDB7AD5));

        this.addDrop(new ItemStack(Items.dye, 1, 13), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 2));

        // Prefers siltier soils
        this.addLikedBiomes(BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SPARSE);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.allium.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
