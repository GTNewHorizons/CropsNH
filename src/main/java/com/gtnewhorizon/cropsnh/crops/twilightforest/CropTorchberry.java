package com.gtnewhorizon.cropsnh.crops.twilightforest;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import twilightforest.item.TFItems;

public class CropTorchberry extends NHCropCard {

    public CropTorchberry() {
        super("torchberry", new Color(0xAB934D), new Color(0xF5D36E));
        this.addDrop(new ItemStack(TFItems.torchberries, 1), 100_00);
        this.addAlternateSeed(new ItemStack(TFItems.torchberries, 1));
        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.decorative.register(this);
    }

    @Override
    public String getCreator() {
        return "Minepolz320";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getGrowthDuration() {
        return 150;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
