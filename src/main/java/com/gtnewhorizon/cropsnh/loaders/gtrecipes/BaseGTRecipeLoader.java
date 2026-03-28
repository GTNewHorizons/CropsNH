package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTRecipeBuilder;

public abstract class BaseGTRecipeLoader {

    private static final ItemStack MISSING = new ItemStack(Blocks.fire).setStackDisplayName("Missing!");

    protected static GTRecipeBuilder recipe(long eut, int seconds, int fraction) {
        int duration = seconds * SECONDS + fraction / 5;
        return recipe(eut, duration);
    }

    protected static GTRecipeBuilder recipe(long eut, int ticks) {
        return GTValues.RA.stdBuilder()
            .eut(eut)
            .duration(ticks);
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

    protected static GTRecipeBuilder ulvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_ULV, ticks);
    }

    protected static GTRecipeBuilder lvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_LV, ticks);
    }

    protected static GTRecipeBuilder mvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_MV, ticks);
    }

    protected static GTRecipeBuilder hvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_HV, ticks);
    }

    protected static GTRecipeBuilder evRecipe(int ticks) {
        return recipe(TierEU.RECIPE_EV, ticks);
    }

    protected static GTRecipeBuilder ivRecipe(int ticks) {
        return recipe(TierEU.RECIPE_IV, ticks);
    }

    protected static GTRecipeBuilder luvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_LuV, ticks);
    }

    protected static GTRecipeBuilder zpmRecipe(int ticks) {
        return recipe(TierEU.RECIPE_ZPM, ticks);
    }

    protected static GTRecipeBuilder uhvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_UV, ticks);
    }

    protected static GTRecipeBuilder uevRecipe(int ticks) {
        return recipe(TierEU.RECIPE_UV, ticks);
    }

    protected static GTRecipeBuilder uivRecipe(int ticks) {
        return recipe(TierEU.RECIPE_UV, ticks);
    }

    protected static GTRecipeBuilder umvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_UV, ticks);
    }

    protected static GTRecipeBuilder uxvRecipe(int ticks) {
        return recipe(TierEU.RECIPE_UV, ticks);
    }

}
