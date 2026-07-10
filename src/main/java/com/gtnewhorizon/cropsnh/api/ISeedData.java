package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.jetbrains.annotations.NotNull;

public interface ISeedData {

    @NotNull
    ICropCard getCrop();

    @NotNull
    ISeedStats getStats();

    ItemStack getStack();

    void setAnalyzed(boolean analyzed);

    @NotNull
    NBTTagCompound writeToNBT();
}
