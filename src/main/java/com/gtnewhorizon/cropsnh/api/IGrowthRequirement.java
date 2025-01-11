package com.gtnewhorizon.cropsnh.api;

import com.gtnewhorizon.cropsnh.utility.Tuple2;

public interface IGrowthRequirement {

    /**
     * @return A short description shown in the seed's tooltip.
     */
    String getDescription();

    /**
     * @return A short description shown in the seed's tooltip.
     */
    Tuple2<String, Object[]> getUnlocalisedDescription();

}
