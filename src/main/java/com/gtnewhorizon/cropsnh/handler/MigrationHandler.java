package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.handler.migrations.CropsPlusPlusMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.GT5uMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.IC2Migrations;
import com.gtnewhorizon.cropsnh.handler.migrations.SoilMigrations;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.utility.MissingMappingHandler;

public class MigrationHandler {

    /**
     * Handles postea migrations
     */
    public static void postInit() {
        // not related to other mod migrations so except from config
        SoilMigrations.postInit();
        MissingMappingHandler.addItemMapping("cropsnh:CropsnhFertilizer", CropsNHItemList.fertilizerCell.getItem());
        MissingMappingHandler
            .addItemMapping("cropsnh:CropsnhEnrichedFertilizer", CropsNHItemList.enrichedFertilizerCell.getItem());
        // for migrating the content of other mods
        if (!ConfigurationHandler.enableMigrations) return;
        CropsPlusPlusMigrations.postInit();
        IC2Migrations.postInit();
        GT5uMigrations.postInit();
    }

    /**
     * Handles recipe removals
     */
    public static void loadComplete() {
        // not related to other mod migrations so except from config
        SoilMigrations.loadCompleted();
        // for migrating the content of other mods
        if (!ConfigurationHandler.enableMigrations) return;
    }

    public static void addOreDictItemOnlyReplacement(String originalId, int originalMeta, String oreDict) {
        ItemStack replacement = OreDictHelper.getCopiedOreStack(oreDict);
        if (replacement != null)
            ItemStackReplacementManager.addSimpleReplacement(originalId, originalMeta, replacement, true);
    }
}
