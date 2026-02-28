package com.gtnewhorizon.cropsnh.handler.migrations;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.utility.MissingMappingHandler;

public abstract class CropsPlusPlusMigrations {

    public static void postInit() {
        // berry item
        final String berryId = ModUtils.CropsPlusPlus.ID + ":foodBerries";
        MissingMappingHandler.addIgnore(berryId);
        ItemStackReplacementManager.addSimpleReplacement(berryId, 0, CropsNHItems.berry, 0, true);
        ItemStackReplacementManager.addSimpleReplacement(berryId, 1, CropsNHItems.berry, 1, true);
        // potion item
        final String potionId = ModUtils.CropsPlusPlus.ID + ":BppPotions";
        MissingMappingHandler.addIgnore(potionId);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 0, CropsNHItems.bottledAlcohol, 0, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 1, CropsNHItems.bottledAlcohol, 1, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 2, CropsNHItems.bottledAlcohol, 2, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 3, CropsNHItems.bottledAlcohol, 3, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 4, CropsNHItems.bottledAlcohol, 4, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 5, CropsNHItems.bottledAlcohol, 5, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 6, CropsNHItems.bottledAlcohol, 6, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 7, CropsNHItems.bottledAlcohol, 7, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 8, CropsNHItems.bottledAlcohol, 8, true);
        ItemStackReplacementManager.addSimpleReplacement(potionId, 9, CropsNHItems.bottledAlcohol, 9, true);
        // modifier item
        final String modifierId = ModUtils.CropsPlusPlus.ID + ":Modifier";
        MissingMappingHandler.addIgnore(modifierId);
        ItemStackReplacementManager.addSimpleReplacement(modifierId, 0, CropsNHItemList.spaceFlower.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(modifierId, 1, CropsNHItemList.magicEssence.get(1), true);
    }
}
