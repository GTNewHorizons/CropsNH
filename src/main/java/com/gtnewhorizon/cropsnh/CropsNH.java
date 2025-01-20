package com.gtnewhorizon.cropsnh;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.init.Recipes;
import com.gtnewhorizon.cropsnh.loaders.BlockUnderRequirementLoader;
import com.gtnewhorizon.cropsnh.loaders.CropLoader;
import com.gtnewhorizon.cropsnh.loaders.FertilizerLoader;
import com.gtnewhorizon.cropsnh.loaders.MTELoader;
import com.gtnewhorizon.cropsnh.loaders.MutationLoader;
import com.gtnewhorizon.cropsnh.loaders.OreDictLoader;
import com.gtnewhorizon.cropsnh.loaders.SoilLoader;
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
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * Hard fork of Agricraft (originally by InfinityRaider) for the GTNH modpack
 * </p>
 *
 * @author InfinityRaider, mitchej123, C0bra5, GTNHTeam
 */
@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION,
    dependencies = "required-after:gregtech; " + "after:IC2; "
        + "after:GalacticraftCore; "
        + "after:Mantle; "
        + "after:Forestry; "
        + "after:Natura; "
        + "after:TConstruct; "
        + "after:BiomesOPlenty; "
        + "after:Thaumcraft; "
        + "after:witchery; "
        + "after:TwilightForest; ")
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
        FMLCommonHandler.instance()
            .bus()
            .register(new ConfigurationHandler());
        ModHelper.findHelpers();
        CropsNHBlocks.preInit();
        CropsNHItems.preInit();
        CropsNHFluids.preInit();
        ModHelper.preInit();
        LogHelper.debug("Pre-Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void init(FMLInitializationEvent event) {
        LogHelper.debug("Starting Initialization");
        proxy.registerEventHandlers();
        proxy.registerRenderers();
        ModHelper.initHelpers();
        MTELoader.init();
        LogHelper.debug("Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void postInit(FMLPostInitializationEvent event) {
        LogHelper.debug("Starting Post-Initialization");
        // Have to do this in postInit because some mods don't register their items/blocks until init
        OreDictLoader.postInit();
        FertilizerLoader.postInit();
        SoilLoader.postInit();
        BlockUnderRequirementLoader.postInit();
        CropLoader.postInit();
        BlockUnderRequirement.validateRegistry();
        MutationLoader.postInit();

        Recipes.postInit();
        ModHelper.postInit();

        LogHelper.debug("Post-Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onServerAboutToStart(FMLServerAboutToStartEvent event) {

        // NEIHelper.setServerConfigs();
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onServerStart(FMLServerStartingEvent event) {
        MutationRegistry.instance.pruneMutationPools();
    }

    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {}

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onMissingMappings(FMLMissingMappingsEvent event) {}
}
