package com.gtnewhorizon.cropsnh.loaders;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.farming.registries.FertilizerRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;

import forestry.plugins.PluginCore;
import gregtech.api.enums.Mods;

public class FertilizerLoader {

    public static void postInit() {
        // vanilla bonemeal
        FertilizerRegistry.instance.register(Items.dye, 15, 5);

        // cropsNH
        FertilizerRegistry.instance.register(CropsNHItems.fertilizer, OreDictionary.WILDCARD_VALUE, 100);

        // forestry fertilizer
        if (Mods.Forestry.isModLoaded()) {
            FertilizerRegistry.instance.register(PluginCore.items.fertilizerCompound, OreDictionary.WILDCARD_VALUE, 25);
            FertilizerRegistry.instance.register(PluginCore.items.fertilizerBio, OreDictionary.WILDCARD_VALUE, 50);
        }
    }
}
