package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.api.IBreedingRequirement;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.HandlerInfo;

public class NEICropsNHBreedingHandler extends CropsNHNEIHandler {

    // this is a class which handles the recipe for crop products (has to contain a CachedRecipe for the products)
    private static final String id = "cropsNHBreeding";
    // not static because language can be changed on the fly
    private final String name = StatCollector.translateToLocal("cropsnh_nei." + id + ".title");

    public static final int X_seed = 75;
    public static final int Y_seed = 22;
    public static final int[] X_parent = new int[] { 44, 106, 23, 127 };
    public static final int Y_soil = 43;
    public static final int Y_base = 64;

    // gets the texture to display the recipe in
    private static final String background2 = new ResourceLocation(
        Reference.MOD_ID.toLowerCase(),
        "textures/gui/nei/breeding2.png").toString();
    private static final String background4 = new ResourceLocation(
        Reference.MOD_ID.toLowerCase(),
        "textures/gui/nei/breeding4.png").toString();

    private static final int COLOR_BLACK = 1644054;

    @Override
    public HandlerInfo getHandlerInfo() {
        return new HandlerInfo.Builder(id, Reference.MOD_NAME, Reference.MOD_ID)
            .setDisplayStack(CropsNHItemList.cropSticks.get(1))
            .setHeight(111)
            .setMaxRecipesPerPage(3)
            .build();
    }

    // the class defining a recipe (must be contained in a TemplateRecipeHandler class)
    public class CachedBreedingRecipe extends CachedRecipe {

        final PositionedStack output;
        final List<PositionedStack> ingredients = new LinkedList<>();
        final List<PositionedStack> others = new LinkedList<>();
        final String background;
        final List<String> reqLines = new LinkedList<>();

        // constructor
        public CachedBreedingRecipe(ICropMutation mutation) {
            // register the output first
            this.output = new PositionedStack(
                mutation.getOutput()
                    .getSeedItem(SeedStats.DEFAULT_ANALYZED),
                X_seed,
                Y_seed,
                false);

            // register the parents
            int i = 0;
            for (ICropCard parent : mutation.getParents()) {
                if (i >= X_parent.length) {
                    this.reqLines.add("More than " + X_parent.length + " parents detected, this shouldn't happen!");
                    break;
                }
                ItemStack stack = parent.getSeedItem(SeedStats.DEFAULT_ANALYZED);
                this.ingredients.add(new PositionedStack(stack, X_parent[i++], Y_seed, false));
            }
            this.background = i > 2 ? background4 : background2;

            // get list of all soils
            this.others.add(
                new PositionedStack(
                    mutation.getOutput()
                        .getSoilsForNEI(true),
                    X_seed,
                    Y_soil,
                    true));

            // get list of all block under
            List<ItemStack> blockUnderList = mutation.getBlocksUnderForNEI(true);
            if (!blockUnderList.isEmpty()) {
                this.others.add(new PositionedStack(blockUnderList, X_seed, Y_base, true));
            }

            for (IBreedingRequirement req : mutation.getRequirements()) {
                // skip block under reqs since those are already displayed via the items
                if (req instanceof BlockUnderRequirement) continue;
                this.reqLines.add(req.getDescription());
            }
        }

        // return ingredients
        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(NEICropsNHBreedingHandler.this.cycleticks / 10, this.ingredients);
        }

        @Override
        public List<PositionedStack> getOtherStacks() {
            return getCycledIngredients(NEICropsNHBreedingHandler.this.cycleticks / 10, this.others);
        }

        // return result
        @Override
        public PositionedStack getResult() {
            return this.output;
        }

        public List<String> getReqLines() {
            return this.reqLines;
        }

        public String getBackground() {
            return this.background;
        }
    }

    // loads the crop product recipes for a given product
    @Override
    protected void loadCraftingRecipesDo(String pId, Object... results) {
        if (pId.equalsIgnoreCase(id)) {
            for (ICropMutation mutation : MutationRegistry.instance.getDeterministicMutations()) {
                this.arecipes.add(new CachedBreedingRecipe(mutation));
            }
        } else if (pId.equalsIgnoreCase("item")) {
            for (Object object : results) {
                if (object instanceof ItemStack) {
                    ItemStack stack = ((ItemStack) object).copy();
                    stack.stackSize = 1;
                    loadCraftingRecipes(stack);
                }
            }
        }
    }

    // load the mutations to create a given seed
    @Override
    protected void loadCraftingRecipesDo(ItemStack item) {
        if (isBadArg(item)) return;
        ICropCard cc = CropRegistry.instance.get(item);
        // bail if it's not a compatible seed
        if (cc == null) return;
        // find mutations that create this seed.
        for (ICropMutation mutation : MutationRegistry.instance.getDeterministicMutations()) {
            if (mutation.getOutput() != cc) continue;
            this.arecipes.add(new CachedBreedingRecipe(mutation));
        }
    }

    // loads the crop product recipes for a given seed
    @Override
    protected void loadUsageRecipesDo(ItemStack item) {
        if (isBadArg(item)) return;

        // this also catches alternate seeds
        ICropCard cc = CropRegistry.instance.get(item);
        if (cc != null) {
            for (ICropMutation mutation : MutationRegistry.instance.getDeterministicMutations()) {
                if (!mutation.getParents()
                    .contains(cc)) continue;
                this.arecipes.add(new CachedBreedingRecipe(mutation));
            }
            // there shouldn't be an odd case where a seed is also a soil or a block under.
            return;
        }

        // try fetching the block associated with the item
        Block block = CropsNHUtils.getBlockFromItem(item);
        // bail if the block isn't found
        if (block == null) return;

        // find crops it's a soil or under-block for.
        outer: for (ICropMutation mutation : MutationRegistry.instance.getDeterministicMutations()) {
            // register mutations for which this is a soil
            if (mutation.getOutput()
                .getSoilTypes()
                .isRegistered(block, item.getItemDamage())) {
                arecipes.add(new CachedBreedingRecipe(mutation));
                // mutations shouldn't be getting registered more than once
                continue;
            }
            // register mutations for which this is a block under.
            for (IBreedingRequirement req : mutation.getRequirements()) {
                if (!(req instanceof BlockUnderRequirement)) continue;
                // The canBreed methods are more for machines and dim checks, canGrow fits our use-case better here.
                if (((BlockUnderRequirement) req).canGrow(block, item.getItemDamage(), null)) {
                    arecipes.add(new CachedBreedingRecipe(mutation));
                    // mutations shouldn't be getting registered more than once
                    continue outer;
                }
            }
        }
    }

    // returns the name for this recipe
    @Override
    public String getRecipeName() {
        return name;
    }

    // returns the id for this recipe
    @Override
    public String getOverlayIdentifier() {
        return id;
    }

    @Override
    public String getGuiTexture() {
        return background2;
    }

    @Override
    public int recipiesPerPage() {
        return 1;
    }

    // defines rectangles on the recipe gui which can be clicked to show all crop mutation recipes
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(59, 12, 48, 4), id));
        transferRects.add(new RecipeTransferRect(new Rectangle(65, 6, 4, 39), id));
        transferRects.add(new RecipeTransferRect(new Rectangle(97, 6, 4, 39), id));
    }

    @Override
    public void drawBackground(int recipeIndex) {
        CachedBreedingRecipe recipe = (CachedBreedingRecipe) arecipes.get(recipeIndex);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(recipe.getBackground());
        GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 166, 87);

        int i = 0;
        for (String line : recipe.getReqLines()) {
            drawFixesWidthLine(line, 0, 89 + (10 * i++), COLOR_BLACK, false, 166);
        }
    }
}
