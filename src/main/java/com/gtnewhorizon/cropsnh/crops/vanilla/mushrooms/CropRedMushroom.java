package com.gtnewhorizon.cropsnh.crops.vanilla.mushrooms;

import java.awt.Color;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropMushroom;

public class CropRedMushroom extends CropMushroom {

    public CropRedMushroom() {
        super("redMushroom", new Color(0xA61313), new Color(0xE21212));

        this.addDrop(new ItemStack(Blocks.red_mushroom, 1), 10_000);

        this.addAlternateSeed(new ItemStack(Blocks.red_mushroom, 1));

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
