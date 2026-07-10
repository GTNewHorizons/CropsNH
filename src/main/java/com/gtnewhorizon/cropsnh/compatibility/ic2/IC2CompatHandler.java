package com.gtnewhorizon.cropsnh.compatibility.ic2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import ic2.core.IC2Potion;

public class IC2CompatHandler {

    /**
     * Reduces the duration of the radiation effect on a player by the given duration.
     *
     * @param player    The player to reduce the radiation from.
     * @param reduction How many seconds to take away from the effect.
     */
    public static void reduceRadiationTimer(EntityPlayer player, int reduction) {
        PotionEffect effect = player.getActivePotionEffect(IC2Potion.radiation);
        // Abort if player isn't radiated
        if (effect == null) return;
        // No matter what we are removing it anyway to update it
        player.removePotionEffect(effect.getPotionID());
        // If there is some time remaining, update the timer.
        int remaining = effect.getDuration() - 600;
        if (remaining > 0) {
            PotionEffect newEffect = new PotionEffect(effect.getPotionID(), remaining, effect.getAmplifier());
            // I know IC2 has them built in via it's potion class, but better use what ever was there for consistency.
            newEffect.setCurativeItems(newEffect.getCurativeItems());
            player.addPotionEffect(newEffect);
        }
    }
}
