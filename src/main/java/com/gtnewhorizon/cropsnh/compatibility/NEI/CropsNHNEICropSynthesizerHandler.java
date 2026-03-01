package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.recipes.backends.CropSynthesizerBackend;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtech.api.enums.ItemList;
import gregtech.api.recipe.RecipeCategory;
import gregtech.common.items.behaviors.BehaviourDataOrb;
import gregtech.nei.GTNEIDefaultHandler;

public class CropsNHNEICropSynthesizerHandler extends GTNEIDefaultHandler {

    public CropsNHNEICropSynthesizerHandler(RecipeCategory recipeCategory) {
        super(recipeCategory);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new CropsNHNEICropSynthesizerHandler(recipeCategory);
    }

    @Override
    public void loadUsageRecipes(ItemStack aInput) {
        if (ItemList.Tool_DataOrb.isStackEqual(aInput, false, true) && BehaviourDataOrb.getDataTitle(aInput)
            .equals(Names.DataOrb.specimen)) {
            ICropCard cc = CropRegistry.instance.get(BehaviourDataOrb.getDataName(aInput));
            if (cc == null) return;
            CropSynthesizerBackend backend = this.getBackend();
            if (backend.cropCacheIndex.containsKey(cc)) {
                arecipes.addAll(backend.cropCacheIndex.get(cc));
            }
            return;
        }
        super.loadUsageRecipes(aInput);
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

        // identify the source recipes
        CropSynthesizerBackend backend = getBackend();
        if (backend.cropCacheIndex.containsKey(seedData.getCrop())) {
            arecipes.addAll(backend.cropCacheIndex.get(seedData.getCrop()));
        }
    }

    private CropSynthesizerBackend getBackend() {
        CropSynthesizerBackend backend = (CropSynthesizerBackend) recipeMap.getBackend();

        // create the rapid lookup table if needed
        if (!backend.cached) {
            for (CachedDefaultRecipe recipe : getCache()) {
                backend.cropCacheIndex
                    .put(recipe.mRecipe.getMetadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY), recipe);
            }
            backend.cached = true;
        }

        return backend;
    }

}
