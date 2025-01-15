package com.gtnewhorizon.cropsnh.crops.natura.nether.glowshroom;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

import mods.natura.common.NContent;

public class CropPurpleGlowshroom extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("netherrack");

    public CropPurpleGlowshroom() {
        super("purpleGlowshroom", new Color(0x511FB6), new Color(0x8B2CD2));
        this.addDrop(new ItemStack(NContent.glowshroom, 1, 1), 100_00);
        this.addAlternateSeed(new ItemStack(NContent.glowshroom, 1, 1));
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 600;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.spore;
    }

    @Override
    public int getMaxGrowthStage() {
        return 2;
    }
}
