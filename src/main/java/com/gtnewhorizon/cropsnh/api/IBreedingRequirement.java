package com.gtnewhorizon.cropsnh.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IBreedingRequirement {

    /**
     * @return A short description shown in the seed's tooltip and in NEI.
     */
    @Nullable
    String getDescription();

    /**
     * @return A complex description shown in NEI dumps.
     */
    @NotNull
    String getDescriptionForMutationDump();
}
