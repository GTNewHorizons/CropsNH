package com.gtnewhorizon.cropsnh.crops.thaumcraft;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import thaumcraft.common.config.ConfigBlocks;

public class CropShimmerleaf extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropShimmerleaf() {
        super("shimmerleaf", new Color(0x78A59C), new Color(0xA9C6C1));
        this.addDrop(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), 100_00);
        this.addAlternateSeed(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2));
        this.addBlockUnderRequirement("quicksilver");
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek and DreamMasterXXL";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 4000;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
