package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropFlax extends NHCropCard {

    public CropFlax() {
        super("flax", new Color(0x804D3E), new Color(0xB76E5A));
        // TODO: CONSIDER IF WE WANT TO KEEP FLAX OR HEMP AROUND SINCE THEY DROP THE SAME THING
        this.addDrop(new ItemStack(Items.string, 1, 0), 100_00);
    }

    @Override
    public String getCreator() {
        return "Eloraam";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
