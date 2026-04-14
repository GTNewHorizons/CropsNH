package com.gtnewhorizon.cropsnh.api;

import net.minecraft.util.StatCollector;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IGrowthRequirement {

    /**
     * @return A short description shown in the seed's tooltip.
     */
    default @NotNull String getDescription() {
        Pair<String, String[]> unloc = this.getUnlocalizedDescription();
        return StatCollector.translateToLocalFormatted(unloc.getLeft(), (Object[]) unloc.getRight());
    }

    /**
     * @return A shorter description shown in NEI.
     */
    default @NotNull String getDescriptionForNEI() {
        Pair<String, String[]> unloc = this.getUnlocalizedDescriptionForNEI();
        return StatCollector.translateToLocalFormatted(unloc.getLeft(), (Object[]) unloc.getRight());
    }

    /**
     * @return A short description shown in the seed's tooltip.
     */
    @NotNull
    Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescription();

    /**
     * @return A shorter description shown in NEI
     */
    default @NotNull Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescriptionForNEI() {
        return this.getUnlocalizedDescription();
    }

}
