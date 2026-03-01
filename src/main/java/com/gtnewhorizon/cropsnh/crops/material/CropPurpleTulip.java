package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropPurpleTulip extends CropVanillaFlower {

    public CropPurpleTulip() {
        super("purpleTulip", new Color(0x8230B2), new Color(0xA453CE));
        this.addDrop(new ItemStack(Items.dye, 1, 5), 10_000);
        // netherlands.jpg
        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.PLAINS);
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
