package com.gtnewhorizon.cropsnh.items.tools;

import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.utility.XSTR;

/**
 * Tool to uproot weeds.
 * Comes in a wooden and iron variant.
 */
public class ItemSpade extends ItemSpadeNH {

    public ItemSpade() {
        super(1.0F, ToolMaterial.STONE);
    }

    @Override
    protected String getInternalName() {
        return "spade";
    }

    @Override
    protected int getSeedCount(int resist) {
        // regular seed chance
        return XSTR.XSTR_INSTANCE.nextInt(Constants.MAX_SEED_STAT) < resist ? 1 : 0;
    }
}
