package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemPotencyRegistry {

    void register(Item item, int meta, int potency);

    boolean isRegistered(ItemStack stack);

    boolean isRegistered(Item item, int meta);

    int getPotency(ItemStack stack);

    int getPotency(Item item, int meta);
}
