package com.gtnewhorizon.cropsnh.crops.twilightforest;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.requirements.growth.MaxLightLevelGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public class CropTorchberry extends NHCropCard {

    public CropTorchberry() {
        super("torchberry", new Color(0xAB934D), new Color(0xF5D36E));

        ItemStack torchberries = CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.torchberries", 1, 0);
        this.addDrop(torchberries.copy(), 100_00);

        this.addAlternateSeed(torchberries.copy());

        this.addGrowthRequirement(new MaxLightLevelGrowthRequirement(10));

        this.addLikedBiomes(BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
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
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
