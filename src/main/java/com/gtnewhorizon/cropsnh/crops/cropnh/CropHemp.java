package com.gtnewhorizon.cropsnh.crops.cropnh;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import goodgenerator.util.ItemRefer;
import gtPlusPlus.core.util.math.MathUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import java.awt.Color;

public class CropHemp extends NHCropCard {
    public CropHemp() {
        super("hemp", new Color(0x275600), new Color(0xBBB49D));
        this.addDrop(new ItemStack(Items.string, 2, 0), 100_00);
    }

    @Override
    public String getFlavourText() {
        return "It prefers dry biomes. Inedible.";
    }

    @Override
    public String getCreator() {
        return "Alkalus";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 2200;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
