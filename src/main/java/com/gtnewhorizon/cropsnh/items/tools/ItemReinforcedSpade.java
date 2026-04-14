package com.gtnewhorizon.cropsnh.items.tools;

import java.util.Collection;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
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
    protected void addSeedDrop(ICropStickTile cropTE, Collection<ItemStack> dropTracker) {
        // abort if the crop can't drop seeds for some reason.
        ItemStack seedStack = cropTE.getSeedStack();
        if (seedStack == null) return;

        // every 10 resistance you get 1 more seed
        int resist = cropTE.getSeed()
            .getStats()
            .getResistance();
        seedStack.stackSize = resist / 10;
        // and then you get a percent chance based on the resil eg 11 = 1 seed + 10% for a 2nd
        seedStack.stackSize += XSTR.XSTR_INSTANCE.nextInt(10) < (resist % 10) ? 1 : 0;
        if (seedStack.stackSize > 0) {
            dropTracker.add(seedStack);
        }
    }
}
