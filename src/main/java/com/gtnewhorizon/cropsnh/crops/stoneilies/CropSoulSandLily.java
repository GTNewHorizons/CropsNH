package com.gtnewhorizon.cropsnh.crops.stoneilies;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropBaseStoneLily;

import gregtech.api.enums.VoltageIndex;

public class CropSoulSandLily extends CropBaseStoneLily {

    public CropSoulSandLily() {
        super("soulSand", new Color(0x281A12), new Color(0x534034));

        this.addDrop(new ItemStack(Blocks.soul_sand, 1), 25_00);

        this.addBlockUnderRequirement("soulSand");

        this.addDuplicationCatalyst(new ItemStack(Blocks.soul_sand, 1));

        this.addLikedBiomes(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.SANDY);
    }

    @Override
    public String getCreator() {
        return "C0bra5";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.HV;
    }

    @Override
    public int getGrowthDuration() {
        return 2000;
    }
}
