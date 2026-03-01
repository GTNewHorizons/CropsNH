package com.gtnewhorizon.cropsnh.crops.vanilla;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;

public class CropVine extends NHCropCard {

    public CropVine() {
        super("vine", new Color(0x173A0A), new Color(0x2C7310));

        this.addDrop(new ItemStack(Blocks.vine, 2), 100_00);

        this.addAlternateSeed(new ItemStack(Blocks.vine, 1, 0));

        this.addLikedBiomes(BiomeDictionary.Type.LUSH, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.WET);
    }

    @Override
    public String getCreator() {
        return "jeb_";
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
