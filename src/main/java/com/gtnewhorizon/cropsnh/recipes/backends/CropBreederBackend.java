package com.gtnewhorizon.cropsnh.recipes.backends;

import net.minecraft.item.ItemStack;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.recipes.CropsNHGTRecipeMaps;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBackendPropertiesBuilder;
import gregtech.api.util.GTRecipe;
import gregtech.nei.GTNEIDefaultHandler;

public class CropBreederBackend extends RecipeMapBackend {

    public final SetMultimap<ICropCard, GTRecipe> cropIndex = LinkedHashMultimap.create();
    public boolean cached = false;
    public final SetMultimap<ICropCard, GTNEIDefaultHandler.CachedDefaultRecipe> usageRecipeCache = LinkedHashMultimap
        .create();
    public final SetMultimap<ICropCard, GTNEIDefaultHandler.CachedDefaultRecipe> craftingRecipeCache = LinkedHashMultimap
        .create();

    public CropBreederBackend(RecipeMapBackendPropertiesBuilder propertiesBuilder) {
        super(propertiesBuilder);
    }

    @Override
    protected GTRecipe addToItemMap(GTRecipe recipe) {
        ICropMutation mutation = recipe.getMetadata(CropsNHGTRecipeMaps.CROPSNH_CROP_MUTATION_METADATAKEY);
        if (mutation == null) {
            throw new LoaderException(
                "Attempted to add a recipe to a crop breeder recipe map that doesn't have the mutation metadata!");
        }
        for (ICropCard cc : mutation.getParents()) {
            cropIndex.put(cc, recipe);
        }
        return super.addToItemMap(recipe);
    }

    @Override
    public boolean containsInput(ItemStack item) {
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(item);
        return seedData != null ? cropIndex.containsKey(seedData.getCrop()) : super.containsInput(item);
    }
}
