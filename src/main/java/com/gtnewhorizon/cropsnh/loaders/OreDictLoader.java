package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.init.Items;

public class OreDictLoader {

    public static void init() {
        register(new ItemStack(Items.goldfish, 1, 0), "listAllfishraw");
        register(new ItemStack(Items.berry, 1, 0), "cropHuckleberry", "listAllberry", "listAllfruit");
        register(new ItemStack(Items.berry, 1, 1), "cropSugarbeet", "listAllveggie", "listAllrootveggie");
    }

    private static void register(ItemStack stack, String... ores) {
        for (String ore : ores) {
            OreDictionary.registerOre(ore, stack.copy());
        }
    }
}
