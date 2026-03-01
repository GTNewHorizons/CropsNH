package com.gtnewhorizon.cropsnh.recipes.backends;

import net.minecraft.item.ItemStack;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBackendPropertiesBuilder;
import gregtech.api.util.GTRecipe;
import gregtech.nei.GTNEIDefaultHandler;

public class CropGeneExtractorBackend extends RecipeMapBackend {

    public final SetMultimap<ICropCard, GTRecipe> cropIndex = LinkedHashMultimap.create();
    public boolean cached = false;
    public final SetMultimap<ICropCard, GTNEIDefaultHandler.CachedDefaultRecipe> cropCacheIndex = LinkedHashMultimap
        .create();

    public CropGeneExtractorBackend(RecipeMapBackendPropertiesBuilder propertiesBuilder) {
        super(propertiesBuilder);
    }

    @Override
    protected GTRecipe addToItemMap(GTRecipe recipe) {
        ICropCard cc = recipe.getMetadata(CropsNHGTRecipeMaps.CROPSNH_CROP_METADATAKEY);
        cropIndex.put(cc, recipe);
        return super.addToItemMap(recipe);
    }

    @Override
    public boolean containsInput(ItemStack item) {
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(item);
        return (seedData != null && cropIndex.containsKey(seedData.getCrop())) || super.containsInput(item);
    }
}
