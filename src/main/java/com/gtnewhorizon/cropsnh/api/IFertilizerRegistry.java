package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IFertilizerRegistry {

    void register(Item item, int meta, int potency);

    boolean isRegistered(ItemStack stack);

    boolean isRegistered(Item item, int meta);

    int getPotnecy(ItemStack stack);

    int getPotnecy(Item item, int meta);
}
