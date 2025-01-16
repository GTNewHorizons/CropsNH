package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHCrops;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizons.modularui.api.GlStateManager;
import com.gtnewhorizons.modularui.api.math.Alignment;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.HandlerInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtech.api.util.GTUtility;

public class NEICropsNHCropHandler extends CropsNHNEIHandler {

    // this is a class which handles the recipe for crop products (has to contain a CachedRecipe for the products)
    private static final String id = "cropsNHCrops";
    // not static because language can be changed on the fly
    private final String name = StatCollector.translateToLocal("cropsnh_nei." + id + ".title");

    public static final int X_seed = 33;
    public static final int Y_seed = 20;
    public static final int Y_soil = 41;
    public static final int Y_base = 62;
    public static final int X_product = 104;
    public static final int Y_product = 16;
    public static final int X_arrow = 73;
    public static final int Y_arrow = 34;

    private static final int COLOR_BLACK = 1644054;

    @Override
    public HandlerInfo getHandlerInfo() {
        return new HandlerInfo.Builder(id, Reference.MOD_NAME, Reference.MOD_ID)
            .setDisplayStack(CropsNHItemList.cropSticks.get(1))
            .setHeight(130)
            .setMaxRecipesPerPage(2)
            .build();
    }

    // the class defining a recipe (must be contained in a TemplateRecipeHandler class)
    public class CachedCropRecipe extends TemplateRecipeHandler.CachedRecipe {

        final List<PositionedStack> ingredients = new LinkedList<>();
        final List<PositionedStack> others = new LinkedList<>();
        final List<String> textLines = new LinkedList<>();
        final ICropCard cropCard;

        // constructor
        public CachedCropRecipe(ItemStack seed, ICropCard crop) {
            seed = seed == null ? crop.getSeedItem(SeedStats.DEFAULT_ANALYZED) : seed;
            this.ingredients.add(new PositionedStack(seed, X_seed, Y_seed));
            this.cropCard = crop;
            // sort drops from most likely to least likely.
            int i = 0;
            Iterable<Map.Entry<ItemStack, Integer>> drops = crop.getDropTable()
                .entrySet()
                .stream()
                .sorted(
                    (a, b) -> -a.getValue()
                        .compareTo(b.getValue()))
                .collect(Collectors.toList());
            for (Map.Entry<ItemStack, Integer> drop : drops) {
                this.others.add(
                    new ChancedPositionnedStack(
                        drop.getKey(),
                        X_product + 18 * (i % 3),
                        Y_product + 18 * (i / 3),
                        drop.getValue()));
                i++;
            }

            // get list of soils
            this.others.add(new PositionedStack(crop.getSoilsForNEI(true), X_seed, Y_soil, true));

            // get list of all block under reqs
            List<ItemStack> blockUnderList = crop.getBlocksUnderForNEI(true);
            if (!blockUnderList.isEmpty()) {
                this.others.add(new PositionedStack(blockUnderList, X_seed, Y_base, true));
            }

            // register lines
            // spotless:off
            this.textLines.add(StatCollector.translateToLocalFormatted("cropsnh_nei.cropsNHCrops.tier", crop.getTier()));
            this.textLines.add(StatCollector.translateToLocalFormatted( "cropsnh_nei.cropsNHCrops.growthDuration", GTUtility.formatNumbers(crop.getGrowthDuration())));
            this.textLines.add(StatCollector.translateToLocalFormatted( "cropsnh_nei.cropsNHCrops.dropMult", GTUtility.formatNumbers(crop.getDropChance())));
            //spotless:on

            for (IGrowthRequirement req : crop.getGrowthRequirements()) {
                // skip block under reqs since those are already displayed via the items
                if (req instanceof BlockUnderRequirement) continue;
                this.textLines.add(req.getDescription());
            }
        }

        // return ingredients
        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(NEICropsNHCropHandler.this.cycleticks / 10, this.ingredients);
        }

        @Override
        public List<PositionedStack> getOtherStacks() {
            return getCycledIngredients(NEICropsNHCropHandler.this.cycleticks / 10, this.others);
        }

        // return result
        @Override
        public PositionedStack getResult() {
            return null;
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

    // loads the crop product recipes for a given product
    @Override
    protected void loadCraftingRecipesDo(String pId, Object... results) {
        if (pId.equalsIgnoreCase(id)) {
            for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
                // no reason to display weeds
                if (cc == CropsNHCrops.Weed) continue;
                arecipes.add(new CachedCropRecipe(null, cc));
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
        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            for (ItemStack drop : cc.getDropTable()
                .keySet()) {
                if (drop == null || drop.getItem() == null) {
                    continue;
                }
                if (drop.getItem() == item.getItem() && drop.getItemDamage() == item.getItemDamage()) {
                    arecipes.add(new CachedCropRecipe(null, cc));
                    break;
                }
            }
        }
    }

    // loads the crop product recipes for a given seed
    @Override
    protected void loadUsageRecipesDo(ItemStack item) {
        // filter args
        if (isBadArg(item)) return;

        // this also catches alternate seeds
        ICropCard cc = CropRegistry.instance.get(item);
        if (cc != null) {
            arecipes.add(new CachedCropRecipe(item, cc));
            return;
        }

        // try fetching the block associated with the item
        Block block = CropsNHUtils.getBlockFromItem(item);
        // bail if the block isn't found
        if (block == null) return;

        // find crops it's a soil or under-block for.
        outer: for (ICropCard cropCard : CropRegistry.instance.getAllInRegistrationOrder()) {
            if (cropCard.getSoilTypes()
                .isRegistered(block, item.getItemDamage())) {
                arecipes.add(new CachedCropRecipe(null, cropCard));
                // crops shouldn't be getting registered more than once
                continue;
            }
            // register mutations for which this is a block under.
            for (IGrowthRequirement req : cropCard.getGrowthRequirements()) {
                if (!(req instanceof BlockUnderRequirement)) continue;
                if (((BlockUnderRequirement) req).canGrow(block, item.getItemDamage(), null)) {
                    arecipes.add(new CachedCropRecipe(null, cropCard));
                    // crops shouldn't be getting registered more than once
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

    // gets the texture to display the recipe in
    @Override
    public String getGuiTexture() {
        return new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/nei/cropList.png").toString();
    }

    @Override
    public int recipiesPerPage() {
        return 1;
    }

    // defines rectangles on the recipe gui which can be clicked to show all crop mutation recipes
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(X_arrow, Y_arrow, 20, 14), id));
    }

    @Override
    public void drawExtras(int recipeIndex) {
        CachedCropRecipe cropProductRecipe = (CachedCropRecipe) arecipes.get(recipeIndex);
        for (PositionedStack stack : cropProductRecipe.getOtherStacks()) {
            if (!(stack instanceof ChancedPositionnedStack)) continue;
            ((ChancedPositionnedStack) stack).drawChanceText();
        }
    }

    @Override
    public void drawBackground(int recipeIndex) {
        CachedCropRecipe recipe = (CachedCropRecipe) arecipes.get(recipeIndex);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 166, 85);

        int i = 0;
        for (String line : recipe.textLines) {
            drawFixesWidthLine(line, 0, 85 + (10 * i++), COLOR_BLACK, false, 166);
        }
    }
}
