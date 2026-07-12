package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.recipes.backends.SeedGeneratorBackend;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtech.api.recipe.RecipeCategory;
import gregtech.nei.GTNEIDefaultHandler;

public class CropsNHNEISeedGeneratorHandler extends GTNEIDefaultHandler {

    public CropsNHNEISeedGeneratorHandler(RecipeCategory recipeCategory) {
        super(recipeCategory);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new CropsNHNEISeedGeneratorHandler(recipeCategory);
    }

    @Override
    public void loadUsageRecipes(ItemStack input) {
        this.findValidRecipe(input, super::loadUsageRecipes);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        this.findValidRecipe(result, super::loadCraftingRecipes);
    }

    private void findValidRecipe(ItemStack stack, Consumer<ItemStack> superCall) {
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(stack);
        if (seedData == null) {
            superCall.accept(stack);
            return;
        }

        // create the rapid lookup table if needed
        SeedGeneratorBackend backend = (SeedGeneratorBackend) recipeMap.getBackend();
        if (!backend.cached) {
            for (CachedDefaultRecipe recipe : getCache()) {
                backend.cropCacheIndex
                    .put(recipe.mRecipe.getMetadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY), recipe);
            }
            backend.cached = true;
        }

        // identify the source recipes
        if (backend.cropCacheIndex.containsKey(seedData.getCrop())) {
            arecipes.addAll(backend.cropCacheIndex.get(seedData.getCrop()));
        }
    }
}
