package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSubSoilTypes;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.VoltageIndex;
import gregtech.api.util.GTOreDictUnificator;

public class CropEnderbloom extends NHCropCard {

    public CropEnderbloom() {
        super("enderbloom", new Color(0x085245), new Color(0x1B7B6B));

        this.addDrop(Materials.EnderPearl.getDust(1), 62_50);
        this.addDrop(new ItemStack(Items.ender_pearl, 1, 0), 37_50);

        this.addSubSoilRequirement(CropsNHSubSoilTypes.endStone);

        this.addDuplicationCatalyst(new ItemStack(Items.ender_pearl, 1, 0));
        this.addDuplicationCatalyst(GTOreDictUnificator.get(OrePrefixes.dust, Materials.EnderPearl, 1));

        this.addLikedBiomes(BiomeDictionary.Type.END, BiomeDictionary.Type.COLD);
    }

    @Override
    public String getCreator() {
        return "RichardG";
    }

    @Override
    public int getTier() {
        return 10;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.HV;
    }

    @Override
    public int getGrowthDuration() {
        return 3000;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
