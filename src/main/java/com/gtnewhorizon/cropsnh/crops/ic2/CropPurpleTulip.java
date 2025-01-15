package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFlower;

public class CropPurpleTulip extends CropVanillaFlower {

    public CropPurpleTulip() {
        super("purpleTulip", new Color(0x8230B2), new Color(0xA453CE));
        this.addDrop(new ItemStack(Items.dye, 1, 5), 10_000);
    }

    @Override
    public String getCreator() {
        return "IC2 Team";
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
