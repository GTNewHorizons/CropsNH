package com.gtnewhorizon.cropsnh.handler;

import java.io.File;
import java.util.Arrays;

import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropManager;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ConfigurationHandler {

    public static class Categories {

        public static final String CATEGORY_WEEDS = "weeds";
        public static final String CATEGORY_CROPSNH = Reference.MOD_ID;
        public static final String CATEGORY_MIGRATIONS = "migrations";
        public static final String CATEGORY_CROPS = "crops";
        public static final String CATEGORY_RENDERING = "rendering";
    }

    public static Configuration config;

    // COMMON
    // ------
    // cropsnh
    public static int cropsPerCraft;
    public static boolean debug;
    public static boolean panicIfNull;
    public static boolean enableEasterEggs;
    // crops
    public static float growthMultiplier;
    public static boolean putAnEndToExistentialDread;
    public static String goldfishScream;
    public static boolean goldfishScreamWhenSteppedOn;
    public static int breedingChance;

    public static int[] lowFertilizerSpreadingVariations;
    public static int medFertilizerSpreadingThreshold;
    public static int[] medFertilizerSpreadingVariations;
    public static int highFertilizerSpreadingThreshold;
    public static int[] highFertilizerSpreadingVariations;

    public static int[] lowFertilizerBreedingVariations;
    public static int medFertilizerBreedingThreshold;
    public static int[] medFertilizerBreedingVariations;
    public static int highFertilizerBreedingThreshold;
    public static int[] highFertilizerBreedingVariations;
    // weeds
    public static boolean enableWeeds;
    public static boolean weedsWipePlants;
    public static int weedSpawnChance;
    public static int weedSpreadChance;
    // rendering
    public static boolean renderCropPlantsAsTESR;
    // migration
    public static boolean enableMigrations;
    public static boolean alwaysMigrateUsingMigrationCrop;
    // CLIENT
    // ------

    public static void init(FMLPreInitializationEvent event) {
        checkAndCreateConfig(event);
        loadConfiguration();
        LogHelper.debug("Configuration Loaded");
    }

    private static void checkAndCreateConfig(FMLPreInitializationEvent event) {
        if (config == null) {
            String directory = event.getModConfigurationDirectory()
                .toString() + '/'
                + Reference.MOD_ID
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

        panicIfNull = config.getBoolean(
            "panic when finding null",
            Categories.CATEGORY_CROPSNH,
            false,
            "Set to true to allow the loader methods to panic when finding nulls. This config is if GT5u's RA2 PANIC_MODE_NULL flag if it's set.");

        enableEasterEggs = config
            .getBoolean("Enable easter eggs", Categories.CATEGORY_CROPSNH, true, "Set to true to enable easter eggs.");

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

        breedingChance = config.getInt(
            "Breeding Chance",
            Categories.CATEGORY_CROPS,
            3,
            1,
            Integer.MAX_VALUE,
            "Lower values increase the speed at which crops attempt to breed themselves. actual chance is measured as 1 / value every growth tick.");

        medFertilizerSpreadingThreshold = config.getInt(
            "Medium Fertilizer Spreading Threshold",
            Categories.CATEGORY_CROPS,
            50,
            1,
            MTECropManager.FERTILIZER_CAP - 1,
            "The minimum amount of fertilizer needed to use the medium stat variation while spreading.");
        highFertilizerSpreadingThreshold = config.getInt(
            "High Fertilizer Spreading Threshold",
            Categories.CATEGORY_CROPS,
            TileEntityCropSticks.MANUAL_FERTILIZER_MAX_STORAGE + 1,
            1,
            MTECropManager.FERTILIZER_CAP - 1,
            "The minimum amount of fertilizer needed to use the high stat variation while spreading.");
        // Never improves, so you have to either be manually breeding crops in order to do your initial statting run.
        lowFertilizerSpreadingVariations = getStatVariationRange(
            "Low Fertilizer Spreading Stat Variations",
            "The possible stat variations while spreading a crop with an amount of fertilizer below the medium spreading threshold.",
            Categories.CATEGORY_CROPS,
            new int[] { -1, 0, 0 });
        // Keeps stats identical to help with making fields with a given template.
        medFertilizerSpreadingVariations = getStatVariationRange(
            "Medium Fertilizer Spreading Stat Variations",
            "The possible stat variations while spreading a crop with a medium amount of fertilizer.",
            Categories.CATEGORY_CROPS,
            new int[] { 0 });
        // Allows the crop manager to be used to stat crops passively. It's a lot slower than before so you'll probably
        // need a couple runs depending on your setup before you reach max stats this way.
        highFertilizerSpreadingVariations = getStatVariationRange(
            "High Fertilizer Spreading Stat Variations",
            "The possible stat variations while spreading a crop with a high amount of fertilizer.",
            Categories.CATEGORY_CROPS,
            new int[] { 0, 0, 1 });

        medFertilizerBreedingThreshold = config.getInt(
            "Medium Fertilizer Breeding Threshold",
            Categories.CATEGORY_CROPS,
            50,
            1,
            MTECropManager.FERTILIZER_CAP - 1,
            "The minimum amount of fertilizer needed to use the medium stat variation while breeding.");
        highFertilizerBreedingThreshold = config.getInt(
            "High Fertilizer Breeding Threshold",
            Categories.CATEGORY_CROPS,
            TileEntityCropSticks.MANUAL_FERTILIZER_MAX_STORAGE + 1,
            1,
            MTECropManager.FERTILIZER_CAP - 1,
            "The minimum amount of fertilizer needed to use the high stat variation while breeding.");
        // Low chance for variation and 1/5 for it to go up
        lowFertilizerBreedingVariations = getStatVariationRange(
            "Low Fertilizer Breeding Stat Variations",
            "The possible stat variations while breeding a crop without fertilizer.",
            Categories.CATEGORY_CROPS,
            new int[] { -1, 0, 0, 0, 1 });
        // Manual fertilizer increases the chance for variation chance for variation
        lowFertilizerBreedingVariations = getStatVariationRange(
            "Medium Fertilizer Breeding Stat Variations",
            "The possible stat variations while breeding a crop with a medium amount of fertilizer.",
            Categories.CATEGORY_CROPS,
            new int[] { -1, -1, 0, 1, 1 });
        // Allows the crop manager to be used to stat crops passively
        lowFertilizerBreedingVariations = getStatVariationRange(
            "High Fertilizer Breeding Stat Variations",
            "The possible stat variations while breeding a crop with a high amount of fertilizer.",
            Categories.CATEGORY_CROPS,
            new int[] { 0, 1 });

        // endregion CATEGORY_CROPS

        // region CATEGORY_WEEDS

        enableWeeds = config
            .getBoolean("Enable weeds", Categories.CATEGORY_WEEDS, true, "set to false if you wish to disable weeds");

        weedSpawnChance = config.getInt(
            "Weed Spawn Chance",
            Categories.CATEGORY_WEEDS,
            100,
            1,
            Integer.MAX_VALUE,
            "Lower values increase the speed at which weeds spawn in empty crop sticks. actual chance is measured as 1 / value every growth tick.");

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

        // region rendering
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
        // endregion rendering

        // region migration
        enableMigrations = config.getBoolean(
            "Enable Migrations",
            Categories.CATEGORY_MIGRATIONS,
            true,
            "Enable the automatic conversion of existing IC2 crops into CropsNH's equivalent crops.");

        alwaysMigrateUsingMigrationCrop = config.getBoolean(
            "Always use migration crop when migrating",
            Categories.CATEGORY_MIGRATIONS,
            false,
            "When migrating IC2 crops, always create a \"migration\" that cannot grow but always returns a seed when harvested.");
        // endregion migration

        if (config.hasChanged()) {
            config.save();
        }
    }

    private static int[] getStatVariationRange(String name, String description, String category, int[] defaultValues) {
        String[] unparsed = config.getStringList(
            name,
            category,
            Arrays.stream(defaultValues)
                .mapToObj(Integer::toString)
                .toArray(String[]::new),
            description);

        try {
            return Arrays.stream(unparsed)
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (NumberFormatException nfe) {
            return defaultValues;
        }
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
