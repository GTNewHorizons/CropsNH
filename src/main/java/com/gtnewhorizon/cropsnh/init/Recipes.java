package com.gtnewhorizon.cropsnh.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.loaders.GTRecipeLoader;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

    public static void postInit() {
        // crop item
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(CropsNHBlocks.blockCrop, ConfigurationHandler.cropsPerCraft),
                "ss",
                "ss",
                's',
                "stickWood"));
        // TODO: add magnifying glass recipe (copy plant lens)
        // TODO: add spade recipe (copy crop++ spade)

        // TODO: ALL RECIPE THAT LETS OIL BERRY CONVERT OILSANDS INTO OTHER OIL TYPES INSTEAD OF JUST HEAVY OIL

        GTRecipeLoader.PostInit();

    }

}
