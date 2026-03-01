package com.gtnewhorizon.cropsnh.api;

import org.apache.commons.lang3.tuple.Pair;

public interface IGrowthRequirement {

    /**
     * @return A short description shown in the seed's tooltip.
     */
    String getDescription();

    /**
     * @return A short description shown in the seed's tooltip.
     */
    Pair<String, Object[]> getUnlocalizedDescription();

}
