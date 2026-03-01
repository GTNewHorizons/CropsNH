package com.gtnewhorizon.cropsnh.farming;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;

public class SeedData implements ISeedData {

    public ICropCard crop;
    public ISeedStats stats;
    public ItemStack stack;

    public SeedData(ICropCard crop, ISeedStats stats) {
        this(crop, stats, crop.getSeedItem(stats));
    }

    public SeedData(ICropCard crop, ISeedStats stats, ItemStack stack) {
        this.crop = crop;
        this.stats = stats;
        this.stack = stack;
    }

    public SeedData(NBTTagCompound tag) {
        if (!tag.hasKey(Names.NBT.crop, Data.NBTType._string)) {
            // fail-safe
            this.crop = CropsNHCrops.Carrot;
        } else {
            this.crop = CropRegistry.instance.get(tag.getString(Names.NBT.crop));
            // fail-safe
            if (this.crop == null) this.crop = CropsNHCrops.Carrot;
        }
        this.stats = SeedStats.readFromNBT(tag);
        this.stack = this.crop.getSeedItem(this.stats);
        this.stack.stackSize = NBTHelper.getInteger(tag, Names.NBT.amount, 1);
    }

    @Override
    public ICropCard getCrop() {
        return crop;
    }

    @Override
    public ISeedStats getStats() {
        return this.stats;
    }

    @Override
    public ItemStack getStack() {
        return this.stack;
    }

    @Override
    public void setAnalyzed(boolean analyzed) {
        this.getStats()
            .setAnalyzed(analyzed);
        ItemStack newStack = this.crop.getSeedItem(this.stats);
        newStack.stackSize = this.stack.stackSize;
        this.stack = newStack;
    }

    @Override
    public NBTTagCompound writeToNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(Names.NBT.crop, this.crop.getId());
        tag.setInteger(Names.NBT.amount, this.stack.stackSize);
        this.stats.writeToNBT(tag);
        return tag;
    }
}
