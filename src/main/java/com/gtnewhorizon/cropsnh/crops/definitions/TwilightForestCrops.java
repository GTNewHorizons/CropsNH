package com.gtnewhorizon.cropsnh.crops.definitions;

import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.BERRY;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.EMISSIVE;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.TWILIGHT_FOREST;
import static com.gtnewhorizon.cropsnh.api.CropsNHMutationPools.YELLOW;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.HILLS;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.data.CropBuilder;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

public final class TwilightForestCrops {

    private TwilightForestCrops() {}

    public static void register() {
        if (!ModUtils.TwilightForest.isModLoaded()) return;
        CropsNHCrops.Torchberry = CropBuilder.crop("torchberry", 0xAB934D, 0xF5D36E)
            .drop(CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.torchberries", 1, 0), 100_00)
            .seed(CropsNHUtils.getModItem(ModUtils.TwilightForest, "item.torchberries", 1, 0))
            .maxLight(10)
            .likes(FOREST, HILLS)
            .creator("Minepolz320")
            .tier(2)
            .growth(150)
            .stages(1, 3)
            .renderShape(PlantRenderShape.X)
            .pools(YELLOW, TWILIGHT_FOREST, BERRY, EMISSIVE)
            .mutation("glowflower", "bopBerry")
            .register();
    }
}
