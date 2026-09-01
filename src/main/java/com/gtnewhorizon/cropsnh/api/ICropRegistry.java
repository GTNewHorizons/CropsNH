package com.gtnewhorizon.cropsnh.api;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public interface ICropRegistry {

    /**
     * Gets the crop for a given id.
     *
     * @param id The id of the crop.
     * @return The crop if found else null.
     */
    public @Nullable ICropCard get(String id);

    /**
     * Gets the crop for a given item.
     *
     * @param stack The item to be planted.
     * @return The crop if found else null.
     */
    public @Nullable ICropCard get(ItemStack stack);

    /**
     * Gets the crop for a given item.
     *
     * @param stack The item to be planted.
     * @return The crop if found else null.
     */
    public @Nullable ICropCard get(ItemStack stack, boolean allowAlternatives);

    /**
     * Checks if the item is an alternate seed.
     *
     * @param stack The item to check.
     * @return True if the stack is an alternate seed.
     */
    boolean isAlternateSeed(ItemStack stack);

    /**
     * Gets the crop card who registered the item as it's alternate seed.
     *
     * @param stack A stack of some alternate seed.
     * @return The crop card associated with the stack
     */
    public @Nullable ICropCard fromAlternateSeed(ItemStack stack);

    /**
     * Registers the crop.
     *
     * @throws IllegalArgumentException if a duplicate ID is inserted.
     *
     * @param crop The crop to register.
     */
    void register(ICropCard crop);

    /**
     * Updates the known item seed list for stuff like vanilla seeds.
     */
    void updateAlternateSeedList();
}
