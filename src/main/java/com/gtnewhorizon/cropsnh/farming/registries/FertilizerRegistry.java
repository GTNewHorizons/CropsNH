package com.gtnewhorizon.cropsnh.farming.registries;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.IFertilizerRegistry;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

public class FertilizerRegistry implements IFertilizerRegistry {

    public static final FertilizerRegistry instance = new FertilizerRegistry();

    /**
     * A list of fertilizers along with their potency.
     */
    public MetaMap<Item, Integer> fertilisers = new MetaMap<>();

    @Override
    public void register(Item item, int meta, int potency) {
        this.fertilisers.putIfAbsent(item, meta, potency);
    }

    @Override
    public boolean isRegistered(ItemStack item) {
        return this.isRegistered(item.getItem(), item.getItemDamage());
    }

    @Override
    public boolean isRegistered(Item item, int meta) {
        return this.fertilisers.containsKey(item, meta);
    }

    @Override
    public int getPotnecy(ItemStack item) {
        return this.getPotnecy(item.getItem(), item.getItemDamage());
    }

    @Override
    public int getPotnecy(Item item, int meta) {
        return this.fertilisers.getOrDefault(item, meta, 0);
    }
}
