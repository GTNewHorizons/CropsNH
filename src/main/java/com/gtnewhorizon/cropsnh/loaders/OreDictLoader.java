package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;

public class OreDictLoader {

    public static void preInit() {
        register(CropsNHItemList.goldfish.get(1), "listAllfishraw");
        register(CropsNHItemList.huckleBerry.get(1), "cropHuckleberry", "listAllberry", "listAllfruit");
        register(CropsNHItemList.sugarBeet.get(1), "cropSugarbeet", "listAllveggie", "listAllrootveggie");
        register(CropsNHItemList.teaLeaf.get(1), "cropTea");
    }

    private static void register(ItemStack stack, String... ores) {
        for (String ore : ores) {
            OreDictionary.registerOre(ore, stack.copy());
        }
    }
}
