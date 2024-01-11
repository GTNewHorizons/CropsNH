package com.gtnewhorizon.cropsnh.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.tileentity.TileEntitySeedAnalyzer;

public class SlotSeedAnalyzerSeed extends Slot {

    public SlotSeedAnalyzerSeed(IInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileEntitySeedAnalyzer.isValid(stack);
    }
}
