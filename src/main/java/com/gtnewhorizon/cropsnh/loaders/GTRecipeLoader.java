package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.loaders.gtrecipes.ChemicalReactorRecipes;
import com.gtnewhorizon.cropsnh.loaders.gtrecipes.CropRecipes;
import com.gtnewhorizon.cropsnh.loaders.gtrecipes.MixerRecipes;

public class GTRecipeLoader {

    public static void PostInit() {
        CropRecipes.postInit();
        ChemicalReactorRecipes.postInit();
        MixerRecipes.postInit();
    }
}
