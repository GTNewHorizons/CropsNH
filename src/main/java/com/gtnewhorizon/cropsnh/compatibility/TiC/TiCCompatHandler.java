package com.gtnewhorizon.cropsnh.compatibility.TiC;

import com.gtnewhorizon.cropsnh.utility.ModUtils;

import tconstruct.api.harvesting.CropHarvestHandlers;

public class TiCCompatHandler {

    public static void onInit() {
        if (!ModUtils.TinkerConstruct.isModLoaded()) return;
        CropHarvestHandlers.registerCropHarvestHandler(new CropsNHTiCHarvestHandler());
    }
}
