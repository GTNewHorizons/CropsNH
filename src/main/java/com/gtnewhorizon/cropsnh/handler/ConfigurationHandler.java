package com.gtnewhorizon.cropsnh.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

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
        public static final String CATEGORY_CROPS = "crops";
        public static final String CATEGORY_COMPATIBILITY = "compatibility";
        public static final String CATEGORY_CLIENT = "clientside config";
        public static final String CATEGORY_RENDERING = "rendering";
    }

    public static Configuration config;
    private static String directory;

    // COMMON
    // ------
    // cropsnh
    public static int cropsPerCraft;
    public static boolean debug;
    // crops
    public static float growthMultiplier;
    public static boolean putAnEndToExistentialDread;
    public static String goldfishScream;
    public static boolean goldfishScreamWhenSteppedOn;
    // weeds
    public static boolean enableWeeds;
    public static boolean weedsWipePlants;
    public static int weedSpawnChance;
    public static int weedSpreadChance;
    // rendering
    public static boolean renderCropPlantsAsTESR;

    // CLIENT
    // ------

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

        if (config.hasChanged()) {
            config.save();
        }
    }

    // read values from the config
    private static void loadConfiguration() {

        // region CATEGORY_CROPSNH

        cropsPerCraft = config.getInt(
            "Crops per craft",
            Categories.CATEGORY_CROPSNH,
            4,
            1,
            4,
            "The number of crops you get per crafting operation");

        debug = config
            .getBoolean("debug", Categories.CATEGORY_CROPSNH, false, "Set to true if you wish to enable debug mode");

        // endregion CATEGORY_CROPSNH

        // region CATEGORY_CROPS

        growthMultiplier = config.getFloat(
            "Growth rate multiplier",
            Categories.CATEGORY_CROPS,
            1.0F,
            0.0F,
            2.0F,
            "This is a global growth rate multiplier");

        putAnEndToExistentialDread = config.getBoolean(
            "Disable crop sounds",
            Categories.CATEGORY_CROPS,
            false,
            "Set to true if you prefer your crops without a side of existential screaming.");

        goldfishScream = config.getString(
            "Goldfish sound",
            Categories.CATEGORY_CROPS,
            "mob.ghast.scream",
            "The noise used for goldfish screams");

        goldfishScreamWhenSteppedOn = config.getBoolean(
            "Goldfish screams when stepped on",
            Categories.CATEGORY_CROPS,
            true,
            "If you are fine with the random screams but not with the EXTREME HOWL that comes with walking on them, turn this off.");

        // endregion CATEGORY_CROPS

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
