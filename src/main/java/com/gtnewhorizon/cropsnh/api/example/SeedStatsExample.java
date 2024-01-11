package com.gtnewhorizon.cropsnh.api.example;

import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;

import cpw.mods.fml.common.Optional;

/**
 * Example implementation of the ISeedStats interface
 */
@Optional.Interface(modid = "CropsNH", iface = "com.gtnewhorizon.cropsnh.api.v2.ISeedStats")
public class SeedStatsExample implements ISeedStats {

    private final short growth;
    private final short gain;
    private final short strength;
    private boolean analyzed;

    public SeedStatsExample(short growth, short gain, short strength, boolean analyzed) {
        this.growth = growth;
        this.gain = gain;
        this.strength = strength;
        this.analyzed = analyzed;
    }

    @Override
    public short getGrowth() {
        return growth;
    }

    @Override
    public short getGain() {
        return gain;
    }

    @Override
    public short getStrength() {
        return strength;
    }

    @Override
    public short getMaxGrowth() {
        return ExampleCropsNHAPIwrapper.getInstance().exampleMethodGetSeedStatsCap();
    }

    @Override
    public short getMaxGain() {
        return ExampleCropsNHAPIwrapper.getInstance().exampleMethodGetSeedStatsCap();
    }

    @Override
    public short getMaxStrength() {
        return ExampleCropsNHAPIwrapper.getInstance().exampleMethodGetSeedStatsCap();
    }

    @Override
    public boolean isAnalyzed() {
        return analyzed;
    }

    @Override
    public void setAnalyzed(boolean value) {
        this.analyzed = value;
    }
}
