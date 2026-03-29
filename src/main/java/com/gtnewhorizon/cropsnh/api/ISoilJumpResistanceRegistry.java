package com.gtnewhorizon.cropsnh.api;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;

public interface ISoilJumpResistanceRegistry {

    /**
     * Sets the resistance for a given soil type.
     *
     * @param blockWithMeta The soil to set the resistance for.
     * @param resistance    The resistance to jumping for the soil. (10000 and higher prevents breaking)
     */
    void setResistance(@Nonnull BlockWithMeta blockWithMeta, int resistance);

    /**
     * Checks if the soil should prevent trampling from falling.
     *
     * @implNote this includes the random roll, so the outcome for values between 1 and 9999 are random.
     *
     * @param block The block to check for
     * @param meta  The meta value of the block to check for
     * @return True if the block shouldn't be getting trampled
     */
    boolean shouldSurvive(@Nonnull Block block, int meta);
}
