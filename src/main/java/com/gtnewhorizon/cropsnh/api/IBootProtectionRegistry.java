package com.gtnewhorizon.cropsnh.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IBootProtectionRegistry {

    /**
     * Registers a handler that provides protection to crop contact effects.
     *
     * @param item    The item for the boots.
     * @param meta    The meta value of the boots or {@link net.minecraftforge.oredict.OreDictionary#WILDCARD_VALUE} to
     *                ignore meta values.
     * @param handler The handler that determines the amount of protection provided by the item.
     */
    void register(final Item item, final int meta, final ICropsNHBootProtectionHandler handler);

    /**
     * Registers a handler that provides protection to crop contact effects except when a handler already exists.
     *
     * @param item    The item for the boots.
     * @param meta    The meta value of the boots or {@link net.minecraftforge.oredict.OreDictionary#WILDCARD_VALUE} to
     *                ignore meta values.
     * @param handler The handler that determines the amount of protection provided by the item
     * @return True if the handler was registered.
     */
    boolean registerIfAbsent(final Item item, final int meta, final ICropsNHBootProtectionHandler handler);

    /**
     * Gets the protection value provided by a given set of boots.
     *
     * @param stack  The stack of boots being worn.
     * @param entity The entity wearing the boots.
     * @param cropTE The crop being collided with.
     * @return A value between 0 and 1 indicating how much protection was provided by the boots.
     */
    float getProtection(final ItemStack stack, final EntityLivingBase entity, final ICropStickTile cropTE);
}
