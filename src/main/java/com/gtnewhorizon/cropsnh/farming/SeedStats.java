package com.gtnewhorizon.cropsnh.farming;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;

public class SeedStats implements ISeedStats {

    public final static SeedStats DEFAULT_ANALYZED = new SeedStats((byte) 1, (byte) 1, (byte) 1, true);

    private final byte growth;
    private final byte gain;
    private final byte resistance;
    private boolean analyzed;

    public SeedStats() {
        this(Constants.MIN_SEED_STAT, Constants.MIN_SEED_STAT, Constants.MIN_SEED_STAT);
    }

    public SeedStats(byte growth, byte gain, byte resistance) {
        this(growth, gain, resistance, false);
    }

    public SeedStats(byte growth, byte gain, byte resistance, boolean analyzed) {
        this.growth = (byte) Math.max(Constants.MIN_SEED_STAT, Math.min(Constants.MAX_SEED_STAT, growth));
        this.gain = (byte) Math.max(Constants.MIN_SEED_STAT, Math.min(Constants.MAX_SEED_STAT, gain));
        this.resistance = (byte) Math.max(Constants.MIN_SEED_STAT, Math.min(Constants.MAX_SEED_STAT, resistance));
        this.analyzed = analyzed;
    }

    @Override
    public byte getGrowth() {
        return growth;
    }

    @Override
    public byte getGain() {
        return gain;
    }

    @Override
    public byte getResistance() {
        return resistance;
    }

    public SeedStats copy() {
        return new SeedStats(this.getGrowth(), this.getGain(), this.getResistance(), analyzed);
    }

    public static SeedStats getStatsFromStack(ItemStack stack) {
        if (stack == null || stack.getItem() == null) {
            return null;
        }
        return readFromNBT(stack.getTagCompound());
    }

    public static SeedStats readFromNBT(NBTTagCompound tag) {
        if (tag == null) return new SeedStats();
        byte gr = tag.hasKey(Names.NBT.growth, Data.NBTType._byte) ? tag.getByte(Names.NBT.growth) : 1;
        byte ga = tag.hasKey(Names.NBT.gain, Data.NBTType._byte) ? tag.getByte(Names.NBT.growth) : 1;
        byte re = tag.hasKey(Names.NBT.resistance, Data.NBTType._byte) ? tag.getByte(Names.NBT.resistance) : 1;
        boolean analyzed = tag.hasKey(Names.NBT.analyzed, Data.NBTType._boolean) && tag.getBoolean(Names.NBT.analyzed);
        return new SeedStats(gr, ga, re, analyzed);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setByte(Names.NBT.growth, this.growth);
        tag.setByte(Names.NBT.gain, this.gain);
        tag.setByte(Names.NBT.resistance, this.resistance);
        tag.setBoolean(Names.NBT.analyzed, this.analyzed);
        return tag;
    }

    @Override
    public boolean isAnalyzed() {
        return this.analyzed;
    }

    @Override
    public void setAnalyzed(boolean value) {
        this.analyzed = value;
    }
}
