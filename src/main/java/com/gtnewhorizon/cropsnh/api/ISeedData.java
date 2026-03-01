package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface ISeedData {

    ICropCard getCrop();

    ISeedStats getStats();

    ItemStack getStack();

    void setAnalyzed(boolean analyzed);

    NBTTagCompound writeToNBT();
}
