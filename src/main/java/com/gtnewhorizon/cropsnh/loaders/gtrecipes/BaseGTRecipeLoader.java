package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTRecipeBuilder;

public abstract class BaseGTRecipeLoader {

    private static final ItemStack MISSING = new ItemStack(Blocks.fire).setStackDisplayName("Missing!");

    protected static GTRecipeBuilder recipe(long eut, int seconds, int fraction) {
        int duration = seconds * SECONDS + fraction / 5;
        return GTValues.RA.stdBuilder()
            .eut(eut)
            .duration(duration);
    }

    protected static GTRecipeBuilder ulvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_ULV, seconds, fraction);
    }

    protected static GTRecipeBuilder lvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_LV, seconds, fraction);
    }

    protected static GTRecipeBuilder mvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_MV, seconds, fraction);
    }

    protected static GTRecipeBuilder hvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_HV, seconds, fraction);
    }

    protected static GTRecipeBuilder evRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_EV, seconds, fraction);
    }

    protected static GTRecipeBuilder ivRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_IV, seconds, fraction);
    }

    protected static GTRecipeBuilder luvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_LuV, seconds, fraction);
    }

    protected static GTRecipeBuilder zpmRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_ZPM, seconds, fraction);
    }

    protected static GTRecipeBuilder uhvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_UV, seconds, fraction);
    }

    protected static GTRecipeBuilder uevRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_UV, seconds, fraction);
    }

    protected static GTRecipeBuilder uivRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_UV, seconds, fraction);
    }

    protected static GTRecipeBuilder umvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_UV, seconds, fraction);
    }

    protected static GTRecipeBuilder uxvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_UV, seconds, fraction);
    }

    protected static ItemStack getModItem(String modId, String name, int amount) {
        return getModItem(modId, name, amount, 0);
    }

    protected static ItemStack getModItem(String modId, String name, int amount, int meta) {
        return GTModHandler.getModItem(modId, name, amount, meta, MISSING);
    }

}
