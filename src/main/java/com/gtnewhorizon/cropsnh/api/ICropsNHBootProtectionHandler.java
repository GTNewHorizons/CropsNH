package com.gtnewhorizon.cropsnh.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public interface ICropsNHBootProtectionHandler {

    /**
     * Checks if a given stack can protect the player.
     *
     * @param stack  The stack of boots being worn by the player
     * @param entity The entity wearing the boots.
     * @param cropTE The crop stick containing the crop the entity is colliding with.
     *
     * @return a value ranging from 0.0 to 1.0 that define the protection percentage given by the item.
     */
    float getProtection(@NotNull final ItemStack stack, @NotNull final EntityLivingBase entity,
        @NotNull final ICropStickTile cropTE);

    /**
     * @return The string used to display the boot protection value when dumping handler in nei.
     */
    String getDumpText();

}
