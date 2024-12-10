package com.gtnewhorizon.cropsnh.init;

import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.items.produce.ItemTerraWart;
import com.gtnewhorizon.cropsnh.items.tools.ItemDebugger;
import com.gtnewhorizon.cropsnh.items.tools.ItemMagnifyingGlass;
import com.gtnewhorizon.cropsnh.items.tools.ItemSpade;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class Items {

    public static Item magnifyingGlass;
    public static Item debugItem;
    public static Item genericSeed;
    public static Item terraWart;
    public static Item spade;

    public static void init() {
        magnifyingGlass = new ItemMagnifyingGlass();
        debugItem = new ItemDebugger();
        terraWart = new ItemTerraWart();
        genericSeed = new ItemGenericSeed();
        spade = new ItemSpade();

        LogHelper.debug("Items Registered");
    }
}
