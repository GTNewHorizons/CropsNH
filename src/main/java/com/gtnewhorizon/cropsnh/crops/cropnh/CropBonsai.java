package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

public class CropBonsai extends NHCropCard {

    private final int tier;
    private final String creator;
    private static final ISoilList soil = SoilRegistry.instance.get("dirt");

    public CropBonsai(String id, Color color1, Color color2, String creator, int tier, ItemStack sapling,
        ItemStack log) {
        super(id, color1, color2);
        this.creator = creator;
        this.tier = tier;
        // drops
        ItemStack logDrop = log.copy();
        logDrop.stackSize = 10;
        ItemStack sapplingDrop = sapling.copy();
        sapplingDrop.stackSize = 2;
        this.addDrop(sapling, 3000);
        this.addDrop(logDrop, 6000);
        // alt seed
        this.addAlternateSeed(sapling);
    }

    @Override
    public ISoilList getSoilTypes() {
        return soil;
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.bonsai;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 1200;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public String getCreator() {
        return this.creator;
    }
}
