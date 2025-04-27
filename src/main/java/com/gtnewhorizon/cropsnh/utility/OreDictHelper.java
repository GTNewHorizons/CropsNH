package com.gtnewhorizon.cropsnh.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class OreDictHelper {

    public static ItemStack getCopiedOreStack(String name) {
        return getCopiedOreStack(name, 1);
    }

    public static ItemStack getCopiedOreStack(String name, int count) {
        ArrayList<ItemStack> ores = OreDictionary.getOres(name);
        if (ores.isEmpty()) return null;
        ItemStack stack = ores.get(ores.size() - 1)
            .copy();
        // always define stack size since the ore item stacks
        // in the ore dictionary are mutable and therefore prone to UB
        stack.stackSize = count;
        return stack;
    }

    private static final Map<String, Block> oreBlocks = new HashMap<>();
    private static final Map<String, Integer> oreBlockMeta = new HashMap<>();

    private static final Map<String, Item> nuggets = new HashMap<>();
    private static final Map<String, Integer> nuggetMeta = new HashMap<>();

    public static Block getOreBlockForName(String name) {
        return oreBlocks.get(name);
    }

    // checks if an itemstack has this ore dictionary entry
    public static boolean hasOreId(ItemStack stack, String tag) {
        if (stack == null || stack.getItem() == null) {
            return false;
        }
        int[] ids = OreDictionary.getOreIDs(stack);
        for (int id : ids) {
            if (OreDictionary.getOreName(id)
                .equals(tag)) {
                return true;
            }
        }
        return false;
    }
}
