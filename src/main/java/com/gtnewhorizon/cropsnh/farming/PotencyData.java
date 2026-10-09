package com.gtnewhorizon.cropsnh.farming;

import com.gtnewhorizon.cropsnh.api.IPotencyData;

public class PotencyData implements IPotencyData {

    /** The amount of potency to add per application. */
    private final int potency;
    /** The maximum amount of potency that can be stored using this entry. */
    private final int maxStorage;
    /** How many units of X are consumed per application. */
    private final int unitsConsumedPerApplication;

    /**
     * Creates a basic potency with no fixed storage cap, defaulting to 1 unit per application.
     * 
     * @param potency The amount of potency to add per application.
     */
    public PotencyData(int potency) {
        this(potency, 0, 1);
    }

    /**
     * Creates a potency entry with the given potency and cap, defaulting to 1 unit per application.
     * 
     * @param potency    The amount of potency to add per application.
     * @param maxStorage The maximum amount of potency that can be stored using this entry.
     */
    public PotencyData(int potency, int maxStorage) {
        this(potency, maxStorage, 1);
    }

    /**
     * Creates a complex potency registry that species a cap
     * 
     * @param potency                     The amount of potency to add per application.
     * @param maxStorage                  The maximum amount of potency that can be stored using this entry.
     * @param unitsConsumedPerApplication How many units are consumed per application.
     */
    public PotencyData(int potency, int maxStorage, int unitsConsumedPerApplication) {
        this.potency = potency;
        this.maxStorage = maxStorage;
        this.unitsConsumedPerApplication = unitsConsumedPerApplication;
    }

    @Override
    public int getPotency() {
        return this.potency;
    }

    @Override
    public int getMaxStorage() {
        return this.maxStorage;
    }

    @Override
    public int getUnitsConsumedPerApplication() {
        return this.unitsConsumedPerApplication;
    }
}
