package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;

import forestry.plugins.PluginCore;
import gregtech.api.enums.Mods;
import ic2.core.Ic2Items;

public class FertilizerLoader {

    public static void postInit() {
        // vanilla bonemeal
        FertilizerRegistry.instance.register(Items.dye, 15, 10);

        // ic2 fertilizer
        if (Mods.IndustrialCraft2.isModLoaded()) {
            ItemStack ic2Fert = Ic2Items.fertilizer;
            FertilizerRegistry.instance.register(ic2Fert.getItem(), ic2Fert.getItemDamage(), 100);
        }

        // forestry fertilizer
        if (Mods.Forestry.isModLoaded()) {
            FertilizerRegistry.instance.register(PluginCore.items.fertilizerCompound, -1, 100);
            FertilizerRegistry.instance.register(PluginCore.items.fertilizerBio, -1, 100);
        }
    }
}
