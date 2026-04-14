package com.gtnewhorizon.cropsnh.api;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IHarvestDropModifier {

    /**
     * Used to modify the drops harvested from a mature crop
     *
     * @param player      The player doing the harvesting
     * @param heldItem    The item held by the player
     * @param dropTracker The items dropped by the crop.
     * @param cropTE      The crop stick tile holding the harvested crop.
     */
    void modifyCropDrops(EntityPlayer player, ItemStack heldItem, Collection<ItemStack> dropTracker,
        ICropStickTile cropTE);
}
