package com.gtnewhorizon.cropsnh;

import java.awt.Color;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;

public class TestCrop extends NHCropCard {

    private static final Item ITEM = new ItemGenericSeed(false);

    public TestCrop(String id) {
        super(id, Color.MAGENTA, Color.BLACK);
    }

    @Override
    public ItemStack getSeedItem(ISeedStats stats) {
        // create seed with tags
        ItemStack seed = new ItemStack(ITEM, 1, 0);
        seed.setTagCompound(writeNBT(this, stats));
        return seed;
    }

    @Override
    public boolean hideFromNEI() {
        // if these were to leak to live gameplay they shouldn't appear in nei.
        return true;
    }
}
