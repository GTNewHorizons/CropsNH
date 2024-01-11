package com.gtnewhorizon.cropsnh.api.v1;

import net.minecraft.item.Item;

/**
 * This interface is an object holding data to be applied to a crop after a mutation (be it a spread or an actual
 * mutation) has occurred.
 *
 * First a random number is generated, if it is smaller than the value returned by getChance(), this result will be
 * applied to the cross crop. The crop will have a new plant with 0% growth and the seed's item and meta will be
 * determined by the getSeed() and getMeta() methods. The crop's stats will be determined by the getStats() method.
 */
public interface ICrossOverResult {

    /**
     * @return the Item corresponding to the result of the mutation
     */
    Item getSeed();

    /**
     * @return the meta data corresponding to the result of the mutation
     */
    int getMeta();

    /**
     * Sets the seed and the meta for this result to the arguments
     * 
     * @param seed the new seed
     * @param meta the new meta
     */
    void setSeedAndMeta(Item seed, int meta);

    /**
     * The value returned from this determines if a mutation was successful. In default CropsNH behaviour this depends
     * on the tier of the resulting seed.
     *
     * @return the chance that this result will be successfully applied to the crop, should be in the interval [0, 1]
     *         (0: never successful, 1: always successful)
     */
    double getChance();

    /**
     * Sets the chance for this result to the argument
     * 
     * @param chance the new chance
     */
    void setChance(double chance);

    /**
     * @return an ISeedStats object holding the stats for the new plant obtained through mutation.
     */
    ISeedStats getStats();

    /**
     * Sets the stats for this result to the argument
     * 
     * @param stats the new stats
     */
    void setSeedStats(ISeedStats stats);
}
