package com.gtnewhorizon.cropsnh.farming;

import com.gtnewhorizon.cropsnh.api.v1.ISeedStats;
import com.gtnewhorizon.cropsnh.api.v1.ITrowel;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlantStats implements ISeedStats {
    private static final short MAX = (short) ConfigurationHandler.cropStatCap;
    private static final short MIN = 1;

    private short growth;
    private short gain;
    private short strength;
    private boolean analyzed;
    private int stage;
    private int growthProgress;
    private int fertilizer;
    private int hydration;
    private int weedEx;


	public PlantStats() {
        this(MIN, MIN, MIN, false, 0, 0, 0, 0, 0);
    }
    
    public PlantStats(int growth, int gain, int strength) {
        this(growth, gain, strength, false, 0, 0, 0, 0, 0);
    }
    
    public PlantStats(int growth, int gain, int strength, boolean analyzed) {
        this(growth, gain, strength, analyzed, 0, 0, 0, 0, 0);
    }

    public PlantStats(int growth, int gain, int strength, boolean analyzed, int stage, int growthProgress, int fertilizer, int hydration, int weedEx) {
        this.setStats(growth, gain, strength);
        this.analyzed = analyzed;
        this.stage = stage;
        this.growthProgress = growthProgress;
        this.fertilizer = fertilizer;
        this.hydration = hydration;
        this.weedEx = weedEx;
    }

    public void setStats(int growth, int gain, int strength) {
        setGrowth(growth);
        setGain(gain);
        setStrength(strength);
    }

    public short getGrowth() {
        return growth;
    }

    public short getGain() {
        return gain;
    }

    public short getStrength() {
        return strength;
    }
    
    public int getStage() {
    	return stage;
    }
    public int getGrowthProgress() {
    	return growthProgress;
    }

    public int getFertilizer() {
    	return fertilizer;
    }
    
    public int getHydration() {
    	return hydration;
    }
    

    public int getWeedEx() {
		return weedEx;
	}

	public void setWeedEx(int weedEx) {
		this.weedEx = weedEx;
	}
    

    @Override
    public short getMaxGrowth() {
        return MAX;
    }

    @Override
    public short getMaxGain() {
        return MAX;
    }

    @Override
    public short getMaxStrength() {
        return MAX;
    }

    public void setGrowth(int growth) {
        this.growth = moveIntoBounds(growth);
    }

    public void setGain(int gain) {
        this.gain = moveIntoBounds(gain);
    }

    public void setStrength(int strength) {
        this.strength = moveIntoBounds(strength);
    }
    
    public void setStage(int stage) {
    	this.stage = stage;
    }
    
    public void setGrowthProgress(int growthProgress) {
    	this.growthProgress = growthProgress;
    }
    
    public void setFertilizer(int fertilizer)
    {
    	this.fertilizer = fertilizer;
    }
    
    public void setHydration(int hydration)
    {
    	this.hydration = hydration;
    }
    

    private short moveIntoBounds(int stat) {
        int lowerLimit = Math.max(MIN, stat);
        return (short) Math.min(MAX, lowerLimit);
    }

    public PlantStats copy() {
        return new PlantStats(getGrowth(), getGain(), getStrength(), analyzed, stage, growthProgress, fertilizer, hydration, weedEx);
    }

    public static PlantStats getStatsFromStack(ItemStack stack) {
        if(stack==null || stack.getItem()==null) {
            return null;
        }
        if(stack.getItem() instanceof ITrowel) {
            ((ITrowel) stack.getItem()).getStats(stack);
        }
        return readFromNBT(stack.getTagCompound());
    }

    public static PlantStats readFromNBT(NBTTagCompound tag) {
        if(tag !=null && tag.hasKey(Names.NBT.growth) && tag.hasKey(Names.NBT.gain) && tag.hasKey(Names.NBT.strength)) {
            PlantStats stats = new PlantStats();
            stats.setGrowth(tag.getShort(Names.NBT.growth));
            stats.setGain(tag.getShort(Names.NBT.gain));
            stats.setStrength(tag.getShort(Names.NBT.strength));
            stats.analyzed=tag.hasKey(Names.NBT.analyzed) && tag.getBoolean(Names.NBT.analyzed);
            stats.setStage(tag.getInteger(Names.NBT.stage));
            stats.setGrowthProgress(tag.getInteger(Names.NBT.growthProgress));
            stats.setFertilizer(tag.getInteger(Names.NBT.fertilizer));
            stats.setHydration(tag.getInteger(Names.NBT.hydration));
            stats.setWeedEx(tag.getInteger(Names.NBT.weedEx));
            return stats;
        }
        return null;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setShort(Names.NBT.growth, growth);
        tag.setShort(Names.NBT.gain, gain);
        tag.setShort(Names.NBT.strength, strength);
        tag.setBoolean(Names.NBT.analyzed, analyzed);
        tag.setInteger(Names.NBT.stage, stage);
        tag.setInteger(Names.NBT.growthProgress, growthProgress);
        tag.setInteger(Names.NBT.fertilizer, fertilizer);
        tag.setInteger(Names.NBT.hydration, hydration);
        tag.setInteger(Names.NBT.weedEx, weedEx);
        return tag;
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
