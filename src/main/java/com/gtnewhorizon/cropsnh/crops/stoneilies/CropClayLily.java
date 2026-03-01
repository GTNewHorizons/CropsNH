package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.VoltageIndex;

public class CropClayLily extends CropBaseStoneLily {

    public CropClayLily() {
        super("clay", new Color(0x666B7F), new Color(0xA5A9B9));

        this.addDrop(new ItemStack(Items.clay_ball, 3, 0), 100_00);

        this.addBlockUnderRequirement("clay");

        this.addDuplicationCatalyst("dustClay", 1);
        this.addDuplicationCatalyst("itemClay", 1);

        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.SWAMP);
    }

    @Override
    public String getCreator() {
        return "C0bra5";
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        // slower growth because it's used for passive alu in the early game
        return 850;
    }
}
