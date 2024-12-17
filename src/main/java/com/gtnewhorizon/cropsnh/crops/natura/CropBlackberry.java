package com.gtnewhorizon.cropsnh.crops.natura;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import mods.natura.common.NContent;

public class CropBlackberry extends NHCropCard {

    public CropBlackberry() {
        super("blackberry", new Color(0x141C22), new Color(0x4D5A66));
        this.addDrop(new ItemStack(NContent.berryItem, 3, 2), 100_00);
        this.addAlternateSeed("cropBlackberry");
        this.addAlternateSeed("seedBlackberry");
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
