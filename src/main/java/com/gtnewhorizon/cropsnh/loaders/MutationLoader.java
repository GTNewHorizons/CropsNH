package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.init.CropsNHCrops;

public class MutationLoader {

    public static void postInit() {
        CropsNHCrops.registerMutations();
    }
}
