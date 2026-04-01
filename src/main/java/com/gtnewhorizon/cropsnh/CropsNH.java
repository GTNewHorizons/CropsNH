package com.gtnewhorizon.cropsnh;

import com.gtnewhorizon.cropsnh.compatibility.StructureLib.StructureLibCompatHandler;
import com.gtnewhorizon.cropsnh.compatibility.TiC.TiCCompatHandler;
import com.gtnewhorizon.cropsnh.compatibility.forestry.ForestryCompatHandler;
import com.gtnewhorizon.cropsnh.compatibility.waila.WailaRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.handler.CropsNHFurnaceFuelHandler;
import com.gtnewhorizon.cropsnh.handler.MigrationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.loaders.AspectLoader;
import com.gtnewhorizon.cropsnh.loaders.BlockUnderRequirementLoader;
import com.gtnewhorizon.cropsnh.loaders.CropLoader;
import com.gtnewhorizon.cropsnh.loaders.FertilizerLoader;
import com.gtnewhorizon.cropsnh.loaders.GTRecipeLoader;
import com.gtnewhorizon.cropsnh.loaders.MTELoader;
import com.gtnewhorizon.cropsnh.loaders.MutationLoader;
import com.gtnewhorizon.cropsnh.loaders.OreDictLoader;
import com.gtnewhorizon.cropsnh.loaders.SoilLoader;
import com.gtnewhorizon.cropsnh.proxy.IProxy;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropBreeder;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTESeedGenerator;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.util.GTUtility;

/**
 * Hard fork of Agricraft (originally by InfinityRaider) for the GTNH modpack
 * </p>
 *
 * @author InfinityRaider, mitchej123, C0bra5, GTNHTeam
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies =
// spotless:off
    "required-after:" + ModUtils.ModIDs.GregTech + "; "
    + "after:" + ModUtils.ModIDs.GalacticraftCore + "; "
    + "after:" + ModUtils.ModIDs.GTPlusPlus + "; "
    + "after:" + ModUtils.ModIDs.Forestry + "; "
    + "after:" + ModUtils.ModIDs.Natura + "; "
    + "after:" + ModUtils.ModIDs.TinkerConstruct + "; "
    + "after:" + ModUtils.ModIDs.BiomesOPlenty + "; "
    + "after:" + ModUtils.ModIDs.Thaumcraft + "; "
    + "after:" + ModUtils.ModIDs.Witchery + "; "
    + "after:" + ModUtils.ModIDs.TwilightForest + "; "
    + "after:" + ModUtils.ModIDs.StructureLib + "; "
    + "after:" + ModUtils.ModIDs.PamsHarvestCraft + "; "
// spotless:on
)
public class CropsNH {

    @Mod.Instance(Reference.MOD_ID)
    public static CropsNH INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void preInit(FMLPreInitializationEvent event) {
        LogHelper.debug("Starting Pre-Initialization");
        proxy.initConfiguration(event);
        FMLCommonHandler.instance()
            .bus()
            .register(new ConfigurationHandler());
        GTUtility.addTexturePage((byte) Constants.GT_CASING_PAGE);
        CropsNHBlocks.preInit();
        CropsNHItems.preInit();
        CropsNHFluids.preInit();
        OreDictLoader.preInit();
        LogHelper.debug("Pre-Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void init(FMLInitializationEvent event) {
        LogHelper.debug("Starting Initialization");
        proxy.registerEventHandlers();
        proxy.registerRenderers();
        GameRegistry.registerFuelHandler(new CropsNHFurnaceFuelHandler());
        OreDictLoader.init();
        WailaRegistry.onInit();
        TiCCompatHandler.onInit();
        StructureLibCompatHandler.onInit();
        MTELoader.init();
        MTESeedGenerator.init();
        MTECropBreeder.init();
        LogHelper.debug("Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public static void postInit(FMLPostInitializationEvent event) {
        LogHelper.debug("Starting Post-Initialization");
        FertilizerLoader.postInit();
        SoilLoader.postInit();
        BlockUnderRequirementLoader.postInit();
        CropLoader.postInit();
        MutationLoader.postInit();
        AspectLoader.postInit();
        GTRecipeLoader.PostInit();
        MigrationHandler.postInit();
        ForestryCompatHandler.onPostInit();

        LogHelper.debug("Post-Initialization Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        LogHelper.debug("Starting Load-Complete");
        CropLoader.loadComplete();
        MigrationHandler.loadComplete();
        LogHelper.debug("Load-Complete Complete");
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onServerStart(FMLServerStartingEvent event) {
        MutationRegistry.instance.pruneMutationPools();
    }

}
