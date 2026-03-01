package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.handler.migrations.CropsPlusPlusMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.GT5uMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.IC2Migrations;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;

public class MigrationHandler {

    /**
     * Handles postea migrations
     */
    public static void postInit() {
        if (!ConfigurationHandler.enableMigrations) return;
        CropsPlusPlusMigrations.postInit();
        IC2Migrations.postInit();
        GT5uMigrations.postInit();
    }

    /**
     * Handles recipe removals
     */
    public static void loadComplete() {
        if (!ConfigurationHandler.enableMigrations) return;
    }

    public static void addOreDictItemOnlyReplacement(String originalId, int originalMeta, String oreDict) {
        ItemStack replacement = OreDictHelper.getCopiedOreStack(oreDict);
        if (replacement != null)
            ItemStackReplacementManager.addSimpleReplacement(originalId, originalMeta, replacement, true);
    }
}
