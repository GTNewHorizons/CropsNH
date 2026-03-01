package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.recipes.backends.CropGeneExtractorBackend;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtech.api.recipe.RecipeCategory;
import gregtech.nei.GTNEIDefaultHandler;

public class CropsNHNEICropGeneExtractorHandler extends GTNEIDefaultHandler {

    public CropsNHNEICropGeneExtractorHandler(RecipeCategory recipeCategory) {
        super(recipeCategory);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new CropsNHNEICropGeneExtractorHandler(recipeCategory);
    }

    @Override
    public void loadUsageRecipes(ItemStack aInput) {
        this.findValidRecipe(aInput, super::loadUsageRecipes);
    }

    @Override
    public void loadCraftingRecipes(ItemStack aResult) {
        this.findValidRecipe(aResult, super::loadCraftingRecipes);
    }

    private void findValidRecipe(ItemStack aStack, Consumer<ItemStack> superCall) {
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(aStack);
        if (seedData == null) {
            superCall.accept(aStack);
            return;
        }

        // create the rapid lookup table if needed
        CropGeneExtractorBackend backend = (CropGeneExtractorBackend) recipeMap.getBackend();
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
