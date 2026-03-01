package com.gtnewhorizon.cropsnh.crops.vanilla.mushrooms;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropMushroom;

public class CropBrownMushroom extends CropMushroom {

    public CropBrownMushroom() {
        super("brownMushroom", new Color(0x8F4E1B), new Color(0xCC9978));

        this.addDrop(new ItemStack(Blocks.brown_mushroom, 1), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.brown_mushroom, 1));

        this.addLikedBiomes(BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP);
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 400;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }
}
