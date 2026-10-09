package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;

public interface IItemPotencyRegistry {

    void register(Item item, int meta, IPotencyData potency);

    boolean isRegistered(ItemStack stack);

    boolean isRegistered(Item item, int meta);

    @Nullable
    IPotencyData getPotency(ItemStack stack);

    @Nullable
    IPotencyData getPotency(Item item, int meta);
}
