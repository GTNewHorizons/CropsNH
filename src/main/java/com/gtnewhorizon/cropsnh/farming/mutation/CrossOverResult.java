package com.gtnewhorizon.cropsnh.farming.mutation;

import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.api.v1.ICrossOverResult;
import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import com.gtnewhorizon.cropsnh.farming.CropRegistry;

/**
 * Represents the result of a specific <code>INewSeedStrategy</code> containing
 * the seed plus meta and the chance to happen.
 */
public class CrossOverResult implements ICrossOverResult {

    private Item seed;
    private int meta;
    private double chance;
    private ISeedStats stats;

    public CrossOverResult(Item seed, int meta, double chance, ISeedStats stats) {
        this.seed = seed;
        this.meta = meta;
        this.chance = chance;
        this.stats = stats;
    }

    @Override
    public Item getSeed() {
        return seed;
    }

    @Override
    public int getMeta() {
        return meta;
    }

    @Override
    public void setSeedAndMeta(Item seed, int meta) {
        if (CropRegistry.isValidSeed(seed, meta)) {
            this.seed = seed;
            this.meta = meta;
        }
    }

    @Override
    public double getChance() {
        return chance;
    }

    @Override
    public void setChance(double chance) {
        this.chance = chance;
    }

    public ISeedStats getStats() {
        return stats;
    }

    @Override
    public void setSeedStats(ISeedStats stats) {
        this.stats = stats;
    }
}
