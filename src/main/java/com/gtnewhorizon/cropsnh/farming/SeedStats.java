package com.gtnewhorizon.cropsnh.farming;

import static net.minecraftforge.common.util.Constants.NBT;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class SeedStats implements ISeedStats {

    /**
     * The default stats for an analyzed crop. Considered read-only in terms of contents. <b>Do not call set analyzed on
     * this.</b>
     */
    public final static SeedStats DEFAULT_ANALYZED_READONLY = new SeedStats((byte) 1, (byte) 1, (byte) 1, true);

    /**
     * The default stats for a not analyzed crop. Considered read-only in terms of contents. <b>Do not call set analyzed
     * on
     * this.</b>
     */
    public final static SeedStats DEFAULT_NOT_ANALYZED_READONLY = new SeedStats((byte) 1, (byte) 1, (byte) 1, false);

    private final byte growth;
    private final byte gain;
    private final byte resistance;
    private boolean analyzed;

    public SeedStats() {
        this(false);
    }

    public SeedStats(boolean analyzed) {
        this(Constants.MIN_SEED_STAT, Constants.MIN_SEED_STAT, Constants.MIN_SEED_STAT, analyzed);
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
        return this.growth;
    }

    @Override
    public byte getGain() {
        return this.gain;
    }

    @Override
    public byte getResistance() {
        return this.resistance;
    }

    public SeedStats copy() {
        return new SeedStats(this.growth, this.gain, this.resistance, this.analyzed);
    }

    /**
     * @implNote doesn't care what type of item it is, so it can be something other than a generic seed.
     * @param stack The stack to get the stats from.
     * @return The stats associated with the item or null if the stack is null, or it's item is null.
     */
    public static @Nullable SeedStats getStatsFromStack(ItemStack stack) {
        if (CropsNHUtils.isStackInvalidIgnoreStackSize(stack)) {
            return null;
        }
        return readFromNBT(stack.getTagCompound());
    }

    public static SeedStats readFromNBT(NBTTagCompound tag) {
        if (tag == null) return new SeedStats();
        byte gr = tag.hasKey(Names.NBT.growth, NBT.TAG_BYTE) ? tag.getByte(Names.NBT.growth) : 1;
        byte ga = tag.hasKey(Names.NBT.gain, NBT.TAG_BYTE) ? tag.getByte(Names.NBT.gain) : 1;
        byte re = tag.hasKey(Names.NBT.resistance, NBT.TAG_BYTE) ? tag.getByte(Names.NBT.resistance) : 1;
        boolean analyzed = tag.hasKey(Names.NBT.analyzed, NBT.TAG_BYTE) && tag.getBoolean(Names.NBT.analyzed);
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

    @Override
    public String toString() {
        return String.format("gr:%d ga:%d re:%d A:%b", this.growth, this.gain, this.resistance, this.analyzed);
    }

    @Override
    public boolean equals(ISeedStats o) {
        return growth == o.getGrowth() && gain == o.getGain()
            && resistance == o.getResistance()
            && analyzed == o.isAnalyzed();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ISeedStats ss && this.equals(ss);
    }
}
