package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.HandlerInfo;

public class NEICropsNHMutationPoolHandler extends CropsNHNEIHandler {

    // this is a class which handles the recipe for crop products (has to contain a CachedRecipe for the products)
    private static final String id = "mutationPool";

    public static final int X_SeedStart = 3;
    public static final int Y_SeedStart = 1;
    public static final int COL_COUNT = 9;

    private static final int COLOR_BLACK = 1644054;

    @Override
    public HandlerInfo getHandlerInfo() {
        return new HandlerInfo.Builder(id, Reference.MOD_NAME, Reference.MOD_ID)
            .setDisplayStack(CropsNHItemList.cropSticks.get(1))
            .setHeight(110)
            .setShowFavoritesButton(false)
            .setShowOverlayButton(false)
            .build();
    }

    // the class defining a recipe (must be contained in a TemplateRecipeHandler class)
    public class CachedMutationPoolRecipe extends CachedRecipe {

        final List<PositionedStack> members = new LinkedList<>();
        final String poolNameLine;

        /**
         * @param pool    The mutation pool to display.
         * @param members The members to display of for the pool (can be a subsection of the pool if overflow is likely
         *                to occur).
         */
        public CachedMutationPoolRecipe(IMutationPool pool, ItemStack[] members) {

            this.poolNameLine = StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_nei.mutationPool.poolName",
                StatCollector.translateToLocal(pool.getUnlocalisedName()));

            for (int i = 0; i < members.length; i++) {
                int x = X_SeedStart + 18 * (i % COL_COUNT);
                int y = Y_SeedStart + 18 * (i / COL_COUNT);
                this.members.add(new PositionedStack(members[i], x, y, false));
            }
        }

        public int getRowCount() {
            return Math.max(1, this.members.size() / COL_COUNT + (this.members.size() % COL_COUNT == 0 ? 0 : 1));
        }

        // return ingredients
        @Override
        public List<PositionedStack> getIngredients() {
            return new ArrayList<>(this.members);
        }

        // return result
        @Override
        public PositionedStack getResult() {
            return null;
        }

        public String getPoolNameLine() {
            return this.poolNameLine;
        }
    }

    // loads the crop mutation pools for a given product
    @Override
    protected void loadCraftingRecipesDo(String pId, Object... results) {
        if (pId.equalsIgnoreCase(id)) {
            for (IMutationPool pool : MutationRegistry.instance.getMutationPools()) {
                this.addRecipes(pool, null, null);
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

    // loads the crop product recipes for a given product
    @Override
    protected void loadCraftingRecipesDo(ItemStack item) {
        // filter args
        if (isBadArg(item)) return;
        // this also catches alternate seeds
        ICropCard cc = CropRegistry.instance.get(item);
        // back out if it's not something that turns into a crop;
        if (cc == null || cc.hideFromNEI()) return;
        for (IMutationPool pool : MutationRegistry.instance.getMutationPools()) {
            if (pool.contains(cc)) {
                this.addRecipes(pool, cc, item);
            }
        }
    }

    /**
     * Adds one or many recipe sections for a given pool
     *
     * @param pool                  The pool to add sections for.
     * @param inspectedItemCropCard The crop card associated with the item that was inspected.
     * @param inspectedItem         The item that was inspected.
     */
    private void addRecipes(IMutationPool pool, @Nullable ICropCard inspectedItemCropCard,
        @Nullable ItemStack inspectedItem) {
        // first sort by name
        Stream<ICropCard> stream = pool.getMembers()
            .stream()
            .sorted(Comparator.comparing(cc -> StatCollector.translateToLocal(cc.getUnlocalizedName())));

        // if we passed in a crop card, put it in first, it's assumed to be in the list.
        if (inspectedItemCropCard != null) {
            stream = stream.sorted(Comparator.comparing(cc -> cc == inspectedItemCropCard ? 0 : 1));
        }

        // resolve the array
        ItemStack[] members = stream.map(x -> x.getSeedItem(SeedStats.DEFAULT_ANALYZED))
            .toArray(ItemStack[]::new);

        // if we passed in a crop card and a stack, override the stack iwth a copy of the passed stack
        if (inspectedItemCropCard != null && inspectedItem != null) {
            ItemStack copied = CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(inspectedItem, 1);
            // can still be null if the item in the stack was somehow null
            if (copied != null) members[0] = copied;
        }

        // create all the sections as necessary
        this.arecipes.add(new CachedMutationPoolRecipe(pool, members));
    }

    // loads the crop product recipes for a given seed
    @Override
    protected void loadUsageRecipesDo(ItemStack item) {
        // same logic for both cases
        loadCraftingRecipesDo(item);
    }

    // returns the name for this recipe
    @Override
    public String getRecipeName() {
        return StatCollector.translateToLocal(Reference.MOD_ID + "_nei.mutationPool.title");
    }

    // returns the id for this recipe
    @Override
    public String getOverlayIdentifier() {
        return id;
    }

    // gets the texture to display the recipe in
    @Override
    public String getGuiTexture() {
        return new ResourceLocation(Reference.MOD_ID, "textures/gui/nei/mutationPool.png").toString();
    }

    // defines rectangles on the recipe gui which can be clicked to show all crop mutation recipes
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(0, 92, 166, 10), id));
    }

    @Override
    public void drawBackground(int recipeIndex) {
        CachedMutationPoolRecipe recipe = (CachedMutationPoolRecipe) this.arecipes.get(recipeIndex);

        // add background
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(this.getGuiTexture());
        int y = 0;

        // draw top
        GuiDraw.drawTexturedModalRect(2, y, 0, 0, 162, 9);
        y += 9;

        // draw middle parts
        int rowCount = recipe.getRowCount();
        for (int i = 1; i < rowCount; i++) {
            GuiDraw.drawTexturedModalRect(2, y, 0, 10, 162, 18);
            y += 18;
        }

        // draw bottom
        GuiDraw.drawTexturedModalRect(2, y, 0, 29, 162, 9);
        y += 9;

        // draw the darn thing already
        String nameLineString = recipe.getPoolNameLine();
        drawFixesWidthLine(nameLineString, 2, y + 2, COLOR_BLACK, false, 166);
    }

    @Override
    public int getRecipeHeight(int recipeIndex) {
        CachedMutationPoolRecipe recipe = (CachedMutationPoolRecipe) this.arecipes.get(recipeIndex);
        return recipe.getRowCount() * 18 + 20;
    }
}
