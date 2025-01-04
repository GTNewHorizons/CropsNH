package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.init.CropsNHItems;

public class OreDictLoader {

    public static void init() {
        register(new ItemStack(CropsNHItems.goldfish, 1, 0), "listAllfishraw");
        register(new ItemStack(CropsNHItems.berry, 1, 0), "cropHuckleberry", "listAllberry", "listAllfruit");
        register(new ItemStack(CropsNHItems.berry, 1, 1), "cropSugarbeet", "listAllveggie", "listAllrootveggie");
    }

    private static void register(ItemStack stack, String... ores) {
        for (String ore : ores) {
            OreDictionary.registerOre(ore, stack.copy());
        }
    }
}
