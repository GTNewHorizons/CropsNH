package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropHemp extends NHCropCard {

    public CropHemp() {
        super("hemp", new Color(0x275600), new Color(0xBBB49D));
        // TODO: CONSIDER IF WE WANT TO KEEP FLAX OR HEMP AROUND
        this.addDrop(new ItemStack(Items.string, 2, 0), 100_00);
    }

    @Override
    public String getFlavourText() {
        return "cropsnh_crops.hemp.flavour";
    }

    @Override
    public String getCreator() {
        return "Alkalus";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 2200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
