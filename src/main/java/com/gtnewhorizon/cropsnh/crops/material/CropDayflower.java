package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropDayflower extends CropVanillaFlower {

    public CropDayflower() {
        super("dayflower", new Color(0x236F8F), new Color(0x3C8EB0));
        this.addDrop(new ItemStack(Items.dye, 1, 6), 10_000);
        // Assuming it's essentially cornflower, invasive species,
        this.addLikedBiomes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SPARSE);
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
