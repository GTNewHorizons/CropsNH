package com.gtnewhorizon.cropsnh.crops.bomesoplenty;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import biomesoplenty.api.content.BOPCBlocks;

public class CropGlowflower extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropGlowflower() {
        super("glowflower", new Color(0x004D4C), new Color(0x12A4A2));
        this.addDrop(new ItemStack(BOPCBlocks.flowers, 2, 3), 100_00);
        this.addAlternateSeed(new ItemStack(BOPCBlocks.flowers, 1, 3));
        this.addBlockUnderRequirement("glowstone");
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getTier() {
        return 3;
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
