package com.gtnewhorizon.cropsnh.reference;

/**
 * A class containing the constants and default values used in the coding of the CropsNH mod.
 */
public final class Constants {

    /**
     * The maximum value for any given stat.
     */
    public static final byte MAX_SEED_STAT = 31;

    /**
     * The maximum value for any given stat.
     */
    public static final byte MIN_SEED_STAT = 1;

    /**
     * The minimum growth stat for weeds to start spreading.
     */
    public static final int SPREAD_WEED_WHEN_GROWTH_AT_DEFAULT = 24;

    /**
     * The number of units in a block.
     */
    public static final int WHOLE = 16;

    /**
     * The value of 1/16 as represented in float form.
     * Pre-calculated as to cut back on calculations.
     */
    public static final float UNIT = 1 / (float) WHOLE;

    /**
     * The meta-data value representing a mature crop.
     *
     * Mature = {@value}
     */
    public static final int MATURE = 7;

    /**
     * An array of the possible growth tiers.
     * Ranges from 0-5, with 0 containing the default value.
     */
    public static final int[] GROWTH_TIER = { 50, // Tier 0, a placeholder and default value.
        50, // Tier I
        45, // Tier II
        35, // Tier III
        25, // Tier VI
        20 // Tier V
    };

    /*
     * Default Plant Stats.
     */

    /**
     * The default mutation chance of the crop.
     *
     * Represents the percent chance to mutate on any given tick.
     *
     * Chance = {@value}
     */
    public static final float DEFAULT_MUTATION_CHANCE = 0.2F;

    // constants for positioning item textures on the NEI recipe
    public static final int nei_X_parent1 = 44;
    public static final int nei_X_parent2 = 106;
    public static final int nei_X_result = 75;
    public static final int nei_Y_seeds = 21;
    public static final int nei_Y_soil = 47;
    public static final int nei_Y_base = 68;
}
