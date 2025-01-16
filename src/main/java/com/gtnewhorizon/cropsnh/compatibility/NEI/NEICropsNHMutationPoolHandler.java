package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IMutationPool;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.registries.MutationRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizons.modularui.api.GlStateManager;
import com.gtnewhorizons.modularui.api.math.Alignment;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.HandlerInfo;

public class NEICropsNHMutationPoolHandler extends CropsNHNEIHandler {

    // this is a class which handles the recipe for crop products (has to contain a CachedRecipe for the products)
    private static final String id = "cropsNHMutationPool";
    // not static because language can be changed on the fly
    private final String name = StatCollector.translateToLocal("cropsnh_nei." + id + ".title");

    public static final int X_SeedStart = 3;
    public static final int Y_SeedStart = 1;
    public static final int SeedRowCount = 9;

    private static final int COLOR_BLACK = 1644054;

    @Override
    public HandlerInfo getHandlerInfo() {
        return new HandlerInfo.Builder(id, Reference.MOD_NAME, Reference.MOD_ID)
            .setDisplayStack(CropsNHItemList.cropSticks.get(1))
            .setHeight(110)
            .setMaxRecipesPerPage(3)
            .build();
    }

    // the class defining a recipe (must be contained in a TemplateRecipeHandler class)
    public class CachedMutationPoolRecipe extends CachedRecipe {

        final List<PositionedStack> members = new LinkedList<>();
        final String poolNameLine;

        /**
         * @param pool       The mutation pool to display
         * @param firstStack The item to put first (when looking at usages or recipes for)
         * @param firstCrop  The crop associated with the item to pur first (when looking at usages or recipes for)
         */
        public CachedMutationPoolRecipe(IMutationPool pool, ItemStack firstStack, ICropCard firstCrop) {

            this.poolNameLine = StatCollector.translateToLocalFormatted(
                "cropsnh_nei.cropsNHMutationPool.poolName",
                StatCollector.translateToLocal(pool.getUnlocalisedName()));

            int i = 0;
            // if a crop card is passed it's assumed to be a member of the pool and is will be displayed first.
            if (firstCrop != null && firstStack != null) {
                this.members.add(new PositionedStack(firstStack, X_SeedStart, Y_SeedStart, false));
                i++;
            }
            // sort drops by alphabetical order otherwize
            Iterable<ICropCard> members = pool.getMembers()
                .stream()
                .filter(card -> card != firstCrop)
                .sorted(Comparator.comparing(x -> StatCollector.translateToLocal(x.getUnlocalizedName())))
                .collect(Collectors.toList());
            for (ICropCard member : members) {
                int x = X_SeedStart + 18 * (i % SeedRowCount);
                int y = Y_SeedStart + 18 * (i / SeedRowCount);
                this.members.add(new PositionedStack(member.getSeedItem(SeedStats.DEFAULT_ANALYZED), x, y, false));
                i++;
            }
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

    public static class ChancedPositionnedStack extends PositionedStack {

        private final static Alignment alignment = Alignment.TopLeft;
        private final static float scale = 0.45f;
        private final static int color = colorOverride.getTextColorOrDefault("nei_overlay_yellow", 0xFDD835);
        private final String chanceText;

        public ChancedPositionnedStack(Object object, int x, int y, int chance) {
            super(object, x, y, false);
            this.chanceText = String.format("%d.%02d%%", (chance / 100), (chance % 100));
        }

        public void drawChanceText() {
            FontRenderer fontRenderer = net.minecraft.client.Minecraft.getMinecraft().fontRenderer;
            int width = fontRenderer.getStringWidth(this.chanceText);
            int x = (int) ((this.relx + 8 + 8 * alignment.x) / scale) - (width / 2 * (alignment.x + 1));
            int y = (int) ((this.rely + 8 + 8 * alignment.y) / scale)
                - (fontRenderer.FONT_HEIGHT / 2 * (alignment.y + 1))
                - (alignment.y - 1) / 2;

            GlStateManager.pushMatrix();
            GlStateManager.scale(scale, scale, 1);
            fontRenderer.drawString(this.chanceText, x, y, color, false);
            GlStateManager.popMatrix();
        }
    }

    // loads the crop mutation pools for a given product
    @Override
    protected void loadCraftingRecipesDo(String pId, Object... results) {
        if (pId.equalsIgnoreCase(id)) {
            for (IMutationPool pool : MutationRegistry.instance.getMutationPools()) {
                arecipes.add(new CachedMutationPoolRecipe(pool, null, null));
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
        if (cc == null) return;
        for (IMutationPool pool : MutationRegistry.instance.getMutationPools()) {
            if (pool.contains(cc)) {
                this.arecipes.add(new CachedMutationPoolRecipe(pool, item, cc));
            }
        }
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
        return name;
    }

    // returns the id for this recipe
    @Override
    public String getOverlayIdentifier() {
        return id;
    }

    // gets the texture to display the recipe in
    @Override
    public String getGuiTexture() {
        return new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/nei/mutationPool.png").toString();
    }

    @Override
    public int recipiesPerPage() {
        return 3;
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
        GuiDraw.drawTexturedModalRect(2, 0, 7, 11, 162, 90);

        // draw the darn thing already
        String nameLineString = recipe.getPoolNameLine();
        drawFixesWidthLine(nameLineString, 2, 92, COLOR_BLACK, false, 166);
    }
}
