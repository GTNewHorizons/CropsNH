package com.gtnewhorizon.cropsnh.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ConfigurationHandler {

    public static class Categories {

        public static final String CATEGORY_WEEDS = "weeds";
        public static final String CATEGORY_CROPSNH = "cropsnh";
        public static final String CATEGORY_FARMING = "farming";
        public static final String CATEGORY_TOOLS = "tools";
        public static final String CATEGORY_DEBUG = "debug";
        public static final String CATEGORY_WORLDGEN = "world gen";
        public static final String CATEGORY_IRRIGATION = "irrigation";
        public static final String CATEGORY_STORAGE = "storage";
        public static final String CATEGORY_DECORATION = "decoration";
        public static final String CATEGORY_COMPATIBILITY = "compatibility";
        public static final String CATEGORY_CLIENT = "clientside config";
        public static final String CATEGORY_RENDERING = "rendering";
    }

    public static Configuration config;
    private static String directory;
    private static Property propGenerateDefaults = new Property("RegenDefaults", "false", Property.Type.BOOLEAN);

    // COMMON
    // ------
    // cropsnh
    public static boolean generateDefaults;
    public static int cropsPerCraft;
    public static boolean resourcePlants;
    public static float growthMultiplier;
    public static boolean debug;
    // weeds
    public static boolean enableWeeds;
    public static boolean weedsWipePlants;
    public static int weedSpawnChance;
    public static int weedSpreadChance;
    // rendering
    public static boolean renderCropPlantsAsTESR;

    // CLIENT
    // ------
    public static boolean condenseCustomWoodInNei;
    public static boolean disableParticles;
    public static String statDisplay;
    public static boolean disableSounds;

    public static void init(FMLPreInitializationEvent event) {
        checkAndCreateConfig(event);
        loadConfiguration();
        LogHelper.debug("Configuration Loaded");
    }

    private static void checkAndCreateConfig(FMLPreInitializationEvent event) {
        if (config == null) {
            directory = event.getModConfigurationDirectory()
                .toString() + '/'
                + Reference.MOD_ID.toLowerCase()
                + '/';
            config = new Configuration(new File(directory, "Configuration.cfg"));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initClientConfigs(FMLPreInitializationEvent event) {
        checkAndCreateConfig(event);

        condenseCustomWoodInNei = config.getBoolean(
            "condense custom wood blocks in NEI",
            Categories.CATEGORY_CLIENT,
            true,
            "set to true to condense all entries for custom wood blocks into one entry in NEI");
        disableParticles = config.getBoolean(
            "Disable particles",
            Categories.CATEGORY_CLIENT,
            false,
            "set to true to disable particles for the sprinklers");
        statDisplay = config.getString(
            "Stat Display",
            Categories.CATEGORY_CLIENT,
            "NUMBER",
            "This defines how to display the stats of plants. Possible settings are 'NUMBER': just display a simple number (ex: \"6\"), 'FRACTION': number/maximum (ex: \"6/10\"), 'CHARACTER-'char'': number of characters equal to the stats (ex: CHARACTER-� will give \"������\") and 'KEYWORD-'type'-'keyword'': keyword followed by the type and then the stat, type is any of the previous types (ex: KEYWORD-FRACTION-Rank will give \"Rank: 6/10\") . Invalid entries will default to NUMBER ");
        disableSounds = config
            .getBoolean("Disable sounds", Categories.CATEGORY_CLIENT, false, "Set to true to disable sounds.");

        if (config.hasChanged()) {
            config.save();
        }
    }

    // read values from the config
    private static void loadConfiguration() {

        // region CATEGORY_CROPSNH

        resourcePlants = config.getBoolean(
            "Resource Crops",
            Categories.CATEGORY_CROPSNH,
            true,
            "set to true if you wish to enable resource crops");

        cropsPerCraft = config.getInt(
            "Crops per craft",
            Categories.CATEGORY_CROPSNH,
            4,
            1,
            4,
            "The number of crops you get per crafting operation");

        propGenerateDefaults = config.get(
            Categories.CATEGORY_CROPSNH,
            "GenerateDefaults",
            false,
            "set to true to regenerate a default mutations file (will turn back to false afterwards)");

        generateDefaults = propGenerateDefaults.getBoolean();

        // debug mode
        debug = config
            .getBoolean("debug", Categories.CATEGORY_DEBUG, false, "Set to true if you wish to enable debug mode");

        growthMultiplier = config.getFloat(
            "Growth rate multiplier",
            Categories.CATEGORY_FARMING,
            1.0F,
            0.0F,
            2.0F,
            "This is a global growth rate multiplier");

        // endregion CATEGORY_CROPSNH

        // region CATEGORY_WEEDS

        enableWeeds = config
            .getBoolean("Enable weeds", Categories.CATEGORY_WEEDS, true, "set to false if you wish to disable weeds");

        weedSpawnChance = config.getInt(
            "Weed Spread Chance",
            Categories.CATEGORY_WEEDS,
            100,
            1,
            Integer.MAX_VALUE,
            "Lower values increase the speed at which weeds spawn in empty crop sticks. actual chance is measured as 1 / value.");

        weedSpreadChance = config.getInt(
            "Weed Spread Chance",
            Categories.CATEGORY_WEEDS,
            50,
            2,
            Integer.MAX_VALUE,
            "Lower values increase the speed at which crops spread weeds, actual chance is (rand(value)-growth) <= 2.");

        weedsWipePlants = enableWeeds && config.getBoolean(
            "Weeds can overtake plants",
            Categories.CATEGORY_WEEDS,
            true,
            "Set to false if you don't want weeds to be able to overgrow other plants.");

        // endregion CATEGORY_WEEDS

        // rendering
        renderCropPlantsAsTESR = config.getBoolean(
            "Crop rendering setting",
            Categories.CATEGORY_RENDERING,
            false,
            "When rendering crops, the default (false) is that the plants will only be re-rendered whenever the chunk updates, "
                + "this basically means that whenever a crop grows it causes the chunk containing the plant to re-rendered.\n"
                + "For small farms this is the suggested approach, however for large farms, it is possible that a crop grows almost every tick, "
                + "resulting in  re-rendering the chunk every tick, possibly causing huge FPS drops.\n"
                + "When setting this to true, there will no longer be chunk updates when a crop grows, but the rendering will be different: "
                + "The plant will be rendered every tick (the sticks itself will still be rendered the default way), for small farms this is a bad approach,"
                + "for large farms as well, but it might result in better FPS compared to the default.\n"
                + "I recommend leaving this on false, if you have FPS problems, set this to true and see for yourself if it is an improvement or not.\n"
                + "This config setting must match on server and client, the server should know if it should cause block updates and the client has to know how to render the crops");

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static boolean enableModCompatibility(String modId) {
        boolean flag = config.getBoolean(
            modId,
            Categories.CATEGORY_COMPATIBILITY,
            true,
            "set to false to disable compatibility for " + modId);
        if (config.hasChanged()) {
            config.save();
        }
        return flag;
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Reference.MOD_ID)) {
            loadConfiguration();
            LogHelper.debug("Configuration reloaded.");
        }
    }
}
