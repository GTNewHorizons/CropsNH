package com.gtnewhorizon.cropsnh.init;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.gtnewhorizon.cropsnh.blocks.BlockCustomWood;
import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.compatibility.computercraft.ComputerCraftHelper;
import com.gtnewhorizon.cropsnh.compatibility.opencomputers.OpenComputersHelper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.items.ItemCropsNH;
import com.gtnewhorizon.cropsnh.items.blocks.ItemBlockCustomWood;
import com.gtnewhorizon.cropsnh.items.crafting.RecipeJournal;
import com.gtnewhorizon.cropsnh.items.crafting.RecipeShapelessCustomWood;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

    /** Will be replaced with all the custom woods in CustomWood recipes */
    public static final ItemStack REFERENCE = new ItemStack(net.minecraft.init.Blocks.planks, 1);

    /** Holds all the custom woods for CustomWood items, will get filled on init() */
    private static List<ItemStack> woodList;

    public static void init() {
        // crop item
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(Items.crops, ConfigurationHandler.cropsPerCraft),
                        "ss",
                        "ss",
                        's',
                        "stickWood"));
        if (ConfigurationHandler.cropsPerCraft == 3) {
            GameRegistry.addShapelessRecipe(
                    new ItemStack(net.minecraft.init.Items.stick, 6 / ConfigurationHandler.cropsPerCraft),
                    new ItemStack(Items.crops),
                    new ItemStack(Items.crops));
        } else {
            GameRegistry.addShapelessRecipe(
                    new ItemStack(net.minecraft.init.Items.stick, 4 / ConfigurationHandler.cropsPerCraft),
                    new ItemStack(Items.crops));
        }
        // seed analyzer
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(Blocks.blockSeedAnalyzer, 1),
                        "sgs",
                        " bs",
                        "pwp",
                        's',
                        "stickWood",
                        'g',
                        "paneGlass",
                        'b',
                        net.minecraft.init.Blocks.stone_slab,
                        'p',
                        "plankWood",
                        'w',
                        "slabWood"));
        // seeds
        GameRegistry.addShapelessRecipe(
                new ItemStack((Item) Item.itemRegistry.getObject("CropsNH:seedPotato")),
                new ItemStack(net.minecraft.init.Items.potato));
        GameRegistry.addShapelessRecipe(
                new ItemStack((Item) Item.itemRegistry.getObject("CropsNH:seedCarrot")),
                new ItemStack(net.minecraft.init.Items.carrot));
        GameRegistry.addShapelessRecipe(
                new ItemStack(net.minecraft.init.Items.wheat_seeds),
                new ItemStack(net.minecraft.init.Items.wheat));
        // journal
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(Items.journal, 1),
                        "csc",
                        "sbs",
                        "csc",
                        'c',
                        Items.crops,
                        's',
                        Names.OreDict.listAllseed,
                        'b',
                        net.minecraft.init.Items.writable_book));
        GameRegistry.addRecipe(new RecipeJournal());
        // trowel
        if (ConfigurationHandler.enableTrowel && Items.trowel != null) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(Items.trowel, 1, 0),
                            "  s",
                            "ii ",
                            's',
                            "stickWood",
                            'i',
                            "ingotIron"));
        }
        // magnifying glass
        if (ConfigurationHandler.enableMagnifyingGlass && Items.magnifyingGlass != null) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(Items.magnifyingGlass, 1, 0),
                            "sgs",
                            " s ",
                            " s ",
                            's',
                            "stickWood",
                            'g',
                            "paneGlass"));
        }
        // hand rakes
        if (ConfigurationHandler.enableHandRake && Items.handRake != null) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(Items.handRake, 1, 0),
                            "fs",
                            'f',
                            net.minecraft.init.Blocks.fence,
                            's',
                            "stickWood"));
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(Items.handRake, 1, 1),
                            "fs",
                            'f',
                            net.minecraft.init.Blocks.iron_bars,
                            's',
                            "stickWood"));
        }
        // clipper
        if (ConfigurationHandler.enableClipper && Items.clipper != null) {
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(
                            new ItemStack(Items.clipper, 1, 0),
                            " i ",
                            "scn",
                            " s ",
                            'i',
                            "ingotIron",
                            's',
                            "stickWood",
                            'c',
                            new ItemStack(net.minecraft.init.Items.shears)));
        }
        // peripheral
        if (Blocks.blockPeripheral != null) {
            if (ModHelper.allowIntegration(Names.Mods.computerCraft)) {
                GameRegistry.addRecipe(
                        new ShapedOreRecipe(
                                new ItemStack(Blocks.blockPeripheral, 1),
                                "iai",
                                "rcr",
                                "iri",
                                'i',
                                "ingotIron",
                                'a',
                                Blocks.blockSeedAnalyzer,
                                'r',
                                net.minecraft.init.Items.comparator,
                                'c',
                                ComputerCraftHelper.getComputerBlock()));
            }
            if (ModHelper.allowIntegration(Names.Mods.openComputers)) {
                GameRegistry.addRecipe(
                        new ShapedOreRecipe(
                                new ItemStack(Blocks.blockPeripheral, 1),
                                "iai",
                                "rcr",
                                "iri",
                                'i',
                                "ingotIron",
                                'a',
                                Blocks.blockSeedAnalyzer,
                                'r',
                                net.minecraft.init.Items.comparator,
                                'c',
                                OpenComputersHelper.getComputerBlock()));
            }
        }
        // CustomWood recipes
        registerCustomWoodRecipes();
        // fruits
        if (ConfigurationHandler.resourcePlants) {
            if (OreDictHelper.getNuggetForName("Diamond") instanceof ItemCropsNH) {
                GameRegistry.addRecipe(
                        new ShapedOreRecipe(
                                new ItemStack(net.minecraft.init.Items.diamond, 1),
                                "nnn",
                                "nnn",
                                "nnn",
                                'n',
                                "nuggetDiamond"));
                GameRegistry.addRecipe(
                        new ShapelessOreRecipe(
                                new ItemStack(OreDictHelper.getNuggetForName("Diamond"), 9),
                                "gemDiamond"));
            }
            if (OreDictHelper.getNuggetForName("Emerald") instanceof ItemCropsNH) {
                GameRegistry.addRecipe(
                        new ShapedOreRecipe(
                                new ItemStack(net.minecraft.init.Items.emerald, 1),
                                "nnn",
                                "nnn",
                                "nnn",
                                'n',
                                "nuggetEmerald"));
                GameRegistry.addRecipe(
                        new ShapelessOreRecipe(
                                new ItemStack(OreDictHelper.getNuggetForName("Emerald"), 9),
                                "gemEmerald"));
            }
            if (OreDictHelper.getNuggetForName("Iron") instanceof ItemCropsNH) {
                GameRegistry.addRecipe(
                        new ShapedOreRecipe(
                                new ItemStack(net.minecraft.init.Items.iron_ingot, 1),
                                "nnn",
                                "nnn",
                                "nnn",
                                'n',
                                "nuggetIron"));
                GameRegistry.addRecipe(
                        new ShapelessOreRecipe(new ItemStack(OreDictHelper.getNuggetForName("Iron"), 9), "ingotIron"));
            }
            if (OreDictHelper.getNuggetForName("Quartz") instanceof ItemCropsNH) {
                GameRegistry.addRecipe(
                        new ShapedOreRecipe(
                                new ItemStack(net.minecraft.init.Items.quartz, 1),
                                "nnn",
                                "nnn",
                                "nnn",
                                'n',
                                "nuggetQuartz"));
                GameRegistry.addRecipe(
                        new ShapelessOreRecipe(
                                new ItemStack(OreDictHelper.getNuggetForName("Quartz"), 9),
                                new ItemStack(net.minecraft.init.Items.quartz, 1)));
            }
            for (String[] data : Data.modResources) {
                String oreName = data[0];
                Item nuggetItem = OreDictHelper.getNuggetForName(oreName);
                if (nuggetItem != null && nuggetItem instanceof ItemCropsNH) {
                    ItemStack nugget = new ItemStack(nuggetItem, 9, OreDictHelper.getNuggetMetaForName(oreName));
                    ItemStack ingot = OreDictHelper.getIngot(oreName);
                    if (ingot != null) {
                        GameRegistry
                                .addRecipe(new ShapedOreRecipe(ingot, "nnn", "nnn", "nnn", 'n', "nugget" + oreName));
                    }
                    GameRegistry.addRecipe(new ShapelessOreRecipe(nugget, "ingot" + oreName));
                }
            }
            LogHelper.debug("Recipes Registered");
        }
    }

    private static void registerCustomWoodRecipes() {
        if (initWoodList()) {
            if (!ConfigurationHandler.disableSeedStorage) {
                registerCustomWoodRecipe(
                        Blocks.blockSeedStorage,
                        1,
                        true,
                        "wiw",
                        "wcw",
                        "wcw",
                        'w',
                        REFERENCE,
                        'i',
                        net.minecraft.init.Items.iron_ingot,
                        'c',
                        net.minecraft.init.Blocks.chest);
            }
        }
    }

    private static boolean initWoodList() {
        if (woodList != null && woodList.size() > 0) {
            return true;
        }
        for (Field field : Blocks.class.getDeclaredFields()) {
            try {
                if (field.get(null) == null) {
                    continue;
                }
                Object obj = field.get(null);
                if (!(obj instanceof BlockCustomWood)) {
                    continue;
                }
                woodList = new ArrayList<>();
                (((ItemBlockCustomWood) Item.getItemFromBlock((BlockCustomWood) obj))).getSubItems(woodList);
                return true;
            } catch (Exception e) {
                LogHelper.printStackTrace(e);
            }
        }
        return false;
    }

    /**
     * Adds the given recipe for every available wood type.
     * 
     * @param params Same as for GameRegistry. The only difference is that planks will get replaced with the different
     *               woods.
     */
    public static void registerCustomWoodRecipe(Block block, int stackSize, boolean shaped, Object... params) {
        for (ItemStack stack : woodList) {
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey(Names.NBT.material)
                    && stack.stackTagCompound.hasKey(Names.NBT.materialMeta)) {
                // get material
                NBTTagCompound materialTag = stack.getTagCompound();
                ItemStack plank = new ItemStack(
                        (Block) Block.blockRegistry.getObject(materialTag.getString(Names.NBT.material)),
                        1,
                        materialTag.getInteger(Names.NBT.materialMeta));
                Object[] ingredients = Arrays.copyOf(params, params.length);
                for (int i = 0; i < ingredients.length; i++) {
                    // replace all planks with the custom ones
                    if (ingredients[i] instanceof ItemStack && ((ItemStack) ingredients[i]).isItemEqual(REFERENCE)) {
                        ingredients[i] = plank;
                    }
                    // Also replace ItemBlockCustomWood with the correct version
                    if (ingredients[i] instanceof ItemStack && ingredients[i] != null
                            && ((ItemStack) ingredients[i]).getItem() instanceof ItemBlockCustomWood) {
                        ((ItemStack) ingredients[i]).stackTagCompound = materialTag;
                    }
                }
                ItemStack result = new ItemStack(block, stackSize);
                result.stackTagCompound = materialTag;
                // register recipes
                if (shaped) GameRegistry.addShapedRecipe(result, ingredients);
                else addShapelessCustomWoodRecipe(result, ingredients);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerCustomWoodRecipe(IRecipe recipe) {
        if (recipe instanceof ShapedRecipes) {
            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
            registerCustomWoodRecipe(
                    ((ItemBlock) shapedRecipe.getRecipeOutput().getItem()).field_150939_a,
                    shapedRecipe.getRecipeOutput().stackSize,
                    true,
                    (Object[]) shapedRecipe.recipeItems);
        } else if (recipe instanceof ShapelessRecipes) {
            ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
            registerCustomWoodRecipe(
                    ((ItemBlock) shapelessRecipe.getRecipeOutput().getItem()).field_150939_a,
                    shapelessRecipe.getRecipeOutput().stackSize,
                    false,
                    shapelessRecipe.recipeItems.toArray(new ItemStack[shapelessRecipe.recipeItems.size()]));
        }
    }

    @SuppressWarnings("unchecked")
    private static void addShapelessCustomWoodRecipe(ItemStack output, Object... params) {
        List recipeItemsCopy = new ArrayList();
        for (Object recipeItem : params) {
            if (recipeItem instanceof ItemStack) {
                recipeItemsCopy.add(((ItemStack) recipeItem).copy());
            } else if (recipeItem instanceof Item) {
                recipeItemsCopy.add(new ItemStack((Item) recipeItem));
            } else {
                if (!(recipeItem instanceof Block)) {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }
                recipeItemsCopy.add(new ItemStack((Block) recipeItem));
            }
        }
        RecipeShapelessCustomWood recipe = new RecipeShapelessCustomWood(output, recipeItemsCopy);
        GameRegistry.addRecipe(recipe);
    }
}
