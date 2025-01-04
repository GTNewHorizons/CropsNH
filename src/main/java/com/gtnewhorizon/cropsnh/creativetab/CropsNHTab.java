package com.gtnewhorizon.cropsnh.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropsNHTab {

    public static CreativeTabs cropsNHTab = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public Item getTabIconItem() {
            return CropsNHItems.debugItem;
        }
    };
}
