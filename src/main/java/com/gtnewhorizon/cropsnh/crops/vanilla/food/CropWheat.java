package com.gtnewhorizon.cropsnh.crops.vanilla.food;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.CropVanillaFood;

public class CropWheat extends CropVanillaFood {

    public CropWheat() {
        super("wheat", new Color(0xB7BB3F), new Color(0x00E210));
        this.addDrop(new ItemStack(Items.wheat, 1), 10_000);
        this.addAlternateSeeds(new ItemStack(Items.wheat_seeds, 1));
    }

    @Override
    public String getCreator() {
        return "Notch";
    }

    @Override
    public String getUnlocalizedName() {
        return "item.wheat.name";
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getGrowthDuration() {
        return 1000;
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 7;
    }

    @Override
    protected String getBaseTexturePath() {
        return "wheat_stage_";
    }

}
