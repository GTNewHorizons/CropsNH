package com.gtnewhorizon.cropsnh.init;

import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemDebugger;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.items.ItemHandRake;
import com.gtnewhorizon.cropsnh.items.ItemMagnifyingGlass;
import com.gtnewhorizon.cropsnh.items.ItemTerraWart;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class Items {

    public static Item magnifyingGlass;
    public static Item debugItem;
    public static Item handRake;
    public static Item genericSeed;
    public static Item terraWart;
    public static Item cropSticks;

    public static void init() {
        magnifyingGlass = new ItemMagnifyingGlass();
        debugItem = new ItemDebugger();
        terraWart = new ItemTerraWart();
        genericSeed = new ItemGenericSeed();

        if (ConfigurationHandler.enableHandRake) {
            handRake = new ItemHandRake();
        }
        LogHelper.debug("Items Registered");
    }
}
