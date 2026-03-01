package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropFlax extends NHCropCard {

    public CropFlax() {
        super("flax", new Color(0x804D3E), new Color(0xB76E5A));

        this.addDrop(new ItemStack(Items.string, 1, 0), 100_00);
        // flax is typically found in sedimentary soils.
        this.addLikedBiomes(
            BiomeDictionary.Type.WET,
            BiomeDictionary.Type.RIVER,
            BiomeDictionary.Type.BEACH,
            BiomeDictionary.Type.SANDY);
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
