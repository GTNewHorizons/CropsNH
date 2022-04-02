package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemClipper;
import com.gtnewhorizon.cropsnh.items.ItemClipping;
import com.gtnewhorizon.cropsnh.items.ItemCrop;
import com.gtnewhorizon.cropsnh.items.ItemDebugger;
import com.gtnewhorizon.cropsnh.items.ItemHandRake;
import com.gtnewhorizon.cropsnh.items.ItemJournal;
import com.gtnewhorizon.cropsnh.items.ItemMagnifyingGlass;
import com.gtnewhorizon.cropsnh.items.ItemTrowel;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import net.minecraft.item.Item;

public class Items {
    public static Item crops;
    public static Item journal;
    public static Item trowel;
    public static Item magnifyingGlass;
    public static Item debugItem;
    public static Item handRake;
    public static Item clipper;
    public static Item clipping;

    public static void init() {
        crops = new ItemCrop();
        journal = new ItemJournal();
        magnifyingGlass = new ItemMagnifyingGlass();
        debugItem = new ItemDebugger();
        if(ConfigurationHandler.enableTrowel) {
            trowel = new ItemTrowel();
        }
        if (ConfigurationHandler.enableHandRake) {
            handRake = new ItemHandRake();
        }
        if(ConfigurationHandler.enableClipper) {
            clipper = new ItemClipper();
        }
        clipping = new ItemClipping();
        LogHelper.debug("Items Registered");
    }
}
