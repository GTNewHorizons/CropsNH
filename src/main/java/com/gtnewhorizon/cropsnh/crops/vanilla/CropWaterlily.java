package com.gtnewhorizon.cropsnh.crops.vanilla;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropWaterlily extends NHCropCard {

    public CropWaterlily() {
        super("waterLily", new Color(0x3E89D8), new Color(0xD497D8));

        this.addDrop(new ItemStack(Items.dye, 2, 9), 20_000);

        this.addDrop(new ItemStack(Blocks.waterlily, 2), 80_000);

        this.addAlternateSeed(new ItemStack(Blocks.waterlily, 2));

        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.RIVER);
    }

    @Override
    public String getCreator() {
        return "moronwmachinegun";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.flower;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
