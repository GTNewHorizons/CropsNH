package com.gtnewhorizon.cropsnh.crops.abstracts;

import java.awt.Color;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

public class CropBonsai extends NHCropCard {

    private final int tier;
    private final String creator;
    private static final ISoilList soil = SoilRegistry.instance.get("dirt");

    public CropBonsai(String id, Color color1, Color color2, String creator, int tier, ItemStack sapling,
        ItemStack log) {
        this(id, color1, color2, creator, tier, sapling, log, 10);
    }

    public CropBonsai(String id, Color color1, Color color2, String creator, int tier, ItemStack sapling, ItemStack log,
        int logCount) {
        super(id, color1, color2);
        this.creator = creator;
        this.tier = tier;
        // drops
        ItemStack logDrop = log.copy();
        logDrop.stackSize = logCount;
        ItemStack sapplingDrop = sapling.copy();
        sapplingDrop.stackSize = 2;
        this.addDrop(sapling, 30_00);
        this.addDrop(logDrop, 60_00);
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
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public String getCreator() {
        return this.creator;
    }
}
