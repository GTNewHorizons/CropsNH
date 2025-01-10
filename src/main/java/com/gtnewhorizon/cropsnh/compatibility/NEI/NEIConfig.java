package com.gtnewhorizon.cropsnh.compatibility.NEI;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.CropsNH;
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

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        // register dumpers
        LogHelper.debug("Registering NEI dumpers");
        API.addOption(new AlternateSeedDumper());
        API.addOption(new CropRegistryDumper());
        API.addOption(new DeterministicMutationRegistryDumper());
        API.addOption(new FertilizerRegistryDumper());
        API.addOption(new MutationPoolRegistryDumper());
        API.addOption(new SoilRegistryDumper());
        // register NEI recipe handler
        LogHelper.debug("Registering NEI recipe handlers");
        // mutation handler
        // NEICropMutationHandler mutationHandler = new NEICropMutationHandler();
        // API.registerRecipeHandler(mutationHandler);
        // API.registerUsageHandler(mutationHandler);
        // crop product handler
        // NEICropProductHandler productHandler = new NEICropProductHandler();
        // API.registerRecipeHandler(productHandler);
        // API.registerUsageHandler(productHandler);
        // hide crop blocks in NEI
        hideItems();
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
