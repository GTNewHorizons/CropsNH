package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.farming.requirements.BlockUnderRequirement;
import com.gtnewhorizon.cropsnh.init.CropsNHItemList;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizons.modularui.api.GlStateManager;
import com.gtnewhorizons.modularui.api.math.Alignment;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.HandlerInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtech.api.util.GTUtility;

public class NEICropsNHCropHandler extends CropsNHNEIHandler {

    // this is a class which handles the recipe for crop products (has to contain a CachedRecipe for the products)
    private static final String name = StatCollector.translateToLocal("cropsnh_nei.cropsNHCrops.title");
    private static final String id = "cropsNHCrops";

    public static final int X_seed = 33;
    public static final int Y_seed = 20;
    public static final int Y_soil = 41;
    public static final int Y_base = 62;
    public static final int X_product = 104;
    public static final int Y_product = 16;
    public static final int X_arrow = 73;
    public static final int Y_arrow = 34;

    private static final int COLOR_BLACK = 1644054;

    public static HandlerInfo getHandlerInfo() {
        return new HandlerInfo.Builder("cropsNHCrops", Reference.MOD_NAME, Reference.MOD_ID)
            .setDisplayStack(CropsNHItemList.cropSticks.get(1))
            .setHeight(130)
            .setMaxRecipesPerPage(2)
            .build();
    }

    // the class defining a recipe (must be contained in a TemplateRecipeHandler class)
    public class CachedCropRecipe extends TemplateRecipeHandler.CachedRecipe {

        final ICropCard cropCard;
        final PositionedStack seed;
        final List<ChancedPositionnedStack> drops = new LinkedList<>();
        final List<PositionedStack> soils = new ArrayList<>();
        final List<PositionedStack> requiredBlocksUnder = new ArrayList<>();

        // constructor
        public CachedCropRecipe(ItemStack seed, ICropCard cc) {
            this.cropCard = cc;
            seed = seed == null ? cc.getSeedItem(SeedStats.DEFAULT_ANALYZED) : seed;
            this.seed = new PositionedStack(seed, X_seed, Y_seed);
            // sort drops from most likely to least likely.
            int i = 0;
            Iterable<Map.Entry<ItemStack, Integer>> drops = this.cropCard.getDropTable()
                .entrySet()
                .stream()
                .sorted(
                    (a, b) -> -a.getValue()
                        .compareTo(b.getValue()))
                .collect(Collectors.toList());
            for (Map.Entry<ItemStack, Integer> drop : drops) {
                this.drops.add(
                    new ChancedPositionnedStack(
                        drop.getKey(),
                        X_product + 18 * (i % 3),
                        Y_product + 18 * (i / 3),
                        drop.getValue()));
                i++;
            }

            // get list of soils
            for (ItemStack s : this.cropCard.getSoilsForNEI(true)) {
                this.soils.add(new PositionedStack(s, X_seed, Y_soil, false));
            }

            // get list of all block under reqs
            for (ItemStack s : this.cropCard.getBlocksUnderForNEI(true)) {
                this.requiredBlocksUnder.add(new PositionedStack(s, X_seed, Y_base, false));
            }
        }

        @Override
        public PositionedStack getIngredient() {
            return seed;
        }

        // return ingredients
        @Override
        public List<PositionedStack> getIngredients() {
            List<PositionedStack> list = new ArrayList<>();
            list.add(this.seed);
            list.add(this.soils.get(NEICropsNHCropHandler.this.cycleticks / 20 % this.soils.size()));
            if (!this.requiredBlocksUnder.isEmpty()) {
                list.add(
                    this.requiredBlocksUnder
                        .get(NEICropsNHCropHandler.this.cycleticks / 20 % this.requiredBlocksUnder.size()));
            }
            list.addAll(this.drops);
            return list;
        }

        // return result
        @Override
        public PositionedStack getResult() {
            return null;
        }

        public ICropCard getCropCard() {
            return cropCard;
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
    protected void loadCraftingRecipesDo(String id, Object... results) {
        if (id.equalsIgnoreCase(NEICropsNHCropHandler.id)) {
            for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
                arecipes.add(new CachedCropRecipe(null, cc));
            }
        } else if (id.equalsIgnoreCase("item")) {
            for (Object object : results) {
                if (object instanceof ItemStack && getClass() == NEICropsNHCropHandler.class) {
                    loadCraftingRecipes(
                        new ItemStack(((ItemStack) object).getItem(), 1, ((ItemStack) object).getItemDamage()));
                }
            }
        }
    }

    // loads the crop product recipes for a given product
    @Override
    protected void loadCraftingRecipesDo(ItemStack result) {
        // filter args
        if (result == null || result.getItem() == null) return;
        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            for (ItemStack drop : cc.getDropTable()
                .keySet()) {
                if (drop == null || drop.getItem() == null) {
                    continue;
                }
                if (drop.getItem() == result.getItem() && drop.getItemDamage() == result.getItemDamage()) {
                    arecipes.add(new CachedCropRecipe(null, cc));
                    break;
                }
            }
        }
    }

    // loads the crop product recipes for a given seed
    @Override
    protected void loadUsageRecipesDo(ItemStack ingredient) {
        if (ingredient.getItem() == null) return;

        // this also catches alternate seeds
        ICropCard cc = CropRegistry.instance.get(ingredient);
        if (cc != null) {
            arecipes.add(new CachedCropRecipe(ingredient, cc));
        }
        // try fetching the block associated with the item
        Block block = null;
        if (ingredient.getItem() instanceof ItemBlock) {
            block = ((ItemBlock) ingredient.getItem()).field_150939_a;
        } else {
            block = Block.getBlockFromItem(ingredient.getItem());
        }
        if (block != null) {
            // find crops it's a soil or under-block for.
            outer: for (ICropCard cropCard : CropRegistry.instance.getAllInRegistrationOrder()) {
                if (cropCard.getSoilTypes()
                    .isRegistered(block, ingredient.getItemDamage())) {
                    arecipes.add(new CachedCropRecipe(null, cropCard));
                    continue;
                }
                for (IGrowthRequirement r : cropCard.getGrowthRequirements()) {
                    if (!(r instanceof BlockUnderRequirement)) continue;
                    if (((BlockUnderRequirement) r).canGrow(block, ingredient.getItemDamage(), null)) {
                        arecipes.add(new CachedCropRecipe(null, cropCard));
                        continue outer;
                    }
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
        return new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/nei/cropProduce.png").toString();
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
    public void drawExtras(int recipe) {
        CachedCropRecipe cropProductRecipe = (CachedCropRecipe) arecipes.get(recipe);
        for (PositionedStack stack : cropProductRecipe.getIngredients()) {
            if (!(stack instanceof ChancedPositionnedStack)) continue;
            ((ChancedPositionnedStack) stack).drawChanceText();
        }
    }

    @Override
    public void drawBackground(int recipe) {
        CachedCropRecipe cropProductRecipe = (CachedCropRecipe) arecipes.get(recipe);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 166, 85);

        String tier = StatCollector
            .translateToLocalFormatted("cropsnh_nei.cropList.tier", cropProductRecipe.cropCard.getTier());
        String growth = StatCollector.translateToLocalFormatted(
            "cropsnh_nei.cropList.growthDuration",
            GTUtility.formatNumbers(cropProductRecipe.cropCard.getGrowthDuration()));
        String dropMult = StatCollector.translateToLocalFormatted(
            "cropsnh_nei.cropList.dropMult",
            GTUtility.formatNumbers(cropProductRecipe.cropCard.getDropChance()));
        GuiDraw.drawString(tier, 0, 85, COLOR_BLACK, false);
        GuiDraw.drawString(growth, 0, 95, COLOR_BLACK, false);
        GuiDraw.drawString(dropMult, 0, 105, COLOR_BLACK, false);
    }
}
