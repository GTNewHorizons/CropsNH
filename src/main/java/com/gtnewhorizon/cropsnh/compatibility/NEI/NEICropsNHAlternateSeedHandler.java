package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.awt.Rectangle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.HandlerInfo;

public class NEICropsNHAlternateSeedHandler extends CropsNHNEIHandler {

    private static final String id = Reference.MOD_ID + "AlternateSeeds";
    private static final String background = new ResourceLocation(
        Reference.MOD_ID,
        "textures/gui/nei/alternateSeeds.png").toString();

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (!isActive()) {
            return;
        }
        if (inputId.equalsIgnoreCase(id)) {
            for (ICropCard entry : CropRegistry.instance.getAllInRegistrationOrder()) {
                for (ItemStack alternateSeed : entry.getAlternateSeeds()) {
                    arecipes.add(new CachedAlternateSeedRecipe(alternateSeed, entry));
                }
            }
        } else if (inputId.equalsIgnoreCase("item")) {
            for (Object object : ingredients) {
                if (object instanceof ItemStack s) {
                    ItemStack stack = s.copy();
                    stack.stackSize = 1;
                    loadUsageRecipes(stack);
                }
            }
        }
    }

    @Override
    protected void loadUsageRecipesDo(ItemStack ingredient) {
        ICropCard cc = CropRegistry.instance.fromAlternateSeed(ingredient);
        if (cc != null) {
            arecipes.add(new CachedAlternateSeedRecipe(ingredient, cc));
        }
    }

    @Override
    public String getGuiTexture() {
        return background;
    }

    @Override
    public HandlerInfo getHandlerInfo() {
        return new HandlerInfo.Builder(id, Reference.MOD_NAME, Reference.MOD_ID)
            .setDisplayStack(CropsNHItemList.cropSticks.get(1))
            .setHeight(20)
            .setShowFavoritesButton(false)
            .setShowOverlayButton(false)
            .build();
    }

    @Override
    protected void loadCraftingRecipesDo(String outputId, Object... results) {
        if (outputId.equalsIgnoreCase(id)) {
            for (ICropCard entry : CropRegistry.instance.getAllInRegistrationOrder()) {
                for (ItemStack alternateSeed : entry.getAlternateSeeds()) {
                    arecipes.add(new CachedAlternateSeedRecipe(alternateSeed, entry));
                }
            }
        } else if (outputId.equalsIgnoreCase("item")) {
            for (Object object : results) {
                if (object instanceof ItemStack s) {
                    ItemStack stack = s.copy();
                    stack.stackSize = 1;
                    loadCraftingRecipes(stack);
                }
            }
        }
    }

    @Override
    protected void loadCraftingRecipesDo(ItemStack stack) {
        ISeedData seedData = CropsNHUtils.getAnalyzedSeedData(stack);
        if (seedData == null || isBadArg(stack)) {
            return;
        }
        ICropCard cc = seedData.getCrop();
        for (ItemStack alternateSeed : cc.getAlternateSeeds()) {
            arecipes.add(new CachedAlternateSeedRecipe(alternateSeed, cc));
        }
    }

    @Override
    public String getRecipeName() {
        return StatCollector.translateToLocal(Reference.MOD_ID + "_nei.altSeeds.title");
    }

    // defines rectangles on the recipe gui which can be clicked to show all crop mutation recipes
    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(75, 2, 16, 16), id));
    }

    @Override
    public String getOverlayIdentifier() {
        return id;
    }

    private class CachedAlternateSeedRecipe extends CachedRecipe {

        private final PositionedStack input;
        private final PositionedStack output;
        private static final SeedStats DEFAULT_STATS = new SeedStats(
            Constants.MIN_SEED_STAT,
            Constants.MIN_SEED_STAT,
            Constants.MIN_SEED_STAT,
            true);

        public CachedAlternateSeedRecipe(ItemStack stack, ICropCard cc) {
            input = new PositionedStack(stack, 55, 2);
            output = new PositionedStack(cc.getSeedItem(DEFAULT_STATS), 96, 2);
        }

        @Override
        public PositionedStack getIngredient() {
            return input;
        }

        @Override
        public PositionedStack getResult() {
            return output;
        }
    }
}
