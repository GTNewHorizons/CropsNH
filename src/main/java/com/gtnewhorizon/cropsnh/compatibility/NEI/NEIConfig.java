package com.gtnewhorizon.cropsnh.compatibility.NEI;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.AlternateSeedDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.CropRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.DeterministicMutationRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.FertilizerRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.MutationPoolRegistryDumper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.dumpers.SoilRegistryDumper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.GuiRecipeTab;
import gregtech.api.enums.Mods;

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        if (!ModHelper.allowIntegration(Mods.NotEnoughItems.ID)) return;
        // register dumpers
        registerDumpers();
        // register NEI recipe handler
        registerNEITabs();
        // hide crop blocks in NEI
        hideItems();
    }

    private static void registerDumpers() {
        if (!ModHelper.allowIntegration(Mods.NotEnoughItems.ID)) return;
        LogHelper.debug("Registering NEI dumpers");
        API.addOption(new AlternateSeedDumper());
        API.addOption(new CropRegistryDumper());
        API.addOption(new DeterministicMutationRegistryDumper());
        API.addOption(new FertilizerRegistryDumper());
        API.addOption(new MutationPoolRegistryDumper());
        API.addOption(new SoilRegistryDumper());
    }

    private static void registerNEITabs() {
        if (!ModHelper.allowIntegration(Mods.NotEnoughItems.ID)) return;
        LogHelper.debug("Registering NEI recipe tabs");

        // crop product handler
        registerNEIHandler(new NEICropsNHCropHandler());
        // deterministic mutation handler
        registerNEIHandler(new NEICropsNHBreedingHandler());
        // mutation pool handler
        registerNEIHandler(new NEICropsNHMutationPoolHandler());

    }

    private static void registerNEIHandler(CropsNHNEIHandler handler) {
        GuiRecipeTab.handlerMap.put(handler.getOverlayIdentifier(), handler.getHandlerInfo());
        API.registerRecipeHandler(handler);
        API.registerUsageHandler(handler);
    }

    private static void hideItems() {

        LogHelper.debug("Hiding stuff in nei");
        for (int i = 0; i < 16; i++) {
            // hide debugger
            if (!ConfigurationHandler.debug) {
                CropsNH.proxy.hideItemInNEI(new ItemStack(CropsNHItems.debugItem, 1, i));
            }
        }
    }

    @Override
    public String getName() {
        return Reference.MOD_ID + "_NEI";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

}
