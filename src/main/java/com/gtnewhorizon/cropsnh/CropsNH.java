package com.gtnewhorizon.cropsnh;

import java.util.ArrayList;

import com.gtnewhorizon.cropsnh.apiimpl.APISelector;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.compatibility.NEI.NEIHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.growthrequirement.GrowthRequirementHandler;
import com.gtnewhorizon.cropsnh.farming.mutation.MutationHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.handler.GuiHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.CropProducts;
import com.gtnewhorizon.cropsnh.init.Crops;
import com.gtnewhorizon.cropsnh.init.CustomCrops;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.init.Recipes;
import com.gtnewhorizon.cropsnh.init.ResourceCrops;
import com.gtnewhorizon.cropsnh.network.NetworkWrapperCropsNH;
import com.gtnewhorizon.cropsnh.proxy.IProxy;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Hard fork of Agricraft (originally by InfinityRaider) for the GTNH modpack
 * </p>
 * 
 * @author InfinityRaider, mitchej123, GTNHTeam
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION,
        guiFactory = Reference.GUI_FACTORY_CLASS)
public class CropsNH {

    @Mod.Instance(Reference.MOD_ID)
    public static CropsNH instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void preInit(FMLPreInitializationEvent event) {
        LogHelper.debug("Starting Pre-Initialization");
        NetworkWrapperCropsNH.init();
        proxy.initConfiguration(event);
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        ModHelper.findHelpers();
        Blocks.init();
        Crops.init();
        Items.init();
        APISelector.init();
        ModHelper.preInit();
        LogHelper.debug("Pre-Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void init(FMLInitializationEvent event) {
        LogHelper.debug("Starting Initialization");
        proxy.registerEventHandlers();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.registerRenderers();
        ModHelper.initHelpers();
        LogHelper.debug("Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void postInit(FMLPostInitializationEvent event) {
        LogHelper.debug("Starting Post-Initialization");
        // Have to do this in postInit because some mods don't register their items/blocks until init
        ResourceCrops.init();
        CustomCrops.init();
        Recipes.init();
        GrowthRequirementHandler.init();
        CropPlantHandler.init();
        CropProducts.init();
        CustomCrops.initGrassSeeds();
        ModHelper.postInit();
        LogHelper.debug("Post-Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        MutationHandler.getInstance().init();
        NEIHelper.setServerConfigs();
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onServerStart(FMLServerStartingEvent event) {}

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onMissingMappings(FMLMissingMappingsEvent event) {
        ArrayList<String> removedIds = new ArrayList<>();
        removedIds.add("CropsNH:cropMelon");
        removedIds.add("CropsNH:cropPumpkin");
        removedIds.add("CropsNH:sprinklerItem");
        for (FMLMissingMappingsEvent.MissingMapping missingMapping : event.get()) {
            if (removedIds.contains(missingMapping.name)) {
                missingMapping.ignore();
            }
        }
    }
}
