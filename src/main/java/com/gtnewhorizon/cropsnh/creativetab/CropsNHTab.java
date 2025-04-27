package com.gtnewhorizon.cropsnh.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropsNHTab {

    public static CreativeTabs cropsNHTab = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public Item getTabIconItem() {
            return this.getIconItemStack()
                .getItem();
        }

        @Override
        public ItemStack getIconItemStack() {
            return CropsNHItemList.cropSticks.get(1);
        }
    };
}
