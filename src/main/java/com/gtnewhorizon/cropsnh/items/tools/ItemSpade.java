package com.gtnewhorizon.cropsnh.items.tools;

import java.util.Collection;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

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
    protected void addSeedDrop(ICropStickTile cropTE, Collection<ItemStack> dropTracker) {
        // just roll the normal chance
        ItemStack drop = cropTE.getSeedDrop();
        if (CropsNHUtils.isStackValid(drop)) dropTracker.add(drop);
    }
}
