package com.gtnewhorizon.cropsnh.handler.migrations;

import static com.gtnewhorizon.cropsnh.handler.MigrationHandler.registerSimpleItemTransformer;

import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.util.GTModHandler;

public abstract class CropsPlusPlusMigrations {

    public static void postInit() {
        // berry item
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":foodBerries", 0, CropsNHItems.berry, 0);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":foodBerries", 1, CropsNHItems.berry, 1);
        // potion item
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 0, CropsNHItems.bottledAlcohol, 0);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 1, CropsNHItems.bottledAlcohol, 1);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 2, CropsNHItems.bottledAlcohol, 2);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 3, CropsNHItems.bottledAlcohol, 3);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 4, CropsNHItems.bottledAlcohol, 4);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 5, CropsNHItems.bottledAlcohol, 5);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 6, CropsNHItems.bottledAlcohol, 6);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 7, CropsNHItems.bottledAlcohol, 7);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 8, CropsNHItems.bottledAlcohol, 8);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":BppPotions", 9, CropsNHItems.bottledAlcohol, 9);
        // modifier item
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":Modifier", 0, MaterialLeafLoader.spaceFlower);
        registerSimpleItemTransformer(ModUtils.CropsPlusPlus.ID + ":Modifier", 1, MaterialLeafLoader.magicEssence);
        // I have to do it in here since there can only be one handler per item type.
        registerSimpleItemTransformer(
            ModUtils.CropsPlusPlus.ID + ":Modifier",
            1,
            () -> GTModHandler.getModItem(ModUtils.NewHorizonsCoreMod.ID, "NANCertificate"));
    }
}
