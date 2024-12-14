package com.gtnewhorizon.cropsnh;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.growthrequirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.init.Recipes;
import com.gtnewhorizon.cropsnh.loaders.BlockUnderRequirementLoader;
import com.gtnewhorizon.cropsnh.loaders.CropLoader;
import com.gtnewhorizon.cropsnh.loaders.FertilizerLoader;
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
    guiFactory = Reference.GUI_FACTORY_CLASS,
    dependencies = "after:IC2; " + "after:GalacticraftCore; "
        + "after:Mantle; "
        + "after:Forestry; "
        + "after:Natura; "
        + "after:TConstruct; "
        + "after:BiomesOPlenty; "
        + "after:Thaumcraft; "
        + "after:witchery; "
        + "after:gregtech; "
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
        Blocks.init();
        Items.init();
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
        LogHelper.debug("Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void postInit(FMLPostInitializationEvent event) {
        LogHelper.debug("Starting Post-Initialization");
        // Have to do this in postInit because some mods don't register their items/blocks until init
        OreDictLoader.init();
        FertilizerLoader.init();
        SoilLoader.init();
        BlockUnderRequirementLoader.init();
        CropLoader.init();
        BlockUnderRequirement.validateRegistry();

        Recipes.init();
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
    public void onServerStart(FMLServerStartingEvent event) {}

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onMissingMappings(FMLMissingMappingsEvent event) {}
}
