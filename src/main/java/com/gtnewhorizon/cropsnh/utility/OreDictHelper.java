package com.gtnewhorizon.cropsnh.utility;

import java.util.ArrayList;

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
}
