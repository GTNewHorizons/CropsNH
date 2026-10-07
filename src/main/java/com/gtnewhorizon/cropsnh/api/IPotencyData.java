package com.gtnewhorizon.cropsnh.api;

public interface IPotencyData extends Comparable<IPotencyData> {

    /**
     * @return The potency amount added when using the item or fluid related to this entry.
     */
    int getPotency();

    /**
     * @apiNote Zero or lower will be interpreted as resorting to the mechanical default.
     * @return The max amount of potency that can be stored when using the item or fluid related to this entry.
     */
    int getMaxStorage();

    /**
     * @apiNote return 0 to go with the mechanic's default
     * @return The max amount of potency that can be stored using a mechanic with an existing storage cap.
     */
    default int getMaxStorage(int defaultMax) {
        if (this.getMaxStorage() <= 0) return defaultMax;
        return Math.min(defaultMax, this.getMaxStorage());
    }

    /**
     * @return The amount of units consumed when using this entry.
     */
    int getUnitsConsumedPerApplication();

    @Override
    default int compareTo(IPotencyData other) {
        // compare potency first
        int comp = Integer.compare(this.getPotency(), other.getPotency());
        if (comp != 0) return comp;
        // then max storage
        comp = Integer.compare(this.getMaxStorage(), other.getMaxStorage());
        if (comp != 0) return comp;
        // then amount per application
        return Integer.compare(this.getUnitsConsumedPerApplication(), other.getUnitsConsumedPerApplication());
    }
}
