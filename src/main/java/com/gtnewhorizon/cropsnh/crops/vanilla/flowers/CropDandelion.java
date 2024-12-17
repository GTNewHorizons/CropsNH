package com.gtnewhorizon.cropsnh.crops.vanilla.flowers;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropDandelion extends CropVanillaFlower {

    public CropDandelion() {
        super("dandelion", new Color(0x8E6B13), new Color(0xE7E769));
        this.addDrop(new ItemStack(Items.dye, 1, 11), 10_000);
        this.addAlternateSeed(new ItemStack(Blocks.yellow_flower, 1, 0));
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
