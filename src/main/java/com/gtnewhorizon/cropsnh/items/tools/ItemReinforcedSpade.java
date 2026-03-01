package com.gtnewhorizon.cropsnh.items.tools;

import com.gtnewhorizon.cropsnh.utility.XSTR;

/**
 * Tool to uproot weeds.
 * Comes in a wooden and iron variant.
 */
public class ItemReinforcedSpade extends ItemSpadeNH {

    public ItemReinforcedSpade() {
        super(1.0F, ToolMaterial.IRON);
    }

    @Override
    protected String getInternalName() {
        return "reinforcedSpade";
    }

    @Override
    protected int getSeedCount(int resist) {
        // every 10 resil you get 1 more seed
        int seedCount = resist / 10;
        // and then you get a percent chance based on the resil eg 11 = 1 seed + 10% for a 2nd
        seedCount += XSTR.XSTR_INSTANCE.nextInt(10) < (resist % 10) ? 1 : 0;
        return seedCount;
    }
}
