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
     * The amount of strong poisonous brew that a weedex represents.
     */
    public static final int WEEDEX_CAPACITY = 750;

    /**
     * The amount of fertilizer liquid 1 fertilizer item should turn into.
     */
    public static final int FERTILIZER_ITEM_FLUID_VALUE = 144;
}
