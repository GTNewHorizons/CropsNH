package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropOxeyeDaisy extends CropVanillaFlower {

    public CropOxeyeDaisy() {
        super("oxeyeDaisy", new Color(0x3D3D4C), new Color(0xC8C8C8));

        this.addDrop(new ItemStack(Items.dye, 1, 7), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_flower, 1, 8));

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.flower2.oxeyeDaisy.name";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
