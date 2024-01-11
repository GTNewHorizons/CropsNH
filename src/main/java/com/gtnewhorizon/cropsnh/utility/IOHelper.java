package com.gtnewhorizon.cropsnh.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.farming.CropPlantHandler;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;

// helper class to read, write and parse data to and from the config files
public abstract class IOHelper {

    public static String getModId(ItemStack stack) {
        if (stack == null || stack.getItem() == null) {
            return null;
        }
        String name = Item.itemRegistry.getNameForObject(stack.getItem());
        int split = name.indexOf(':');
        if (split >= 0) {
            name = name.substring(0, split);
        }
        return name;
    }

    /**
     * <p>
     * Attempts to read or write data from or to a file.
     * </p>
     * <p>
     * If the file is found to be missing, the file will be written with the default data, and the default data will be
     * returned.
     * </p>
     * <p>
     * If the file is found to be a regular, readable file, the file is read in, and its contents returned.
     * </p>
     * <p>
     * If the method runs into an error while attempting any of this, a message is printed to the log, and the default
     * data is returned.
     * </p>
     * 
     * @param directory   the directory the file is in.
     * @param fileName    the name of the file, without the .txt ending.
     * @param defaultData the data to write to the file, or default to.
     * @return the data contained in the file, or the default data.
     */
    public static String readOrWrite(String directory, String fileName, String defaultData) {
        return readOrWrite(directory, fileName, defaultData, false);
    }

    /**
     * <p>
     * Attempts to read or write data from or to a file.
     * </p>
     * <p>
     * If the file is found to be missing or the reset parameter is set to true, the file will be written with the
     * default data, and the default data will be returned.
     * </p>
     * <p>
     * If the file is found to be a regular, readable file, the file is read in, and its contents returned.
     * </p>
     * <p>
     * If the method runs into an error while attempting any of this, a message is printed to the log, and the default
     * data is returned.
     * </p>
     * 
     * @param directory   the directory the file is in.
     * @param fileName    the name of the file, without the .txt ending.
     * @param defaultData the data to write to the file, or default to.
     * @param reset       if the file should be overwritten with the default data.
     * @return the data contained in the file, or the default data.
     */
    public static String readOrWrite(String directory, String fileName, String defaultData, boolean reset) {
        Path path = Paths.get(directory, fileName + ".txt");
        try {
            if (Files.isRegularFile(path) && !reset) {
                return new String(Files.readAllBytes(path));
            } else {
                Files.write(path, defaultData.getBytes());
            }
        } catch (IOException e) {
            LogHelper.info("Caught IOException when reading " + path.getFileName());
        }
        return defaultData;
    }

    /**
     * Utility method: splits the string in different lines so it will fit on the page.
     *
     * @param fontRendererObj the font renderer to check against.
     * @param input           the line to split up.
     * @param maxWidth        the maximum allowable width of the line before being wrapped.
     * @param scale           the scale of the text to the width.
     * @return the string split up into lines by the '\n' character.
     */
    public static String splitInLines(FontRenderer fontRendererObj, String input, float maxWidth, float scale) {
        maxWidth = maxWidth / scale;
        String notProcessed = input;
        String output = "";
        while (fontRendererObj.getStringWidth(notProcessed) > maxWidth) {
            int index = 0;
            if (notProcessed != null && !notProcessed.equals("")) {
                // find the first index at which the string exceeds the size limit
                while (notProcessed.length() - 1 > index
                        && fontRendererObj.getStringWidth(notProcessed.substring(0, index)) < maxWidth) {
                    index = (index + 1) < notProcessed.length() ? index + 1 : index;
                }
                // go back to the first space to cut the string in two lines
                while (index > 0 && notProcessed.charAt(index) != ' ') {
                    index--;
                }
                // update the data for the next iteration
                output = output.equals("") ? output : output + '\n';
                output = output + notProcessed.substring(0, index);
                notProcessed = notProcessed.length() > index + 1 ? notProcessed.substring(index + 1) : notProcessed;
            }
        }
        return output + '\n' + notProcessed;
    }

    // finds blacklisted crops
    public static void initSeedBlackList() {
        String[] data = IOHelper.getLinesArrayFromData(ConfigurationHandler.readSeedBlackList());
        for (String line : data) {
            LogHelper.debug(new StringBuffer("parsing ").append(line));
            ItemStack seedStack = IOHelper.getStack(line, false);
            boolean success = seedStack != null && seedStack.getItem() != null;
            String errorMsg = "Invalid seed";
            if (success) {
                CropPlantHandler.addSeedToBlackList(seedStack);
            } else {
                LogHelper.info(
                        new StringBuffer("Error when adding seed to blacklist: ").append(errorMsg).append(" (line: ")
                                .append(line).append(")"));
            }
        }
    }

    // initializes the seed tier overrides
    public static void initSeedTiers() {
        String[] input = IOHelper.getLinesArrayFromData(ConfigurationHandler.readSeedTiers());
        LogHelper.debug("reading seed tier overrides");
        for (String line : input) {
            String[] data = IOHelper.getData(line);
            boolean success = data.length == 2;
            String errorMsg = "Incorrect amount of arguments";
            LogHelper.debug("parsing " + line);
            if (success) {
                ItemStack seedStack = IOHelper.getStack(data[0], false);
                CropPlant plant = CropPlantHandler.getPlantFromStack(seedStack);
                success = plant != null;
                errorMsg = "Invalid seed";
                if (success) {
                    int tier = Integer.parseInt(data[1]);
                    success = tier >= 1 && tier <= 5;
                    errorMsg = "Tier should be between 1 and 5";
                    if (success) {
                        plant.setTier(tier);
                        LogHelper.info(
                                " - " + Item.itemRegistry.getNameForObject(plant.getSeed().getItem())
                                        + ':'
                                        + plant.getSeed().getItemDamage()
                                        + " - tier: "
                                        + tier);
                    }
                }
            }
            if (!success) {
                LogHelper.info(
                        new StringBuffer("Error when adding seed tier override: ").append(errorMsg).append(" (line: ")
                                .append(line).append(")"));
            }
        }
    }

    public static void initSpreadChancesOverrides() {
        // read mutation chance overrides & initialize the arrays
        String[] input = IOHelper.getLinesArrayFromData(ConfigurationHandler.readSpreadChances());
        LogHelper.debug("reading mutation chance overrides");
        for (String line : input) {
            String[] data = IOHelper.getData(line);
            boolean success = data.length == 2;
            String errorMsg = "Incorrect amount of arguments";
            LogHelper.debug("parsing " + line);
            if (success) {
                ItemStack seedStack = IOHelper.getStack(data[0], false);
                CropPlant plant = CropPlantHandler.getPlantFromStack(seedStack);
                success = plant != null;
                errorMsg = "Invalid seed";
                if (success) {
                    int chance = Integer.parseInt(data[1]);
                    success = chance >= 0 && chance <= 100;
                    errorMsg = "Chance should be between 0 and 100";
                    if (success) {
                        plant.setSpreadChance(chance);
                        LogHelper.debug(
                                "Set spread chance for " + Item.itemRegistry.getNameForObject(plant.getSeed().getItem())
                                        + ':'
                                        + plant.getSeed().getItemDamage()
                                        + " to "
                                        + chance
                                        + '%');
                    }
                }
            }
            if (!success) {
                LogHelper.debug("Error when adding mutation chance override: " + errorMsg + " (line: " + line + ")");
            }
        }
        LogHelper.debug("Registered Mutations Chances overrides:");
    }

    public static void initVanillaPlantingOverrides() {
        LogHelper.debug("Registered seeds ignoring vanilla planting rule:");
        String[] data = IOHelper.getLinesArrayFromData(ConfigurationHandler.readVanillaOverrides());
        for (String line : data) {
            LogHelper.debug(new StringBuffer("parsing ").append(line));
            ItemStack seedStack = IOHelper.getStack(line, false);
            CropPlant plant = CropPlantHandler.getPlantFromStack(seedStack);
            boolean success = plant != null;
            String errorMsg = "Invalid seed";
            if (success) {
                plant.setIgnoreVanillaPlantingRule(true);
                LogHelper.debug(
                        Item.itemRegistry.getNameForObject(plant.getSeed().getItem()) + ":"
                                + plant.getSeed().getItemDamage());
            } else {
                LogHelper.debug("Error when adding seed to vanilla overrides: " + errorMsg + " (line: " + line + ")");
            }
        }
    }

    // get the mutations file contents
    // This should probably be a loop...
    public static String getDefaultMutations() {
        String data = mutationInstructions; // mutationInstructions
        data = data + '\n' + minecraftMutations; // minecraft mutations
        if (ConfigurationHandler.resourcePlants) {
            data = data + '\n' + cropsnhMutations; // cropsnh mutations
            if (OreDictHelper.getOreBlockForName("Copper") != null) {
                data = data + '\n' + copperMutation;
            }
            if (OreDictHelper.getOreBlockForName("Tin") != null) {
                data = data + '\n' + tinMutation;
            }
            if (OreDictHelper.getOreBlockForName("Lead") != null) {
                data = data + '\n' + leadMutation;
            }
            if (OreDictHelper.getOreBlockForName("Silver") != null) {
                data = data + '\n' + silverMutation;
            }
            if (OreDictHelper.getOreBlockForName("Aluminum") != null) {
                data = data + '\n' + aluminumMutation;
            }
            if (OreDictHelper.getOreBlockForName("Nickel") != null) {
                data = data + '\n' + nickelMutation;
            }
            if (OreDictHelper.getOreBlockForName("Platinum") != null) {
                data = data + '\n' + platinumMutation;
            }
            if (OreDictHelper.getOreBlockForName("Osmium") != null) {
                data = data + '\n' + osmiumMutation;
            }
        }
        if (ModHelper.allowIntegration(Names.Mods.botania)) {
            data = data + '\n' + botaniaMutations;
        }
        if (ModHelper.allowIntegration(Names.Mods.harvestcraft) && ModHelper.allowIntegration(Names.Mods.natura)) {
            data = data + '\n' + harvestcraftMutations + '\n' + barleyNaturaMutations; // harvestcraft with natura
                                                                                       // barley
        } else {
            if (ModHelper.allowIntegration(Names.Mods.harvestcraft)) {
                data = data + '\n' + harvestcraftMutations + '\n' + barleyHarvestCraftMutations;
            }
            if (ModHelper.allowIntegration(Names.Mods.natura)) {
                data = data + '\n' + naturaMutations;
            }
        }
        if (ModHelper.allowIntegration(Names.Mods.weeeFlowers)) {
            data = data + '\n' + weeeFlowersMutations;
        }
        if (ModHelper.allowIntegration(Names.Mods.plantMegaPack)) {
            data = data + '\n' + plantMegaPackMutations;
        }
        if (ModHelper.allowIntegration(Names.Mods.chococraft)) {
            if (ModHelper.allowIntegration(Names.Mods.harvestcraft)) {
                data = data + '\n' + chococraft_harvestcraftMutations;
            } else {
                data = data + '\n' + chococraftMutations;
            }
        }
        if (ModHelper.allowIntegration(Names.Mods.psychedelicraft)) {
            data = data + '\n' + psychedelicraftMutations;
        }
        if (ModHelper.allowIntegration(Names.Mods.thaumcraft) && ModHelper.allowIntegration(Names.Mods.arsMagica)) {
            data = data + '\n' + thaumcraft_ArsMagicaMutations;
        } else {
            if (ModHelper.allowIntegration(Names.Mods.thaumcraft)) {
                data = data + '\n' + thaumcraftMutations;
            } else if (ModHelper.allowIntegration(Names.Mods.arsMagica)) {
                data = data + '\n' + arsMagicaMutations;
            }
        }
        return data;
    }

    // get the custom crop file contents
    public static String getCustomCropInstructions() {
        return customCropInstructions;
    }

    // get the grass drops file contents
    public static String getGrassDrops() {
        // This is bad...
        return grassDropInstructions;
    }

    // mutation chances overrides file contents
    public static String getSpreadChancesOverridesInstructions() {
        return spreadChancesOverridesInstructions;
    }

    // seed tier overrides file contents
    public static String getSeedTierOverridesInstructions() {
        return seedTierOverridesInstructions;
    }

    // seed black list
    public static String getSeedBlackListInstructions() {
        return seedBlackListInstructions;
    }

    // vanilla planting overrides
    public static String getPlantingExceptionsInstructions() {
        return plantingExceptionsInstructions;
    }

    public static String getSoilWhitelistData() {
        return soilWhitelistInstructions;
    }

    // turns the raw data string into an array (each array element is a line from the string)
    public static String[] getLinesArrayFromData(String input) {
        int count = 0;
        String unprocessed = input;
        for (int i = 0; i < unprocessed.length(); i++) {
            if (unprocessed.charAt(i) == '\n') {
                count++;
            }
        }
        ArrayList<String> data = new ArrayList<>(count + 1); // There will be no more than count plus + lines, thereby
                                                             // preventing resizing.
        if (unprocessed.length() > 0) {
            for (int i = 0; i < count; i++) {
                String line = (unprocessed.substring(0, unprocessed.indexOf('\n'))).trim();
                if (line.length() > 0 && line.charAt(0) != '#') {
                    data.add(line); // The string line was already trimmed in its declaration.
                }
                unprocessed = unprocessed.substring(unprocessed.indexOf('\n') + 1);
            }
        }

        unprocessed = unprocessed.trim();

        if (unprocessed.length() > 0 && unprocessed.charAt(0) != '#') {
            data.add(unprocessed);
        }
        return data.toArray(new String[data.size()]);
    }

    // splits a comma seperated string into an array
    public static String[] getData(String input) {
        ArrayList<String> output = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ',') {
                if (input.charAt(i + 1) == ' ') {
                    continue;
                }
                String element = (input.substring(start, i)).trim();
                if (element.length() > 0) {
                    output.add(element);
                }
                start = i + 1;
            }
        }
        String element = (input.substring(start)).trim();
        if (element.length() > 0) {
            output.add(element);
        }
        return output.toArray(new String[output.size()]);
    }

    /**
     * Retrieves an itemstack from a string representation. The string must be formatted as "modid:name:meta". The meta
     * is not required in all cases.
     *
     * @param input    the string representation of the item to get.
     * @param wildcard if true, the metadata will be set to OreDictionary.WILDCARD_VALUE if no metadata was specified,
     *                 will be set to 0 if false
     * @return the item as an itemstack, or null.
     */
    public static ItemStack getStack(String input, boolean wildcard) {
        String[] data = input.split(":");
        if (data.length <= 1) {
            return null;
        }
        int meta = data.length == 3 ? Integer.parseInt(data[2]) : (wildcard ? OreDictionary.WILDCARD_VALUE : 0);
        ItemStack stack = GameRegistry.findItemStack(data[0], data[1], 1);
        if (stack != null && stack.getItem() != null) {
            stack.setItemDamage(meta);
        }
        return stack;
    }

    /**
     * Retrieves a a block from a string representation. The string must be formatted as "modid:name:meta". The meta is
     * not required in all cases.
     *
     * @param input the string representation of the block to get.
     * @return the block as a blockWithMeta, or null.
     */
    public static BlockWithMeta getBlock(String input) {
        String[] data = input.split(":");
        if (data.length <= 1) {
            return null;
        }
        int meta = data.length == 3 ? Integer.parseInt(data[2]) : -1;
        Block block = GameRegistry.findBlock(data[0], data[1]);
        if (block == null) {
            return null;
        }
        return meta < 0 ? new BlockWithMeta(block) : new BlockWithMeta(block, meta);
    }

    // TODO: move all this crap to asset files
    private static final String grassDropInstructions = "#Put a list of seeds here that will drop from tall grass with the following schematic: <seedname:seedmeta>,<weight>\n"
            + "#The seedname should be the name NEI gives you, the weight is the weighted chance for this seed to drop (for reference, minecraft wheat seeds have weight 10)\n"
            + "#Only define one seed per line, meta is optional. Example: minecraft:melon_seeds,10\n"
            + "CropsNH:seedCarrot,10\n"
            + "CropsNH:seedPotato,10\n";

    private static final String customCropInstructions = "#Define custom crops here: <name>,<fruit:fruitmeta>,<soil>,<baseblock:baseblockmeta>,<tier>,<rendermethod>,<information>,<shearable>\n"
            + "# - name:         is the name of the crop you want, for example: claysanthemum\n"
            + "# - fruit:        the fruit you want the crop to produce, for example: minecraft:clay:0 (metadata is optional, you can get this from NEI). Type \"null\" if you want the crop to have no fruit.\n"
            + "# - soil:         the soil you want the crop to be planted on, if you specify null, it will grow on farmland and any soil you whitelisted. Other possible soils are soulsand, sand or mycelium.\n"
            + "# - baseblock:    this is the block that has to be underneath for the plant to grow, for example: (this can also be gotten from NEI, if you don't want to specify, type null)\n"
            + "# - tier:         from 1 to 5, the higher, the slower the crop will grow\n"
            + "# - rendermethod: put 1 to render like a flower (in an X-pattern), put 6 to render like wheat (hashtag-pattern)\n"
            + "# - information:  put a short description (in the same line) of the crop. This will appear in the journal\n"
            + "# - shearable:    (optional) the item this plant drops when sheared\n"
            + "#you will need to make a texture pack and add textures for the crops in cropsnh/blocks with the name cropName1, cropName2, cropName3, cropName4\n"
            + "#where name is the name you specified here, there have to be 4 textures, texture 4 is the mature one\n"
            + "#for the seed texture, put a texture named seedName in the cropsnh/items of your resourcepack\n"
            + "#Example: claysanthemum,minecraft:clay,minecraft:sand,minecraft:hardened_clay,2,6,a crop that extracts clay from the soil to grow clay buds.\n"
            + "#this will need textures cropClaysanthemum1, cropClaysanthemum2, Claysanthemum3, Claysanthemum4 in the cropsnh/textures/blocks folder and seedClaysanthemum in the cropsnh/textures/items folder of your resourcepack.\n"
            + "#Note the capitalization, always put a capital in the texture name. Also, this crop will not grow unless it has a hardened clay block underneath the block of farmland it's on";

    private static final String seedBlackListInstructions = "#Define blacklisted seeds here: <mod>:<seedname>:<seedmeta>\n"
            + "#You can get these values from NEI\n"
            + "#Blacklisted seeds will not be able to planted on crops\n"
            + "#For example: CropsNH:seedDandelion";

    private static final String plantingExceptionsInstructions = "#Define seeds that will ignore the vanilla planting rule here: <mod>:<seedname>:<seedmeta>\n"
            + "#You can get these values from NEI\n"
            + "#All seeds defined here will still be able to be planted outside of crops when vanilla farming is disabled\n"
            + "#For example: CropsNH:seedDandelion";

    private static final String soilWhitelistInstructions = "#Define blocks to whitelist as fertile soil here: <mod>:<blockname>:<blockmeta>\n"
            + "#You can get these values from NEI\n"
            + "#Whitelisting a block as a fertile soil means you can plant crops on them\n"
            + "#Note that this only works for crops that can be planted on farmland, crops that require a specific soil (e.g. nether wart or cactus) will still need that particular soil\n"
            + "#For example: Forestry:soil:0 (this will add forestry humus to the whitelist)";

    private static final String spreadChancesOverridesInstructions = "#Define overrides for spreading chances here: <mod>:<seedname>:<seedmeta>,<chance>\n"
            + "#You can get these values from NEI (example: minecraft:wheat_seeds is the vanilla seeds)\n"
            + "#The chance is an integer specified in percent, minimum is 0, maximum a 100. Spread chance is the chance that crops will spread to empty crosscrops\n"
            + "#For example: CropsNH:seedDandelion,85";

    private static final String seedTierOverridesInstructions = "#Define overrides for seed tiers here: <mod>:<seedname>:<seedmeta>,<tier>\n"
            + "#You can get these values from NEI (example: minecraft:wheat_seeds is the vanilla seeds)\n"
            + "#The tier is an integer between 1 and 5 (1 and 5 included). The higher the tier, the slower it will grow, mutate, be analysed, ...\n"
            + "#For example: CropsNH:seedDandelion,2";

    private static final String mutationInstructions = "#ONLY MODIFY THIS CONFIG IF YOU DO NOT WANT TO USE MINETWEAKER, IF YOU WANT TO MODIFY MUTATIONS USE MINETWEAKER, THIS CONFIG IS ONLY FOR PEOPLE WHO DON'T USE MINETWEAKER\n"
            + "#Define mutations here: <mutation>=<parent1>+<parent2>\n"
            + "#To specify a crop, write <mod>:<cropname>:<meta>, all in lowercase (meta is optional)\n"
            + "#You can get these values from NEI (example: minecraft:wheat_seeds is the vanilla seeds)\n";

    private static final String harvestcraftMutations = "harvestcraft:artichokeseedItem=harvestcraft:asparagusseedItem+harvestcraft:lettuceseedItem\n"
            + "harvestcraft:asparagusseedItem=harvestcraft:scallionseedItem+harvestcraft:cornseedItem\n"
            + "harvestcraft:bambooshootseedItem=harvestcraft:cornseedItem+harvestcraft:riceseedItem\n"
            + "harvestcraft:beanseedItem=minecraft:pumpkin_seeds+CropsNH:seedPotato\n"
            + "harvestcraft:beetseedItem=harvestcraft:radishseedItem+CropsNH:seedCarrot\n"
            + "harvestcraft:bellpepperseedItem=harvestcraft:chilipepperseedItem+harvestcraft:spiceleafseedItem\n"
            + "harvestcraft:blackberryseedItem=harvestcraft:strawberryseedItem+harvestcraft:blueberryseedItem\n"
            + "harvestcraft:blueberryseedItem=harvestcraft:strawberryseedItem+CropsNH:seedOrchid\n"
            + "harvestcraft:broccoliseedItem=harvestcraft:lettuceseedItem+CropsNH:seedDaisy\n"
            + "harvestcraft:brusselsproutseedItem=harvestcraft:cabbageseedItem+harvestcraft:peasseedItem\n"
            + "harvestcraft:cabbageseedItem=harvestcraft:broccoliseedItem+harvestcraft:lettuceseedItem\n"
            + "harvestcraft:cactusfruitseedItem=harvestcraft:kiwiseedItem+harvestcraft:bambooshootseedItem\n"
            + "harvestcraft:candleberryseedItem=harvestcraft:cactusfruitseedItem+harvestcraft:grapeseedItem\n"
            + "harvestcraft:cantaloupeseedItem=minecraft:melon_seeds+harvestcraft:strawberryseedItem\n"
            + "harvestcraft:cauliflowerseedItem=harvestcraft:cabbageseedItem+harvestcraft:lettuceseedItem\n"
            + "harvestcraft:celeryseedItem=minecraft:wheat_seeds+CropsNH:seedAllium\n"
            + "harvestcraft:chilipepperseedItem=harvestcraft:tomatoseedItem+harvestcraft:onionseedItem\n"
            + "harvestcraft:coffeeseedItem=harvestcraft:beanseedItem+CropsNH:seedSugarcane\n"
            + "harvestcraft:cranberryseedItem=harvestcraft:blueberryseedItem+harvestcraft:grapeseedItem\n"
            + "harvestcraft:cucumberseedItem=harvestcraft:peasseedItem+harvestcraft:okraseedItem\n"
            + "harvestcraft:eggplantseedItem=harvestcraft:zucchiniseedItem+harvestcraft:tomatoseedItem\n"
            + "harvestcraft:garlicseedItem=harvestcraft:onionseedItem+harvestcraft:gingerseedItem\n"
            + "harvestcraft:gingerseedItem=harvestcraft:mustardseedItem+harvestcraft:peanutseedItem\n"
            + "harvestcraft:grapeseedItem=harvestcraft:blueberryseedItem+harvestcraft:cantaloupeseedItem\n"
            + "harvestcraft:kiwiseedItem=harvestcraft:cantaloupeseedItem+harvestcraft:strawberryseedItem\n"
            + "harvestcraft:leekseedItem=harvestcraft:scallionseedItem+harvestcraft:celeryseedItem\n"
            + "harvestcraft:lettuceseedItem=CropsNH:seedDaisy+harvestcraft:celeryseedItem\n"
            + "harvestcraft:spinachseedItem=harvestcraft:lettuceseedItem+CropsNH:seedCactus\n"
            + // This line seems not to be valid, in my testing.
            "harvestcraft:mustardseedItem=harvestcraft:chilipepperseedItem+harvestcraft:beanseedItem\n"
            + "harvestcraft:oatsseedItem=harvestcraft:cornseedItem+harvestcraft:riceseedItem\n"
            + "harvestcraft:okraseedItem=harvestcraft:beanseedItem+harvestcraft:leekseedItem\n"
            + "harvestcraft:onionseedItem=harvestcraft:celeryseedItem+harvestcraft:brusselsproutseedItem\n"
            + "harvestcraft:parsnipseedItem=CropsNH:seedCarrot+harvestcraft:beetseedItem\n"
            + "harvestcraft:peanutseedItem=harvestcraft:peasseedItem+harvestcraft:bambooshootseedItem\n"
            + "harvestcraft:peasseedItem=harvestcraft:soybeanseedItem+harvestcraft:okraseedItem\n"
            + "harvestcraft:pineappleseedItem=harvestcraft:bambooshootseedItem+harvestcraft:cantaloupeseedItem\n"
            + "harvestcraft:radishseedItem=harvestcraft:tomatoseedItem+harvestcraft:brusselsproutseedItem\n"
            + "harvestcraft:strawberryseedItem=harvestcraft:radishseedItem+CropsNH:seedPoppy\n"
            + "harvestcraft:raspberryseedItem=harvestcraft:strawberryseedItem+CropsNH:seedTulipRed\n"
            + "harvestcraft:rhubarbseedItem=CropsNH:seedSugarcane+harvestcraft:lettuceseedItem\n"
            + "harvestcraft:riceseedItem=harvestcraft:ryeseedItem+CropsNH:seedSugarcane\n"
            + "harvestcraft:rutabagaseedItem=harvestcraft:beetseedItem+harvestcraft:turnipseedItem\n"
            + "harvestcraft:scallionseedItem=CropsNH:seedCarrot+CropsNH:seedSugarcane\n"
            + "harvestcraft:seaweedseedItem=harvestcraft:lettuceseedItem+harvestcraft:celeryseedItem\n"
            + "harvestcraft:soybeanseedItem=harvestcraft:beanseedItem+harvestcraft:riceseedItem\n"
            + "harvestcraft:spiceleafseedItem=harvestcraft:teaseedItem+harvestcraft:chilipepperseedItem\n"
            + "harvestcraft:sweetpotatoseedItem=CropsNH:seedPotato+CropsNH:seedSugarcane\n"
            + "harvestcraft:teaseedItem=harvestcraft:seaweedseedItem+CropsNH:seedDaisy\n"
            + "harvestcraft:tomatoseedItem=harvestcraft:sweetpotatoseedItem+CropsNH:seedCarrot\n"
            + "harvestcraft:turnipseedItem=harvestcraft:parsnipseedItem+harvestcraft:radishseedItem\n"
            + "harvestcraft:wintersquashseedItem=minecraft:pumpkin_seeds+harvestcraft:zucchiniseedItem\n"
            + "harvestcraft:zucchiniseedItem=minecraft:pumpkin_seeds+harvestcraft:cucumberseedItem\n"
            + "harvestcraft:whitemushroomseedItem=CropsNH:seedShroomRed+CropsNH:seedShroomBrown\n"
            + "harvestcraft:curryleafseedItem=harvestcraft:spiceleafseedItem+harvestcraft:mustardseedItem\n"
            + "harvestcraft:sesameseedsseedItem=harvestcraft:riceseedItem+harvestcraft:coffeeseedItem\n"
            + "harvestcraft:waterchestnutseedItem=harvestcraft:sesameseedsseedItem+harvestcraft:seaweedseedItem";

    private static final String weeeFlowersMutations = "weeeflowers:Red Flower Seed=CropsNH:seedPoppy+CropsNH:seedCarrot\n"
            + "weeeflowers:Yellow Flower Seed=CropsNH:seedDandelion+minecraft:pumpkin_seeds\n"
            + "weeeflowers:Orange Flower Seed=weeeflowers:Yellow Flower Seed+weeeflowers:Red Flower Seed\n"
            + "weeeflowers:White Flower Seed=CropsNH:seedTulipWhite+CropsNH:seedAllium\n"
            + "weeeflowers:Pink Flower Seed=weeeflowers:Red Flower Seed+weeeflowers:White Flower Seed\n"
            + "weeeflowers:Blue Flower Seed=CropsNH:seedOrchid+CropsNH:seedDaisy\n"
            + "weeeflowers:Light Blue Flower Seed=weeeflowers:Blue Flower Seed+weeeflowers:White Flower Seed\n"
            + "weeeflowers:Purple Flower Seed=weeeflowers:Blue Flower Seed+weeeflowers:Red Flower Seed\n"
            + "weeeflowers:Magenta Flower Seed=weeeflowers:Purple Flower Seed+weeeflowers:Pink Flower Seed\n"
            + "weeeflowers:Green Flower Seed=weeeflowers:Yellow Flower Seed+weeeflowers:Blue Flower Seed\n"
            + "weeeflowers:Lime Flower Seed=weeeflowers:Green Flower Seed+weeeflowers:White Flower Seed\n"
            + "weeeflowers:Black Flower Seed=weeeflowers:Blue Flower Seed+weeeflowers:Green Flower Seed\n"
            + "weeeflowers:Light Grey Flower Seed=weeeflowers:Black Flower Seed+weeeflowers:White Flower Seed\n"
            + "weeeflowers:Dark Grey Flower Seed=weeeflowers:Light Grey Flower Seed+weeeflowers:Black Flower Seed\n"
            + "weeeflowers:Cyan Flower Seed=weeeflowers:Lime Flower Seed+weeeflowers:Light Blue Flower Seed\n"
            + "weeeflowers:Brown Flower Seed=weeeflowers:Green Flower Seed+weeeflowers:Red Flower Seed";

    private static final String botaniaMutations = "CropsNH:seedBotaniaRed=CropsNH:seedPoppy+CropsNH:seedTulipRed\n"
            + "CropsNH:seedBotaniaYellow=CropsNH:seedDandelion+CropsNH:seedTulipOrange\n"
            + "CropsNH:seedBotaniaBlue=CropsNH:seedOrchid+CropsNH:seedAllium\n"
            + "CropsNH:seedBotaniaOrange=CropsNH:seedBotaniaYellow+CropsNH:seedBotaniaRed\n"
            + "CropsNH:seedBotaniaPurple=CropsNH:seedBotaniaMagenta+CropsNH:seedBotaniaBlue\n"
            + "CropsNH:seedBotaniaGreen=CropsNH:seedBotaniaBlue+CropsNH:seedBotaniaYellow\n"
            + "CropsNH:seedBotaniaMagenta=CropsNH:seedBotaniaRed+CropsNH:seedBotaniaBlue\n"
            + "CropsNH:seedBotaniaPink=CropsNH:seedBotaniaPurple+CropsNH:seedBotaniaWhite\n"
            + "CropsNH:seedBotaniaLime=CropsNH:seedBotaniaWhite+CropsNH:seedBotaniaGreen\n"
            + "CropsNH:seedBotaniaCyan=CropsNH:seedBotaniaGreen+CropsNH:seedBotaniaBlue\n"
            + "CropsNH:seedBotaniaLightBlue=CropsNH:seedBotaniaBlue+CropsNH:seedBotaniaWhite\n"
            + "CropsNH:seedBotaniaBlack=CropsNH:seedBotaniaBlue+CropsNH:seedCactus\n"
            + "CropsNH:seedBotaniaWhite=CropsNH:seedTulipWhite+CropsNH:seedDaisy\n"
            + "CropsNH:seedBotaniaGray=CropsNH:seedBotaniaBlack+CropsNH:seedBotaniaWhite\n"
            + "CropsNH:seedBotaniaLightGray=CropsNH:seedBotaniaGray+CropsNH:seedBotaniaWhite\n"
            + "CropsNH:seedBotaniaBrown=CropsNH:seedBotaniaPurple+CropsNH:seedBotaniaGreen";

    private static final String minecraftMutations = "CropsNH:seedSugarcane=minecraft:wheat_seeds+CropsNH:seedCarrot\n"
            + "CropsNH:seedCactus=CropsNH:seedSugarcane+CropsNH:seedPoppy\n"
            + "minecraft:pumpkin_seeds=CropsNH:seedPotato+CropsNH:seedCarrot\n"
            + "minecraft:melon_seeds=CropsNH:seedCarrot+minecraft:pumpkin_seeds\n"
            + "CropsNH:seedPoppy=CropsNH:seedSugarcane+minecraft:pumpkin_seeds\n"
            + "CropsNH:seedDandelion=CropsNH:seedSugarcane+minecraft:melon_seeds\n"
            + "CropsNH:seedOrchid=CropsNH:seedPoppy+CropsNH:seedDandelion\n"
            + "CropsNH:seedAllium=CropsNH:seedPoppy+CropsNH:seedOrchid\n"
            + "CropsNH:seedTulipRed=CropsNH:seedPoppy+CropsNH:seedAllium\n"
            + "CropsNH:seedTulipOrange=CropsNH:seedDaisy+CropsNH:seedOrchid\n"
            + "CropsNH:seedTulipWhite=CropsNH:seedDaisy+CropsNH:seedDandelion\n"
            + "CropsNH:seedTulipPink=CropsNH:seedAllium+CropsNH:seedDandelion\n"
            + "CropsNH:seedDaisy=CropsNH:seedDandelion+CropsNH:seedOrchid\n"
            + "CropsNH:seedShroomRed=minecraft:nether_wart+CropsNH:seedPoppy\n"
            + "CropsNH:seedShroomBrown=minecraft:nether_wart+CropsNH:seedPotato\n";

    private static final String cropsnhMutations = "CropsNH:seedRedstodendron=CropsNH:seedTulipRed+CropsNH:seedDaisy\n"
            + "CropsNH:seedLapender=CropsNH:seedTulipPink+CropsNH:seedOrchid\n"
            + "CropsNH:seedFerranium=CropsNH:seedLapender+CropsNH:seedTulipWhite\n"
            + "CropsNH:seedAurigold=CropsNH:seedRedstodendron+CropsNH:seedTulipOrange\n"
            + "CropsNH:seedDiamahlia=CropsNH:seedAurigold+CropsNH:seedLapender\n"
            + "CropsNH:seedEmeryllis=CropsNH:seedFerranium+CropsNH:seedRedstodendron\n"
            + "CropsNH:seedNitorWart=minecraft:nether_wart+CropsNH:seedTulipOrange\n"
            + "CropsNH:seedQuartzanthemum=CropsNH:seedNitorWart+CropsNH:seedLapender";

    private static final String barleyHarvestCraftMutations = "harvestcraft:barleyseedItem=minecraft:wheat_seeds+CropsNH:seedSugarcane\n"
            + "harvestcraft:cornseedItem=harvestcraft:barleyseedItem+harvestcraft:ryeseedItem\n"
            + "harvestcraft:ryeseedItem=harvestcraft:barleyseedItem+minecraft:wheat_seeds\n"
            + "harvestcraft:cottonseedItem=harvestcraft:barleyseedItem+CropsNH:seedDaisy";

    private static final String naturaMutations = "Natura:barley.seed:0=minecraft:wheat_seeds+CropsNH:seedSugarcane\n"
            + "Natura:barley.seed:1=Natura:barley.seed:0+CropsNH:seedDaisy";

    private static final String barleyNaturaMutations = "Natura:barley.seed:0=minecraft:wheat_seeds+CropsNH:seedSugarcane\n"
            + "harvestcraft:cornseedItem=Natura:barley.seed:0+harvestcraft:ryeseedItem\n"
            + "harvestcraft:ryeseedItem=Natura:barley.seed:0+minecraft:wheat_seeds\n"
            + "Natura:barley.seed:1=Natura:barley.seed:0+CropsNH:seedDaisy";

    private static final String plantMegaPackMutations = "plantmegapack:seedCelery=minecraft:wheat_seeds+CropsNH:seedTulipWhite\n"
            + "plantmegapack:seedTomato=minecraft:melon_seeds+CropsNH:seedCarrot\n"
            + "plantmegapack:seedBroccoli=plantmegapack:seedSpinach+plantmegapack:seedCelery\n"
            + "plantmegapack:seedBeet=plantmegapack:seedTomato+CropsNH:seedPotato\n"
            + "plantmegapack:seedCassava=plantmegapack:seedBeet+plantmegapack:seedCorn\n"
            + "plantmegapack:seedGreenBean=plantmegapack:seedCucumber+plantmegapack:seedCelery\n"
            + "plantmegapack:seedLeek=plantmegapack:seedGreenBean+plantmegapack:seedCucumber\n"
            + "plantmegapack:seedLettuce=plantmegapack:seedCelery+CropsNH:seedDaisy\n"
            + "plantmegapack:seedSpinach=plantmegapack:seedLettuce+CropsNH:seedSugarcane\n"
            + "plantmegapack:seedCorn=minecraft:wheat_seeds+plantmegapack:seedCelery\n"
            + "plantmegapack:seedBellPepperYellow=CropsNH:seedCarrot+CropsNH:seedDandelion\n"
            + "plantmegapack:seedBellPepperOrange=plantmegapack:seedBellPepperYellow+plantmegapack:seedTomato\n"
            + "plantmegapack:seedBellPepperRed=plantmegapack:seedBellPepperOrange+plantmegapack:seedTomato\n"
            + "plantmegapack:seedSorrel=plantmegapack:seedBroccoli+plantmegapack:seedCassava\n"
            + "plantmegapack:seedCucumber=plantmegapack:seedBeet+plantmegapack:seedBellPepperYellow\n"
            + "plantmegapack:seedEggplant=plantmegapack:seedBeet+plantmegapack:seedCucumber";

    private static final String chococraftMutations = "chococraft:Gysahl_Seeds=CropsNH:seedPotato+CropsNH:seedTulipRed";

    private static final String chococraft_harvestcraftMutations = "chococraft:Gysahl_Seeds=harvestcraft:rutabagaseedItem+harvestcraft:beetseedItem";

    private static final String psychedelicraftMutations = "psychedelicraft:tobaccoSeeds=CropsNH:seedShroomBrown+minecraft:nether_wart\n"
            + "psychedelicraft:hop_seeds=psychedelicraft:tobaccoSeeds+minecraft:wheat_seeds\n"
            + "psychedelicraft:cannabisSeeds=psychedelicraft:coffeaCherries+psychedelicraft:hop_seeds\n"
            + "psychedelicraft:cocaSeeds=psychedelicraft:cannabisSeeds+psychedelicraft:hop_seeds\n"
            + "psychedelicraft:coffeaCherries=psychedelicraft:hop_seeds+psychedelicraft:tobaccoSeeds";

    private static final String thaumcraft_ArsMagicaMutations = "CropsNH:seedCinderpearl=CropsNH:seedCactus+CropsNH:seedTulipRed\n"
            + "CropsNH:seedDesertNova=CropsNH:seedCinderpearl+CropsNH:seedTulipRed\n"
            + "CropsNH:seedCerublossom=CropsNH:seedDesertNova+CropsNH:seedCinderpearl\n"
            + "CropsNH:seedAum=CropsNH:seedCerublossom+CropsNH:seedCinderpearl\n"
            + "CropsNH:seedWakebloom=CropsNH:seedAum+CropsNH:seedCerublossom\n"
            + "CropsNH:seedVishroom=CropsNH:seedShroomBrown+CropsNH:seedShimmerleaf\n"
            + "CropsNH:seedTarmaRoot=CropsNH:seedDesertNova+CropsNH:seedShroomBrown\n"
            + "CropsNH:seedShimmerleaf=CropsNH:seedCerublossom+CropsNH:seedTulipWhite\n"
            + "CropsNH:seedTaintedRoot=CropsNH:seedVishroom+CropsNH:seedTarmaRoot";

    private static final String thaumcraftMutations = "CropsNH:seedCinderpearl=CropsNH:seedCactus+CropsNH:seedTulipRed\n"
            + "CropsNH:seedShimmerleaf=CropsNH:seedTulipWhite+CropsNH:seedCinderpearl\n"
            + "CropsNH:seedVishroom=CropsNH:seedShroomBrown+CropsNH:seedShimmerleaf\n"
            + "CropsNH:seedTaintedRoot=CropsNH:seedVishroom+minecraft:nether_wart";

    private static final String arsMagicaMutations = "CropsNH:seedDesertNova=CropsNH:seedCactus+CropsNH:seedTulipRed\n"
            + "CropsNH:seedCerublossom=CropsNH:seedDesertNova+CropsNH:seedOrchid\n"
            + "CropsNH:seedAum=CropsNH:seedCerublossom+CropsNH:seedTulipWhite\n"
            + "CropsNH:seedTarmaRoot=CropsNH:seedDesertNova+CropsNH:seedShroomBrown\n"
            + "CropsNH:seedWakebloom=CropsNH:seedAum+CropsNH:seedCerublossom";

    private static final String copperMutation = "CropsNH:seedCuprosia=CropsNH:seedRedstodendron+CropsNH:seedTulipRed";

    private static final String tinMutation = "CropsNH:seedPetinia=CropsNH:seedLapender+CropsNH:seedDaisy";

    private static final String leadMutation = "CropsNH:seedPlombean=CropsNH:seedFerranium+CropsNH:seedPotato";

    private static final String silverMutation = "CropsNH:seedSilverweed=CropsNH:seedAurigold+CropsNH:seedAllium";

    private static final String aluminumMutation = "CropsNH:seedJaslumine=CropsNH:seedFerranium+minecraft:wheat_seeds";

    private static final String nickelMutation = "CropsNH:seedNiccissus=CropsNH:seedAurigold+CropsNH:seedDandelion";

    private static final String platinumMutation = "CropsNH:seedPlatiolus=CropsNH:seedDiamahlia+CropsNH:seedFerranium";

    private static final String osmiumMutation = "CropsNH:seedOsmonium=CropsNH:seedFerranium+CropsNH:seedOrchid";
}
