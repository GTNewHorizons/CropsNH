package com.gtnewhorizon.cropsnh.farming;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;

public class SeedData implements ISeedData {

    public @NotNull ICropCard crop;
    public @NotNull ISeedStats stats;
    /**
     * Not necessarily representative of the item that was used to create this stack, but it's stack size is
     * representative, and can be trusted to be carried though. This is intended to be used for quickly resolving
     * stacks more than anything else. Might want to actually turn this into an int field for the amount down the line.
     */
    public ItemStack stack;

    public SeedData(@NotNull ICropCard crop, @NotNull ISeedStats stats) {
        this(crop, stats, crop.getSeedItem(stats));
    }

    public SeedData(@NotNull ICropCard crop, @NotNull ISeedStats stats, ItemStack stack) {
        this.crop = crop;
        this.stats = stats;
        this.stack = stack;
    }

    public SeedData(NBTTagCompound tag) {
        // if we ever do a change to how we store seed data, we also need to update the soil migration handler
        if (!tag.hasKey(Names.NBT.crop, Constants.NBT.TAG_STRING)) {
            // fail-safe
            this.crop = CropsNHUtils.getFallbackCrop();
        } else {
            final ICropCard cc = CropRegistry.instance.get(tag.getString(Names.NBT.crop));
            // fail-safe
            this.crop = cc == null ? CropsNHUtils.getFallbackCrop() : cc;
        }
        this.stats = SeedStats.readFromNBT(tag);
        // Only the stack size of the stack is meant to be cared about, the actual item shouldn't or should be stored
        // separately. Seed data is more of a crop, stat and amount container and not an item container.
        this.stack = this.crop.getSeedItem(this.stats);
        this.stack.stackSize = NBTHelper.getInteger(tag, Names.NBT.amount, 1);
    }

    @Override
    public @NotNull ICropCard getCrop() {
        return crop;
    }

    @Override
    public @NotNull ISeedStats getStats() {
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
    public @NotNull NBTTagCompound writeToNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(Names.NBT.crop, this.crop.getId());
        // Only the stack size of the stack is meant to be cared about, the actual item shouldn't or should be stored
        // separately. Seed data is more of a crop, stat and amount container and not an item container.
        tag.setInteger(Names.NBT.amount, this.stack.stackSize);
        this.stats.writeToNBT(tag);
        return tag;
    }
}
