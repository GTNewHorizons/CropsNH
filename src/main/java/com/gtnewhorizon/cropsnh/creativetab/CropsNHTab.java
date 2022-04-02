package com.gtnewhorizon.cropsnh.creativetab;

import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CropsNHTab {
    public static CreativeTabs cropsNHTab = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public Item getTabIconItem() {
            return Items.debugItem;
        }
    };
}
