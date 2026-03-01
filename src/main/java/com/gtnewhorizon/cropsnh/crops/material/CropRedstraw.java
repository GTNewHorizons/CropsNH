package com.gtnewhorizon.cropsnh.crops.material;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MinLightLevelGrowthRequirement;

import gregtech.api.enums.VoltageIndex;

public class CropRedstraw extends NHCropCard {

    public CropRedstraw() {
        super("redstraw", new Color(0x6C4B17), new Color(0xD45555));
        this.addDrop(new ItemStack(Items.redstone, 1, 0), 100_00);

        this.addGrowthRequirement(new MinLightLevelGrowthRequirement(5));
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(12));

        this.addBlockUnderRequirement("redstone");

        this.addDuplicationCatalyst("dustRedstone", 1);

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);
    }

    @Override
    public float getDropChance() {
        return 0.85f;
    }

    @Override
    public int getTier() {
        return 6;
    }

    @Override
    public int getMachineBreedingRecipeTier() {
        return VoltageIndex.MV;
    }

    @Override
    public int getGrowthDuration() {
        return 1500;
    }

    @Override
    public String getCreator() {
        return "raa1337";
    }

    @Override
    public int getMaxGrowthStage() {
        return 7;
    }
}
