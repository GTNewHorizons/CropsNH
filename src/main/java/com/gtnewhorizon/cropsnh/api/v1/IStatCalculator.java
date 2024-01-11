package com.gtnewhorizon.cropsnh.api.v1;

import java.util.List;

import net.minecraft.item.Item;

public interface IStatCalculator {

    /**
     * Calculates the stats for a mutation or spread result
     * 
     * @param result   an ItemStack containing the seed of the new plant
     * @param input    a List containing all neighbouring crops
     * @param mutation if a mutation occurred, this is false if the plant simply spread to a cross crop
     * @return an ISeedStats object containing the resulting stats
     */
    ISeedStats calculateStats(Item result, int resultMeta, List<? extends ICrop> input, boolean mutation);
}
