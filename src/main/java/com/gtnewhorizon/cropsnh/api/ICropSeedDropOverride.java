package com.gtnewhorizon.cropsnh.api;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICropSeedDropOverride {

    /**
     * Modifies the seed drop when breaking a crop stick with a crop in it.
     *
     * @param player       The player who broke the crop.
     * @param heldItem     The stack held by the player.
     * @param dropTracker  The list of drops if the crop was mature
     * @param cropTE       The crop stick tile entity being interacted with.
     * @param hasHarvested True if the crop was also harvested
     * @return True to override/prevent the default seed drop mechanics.
     */
    boolean overrideSeedDrop(EntityPlayer player, ItemStack heldItem, Collection<ItemStack> dropTracker,
        ICropStickTile cropTE, boolean hasHarvested);
}
