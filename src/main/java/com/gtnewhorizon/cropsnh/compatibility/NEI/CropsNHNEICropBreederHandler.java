package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.recipes.backends.CropBreederBackend;

import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtech.api.recipe.RecipeCategory;
import gregtech.nei.GTNEIDefaultHandler;

public class CropsNHNEICropBreederHandler extends GTNEIDefaultHandler {

    public CropsNHNEICropBreederHandler(RecipeCategory recipeCategory) {
        super(recipeCategory);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new CropsNHNEICropBreederHandler(recipeCategory);
    }

    @Override
    public void loadUsageRecipes(ItemStack aInput) {
        this.findValidRecipe(aInput, super::loadUsageRecipes, true);
    }

    @Override
    public void loadCraftingRecipes(ItemStack aResult) {
        this.findValidRecipe(aResult, super::loadCraftingRecipes, false);
    }

    private void findValidRecipe(ItemStack aStack, Consumer<ItemStack> superCall, boolean isUsage) {
        ICropCard cc = CropRegistry.instance.get(aStack, false);
        if (cc == null) {
            superCall.accept(aStack);
            return;
        }
        SeedStats seedStats = SeedStats.getStatsFromStack(aStack);
        if (seedStats == null || !seedStats.isAnalyzed()) {
            return;
        }

        // create the rapid lookup table if needed
        CropBreederBackend backend = (CropBreederBackend) recipeMap.getBackend();
        if (!backend.cached) {
            for (CachedDefaultRecipe recipe : getCache()) {
                ICropMutation mutation = recipe.mRecipe
                    .getMetadata(CropsNHGTRecipeMaps.CROPSNH_CROP_MUTATION_METADATAKEY);
                if (mutation == null) continue;
                for (ICropCard parent : mutation.getParents()) {
                    backend.usageRecipeCache.put(parent, recipe);
                }
                backend.craftingRecipeCache.put(mutation.getOutput(), recipe);
            }
            backend.cached = true;
        }

        // identify the source recipes
        if (isUsage) {
            if (backend.usageRecipeCache.containsKey(cc)) {
                arecipes.addAll(backend.usageRecipeCache.get(cc));
            }
        } else {
            if (backend.craftingRecipeCache.containsKey(cc)) {
                arecipes.addAll(backend.craftingRecipeCache.get(cc));
            }
        }

    }
}
