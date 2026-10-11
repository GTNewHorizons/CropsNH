package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;

public class MutationLoader {

    public static void postInit() {
        // The pools and mutations are defined with each crop, in crops/definitions.
        // Most of them are:
        // - Mild taxonomic relations according to wikipedia
        // - Based on bee progression
        // - Balance related tier gating
        // - Mildly random guesses
        // - Preventing 2 mutations from having the exact same list of parents.
        //
        // If you have some good botanical knowledge; Feel free to update them to be more accurate.
        CropBuilder.registerBreeding();
    }
}
