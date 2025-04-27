package com.gtnewhorizon.cropsnh.api;

import net.minecraft.nbt.NBTTagCompound;

public interface ISeedStats {

    /**
     * @return The growth value of the seed.
     */
    byte getGrowth();

    /**
     * @return The gain value of the seed.
     */
    byte getGain();

    /**
     * @return The resistance value of the seed.
     */
    byte getResistance();

    /**
     * @return if the seed stats are analyzed
     */
    boolean isAnalyzed();

    /**
     * Sets if the stats are analyzed
     *
     * @param value to be set
     */
    void setAnalyzed(boolean value);

    ISeedStats copy();

    NBTTagCompound writeToNBT(NBTTagCompound tag);
}
