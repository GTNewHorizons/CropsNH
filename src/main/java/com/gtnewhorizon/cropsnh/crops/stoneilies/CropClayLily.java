package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

public class CropClayLily extends CropBaseStoneLily {

    public CropClayLily() {
        super("clay", new Color(0x666B7F), new Color(0xA5A9B9));
        this.addDrop(new ItemStack(Items.clay_ball, 3, 0), 100_00);
        this.addBlockUnderRequirement("clay");
        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.RIVER);
    }

    @Override
    public int getGrowthDuration() {
        // slower growth because it's used for passive alu in the early game
        return 850;
    }
}
