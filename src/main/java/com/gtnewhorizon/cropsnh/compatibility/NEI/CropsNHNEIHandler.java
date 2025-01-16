package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.recipe.HandlerInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.gui.GUIColorOverride;
import gregtech.api.gui.modularui.GTUITextures;

@SideOnly(Side.CLIENT)
public abstract class CropsNHNEIHandler extends TemplateRecipeHandler {

    protected static final GUIColorOverride colorOverride = GUIColorOverride
        .get(GTUITextures.BACKGROUND_NEI_SINGLE_RECIPE.location);

    private static final HashMap<Class<? extends CropsNHNEIHandler>, Boolean> handlerStatuses = new HashMap<>();

    public CropsNHNEIHandler() {
        if (!handlerStatuses.containsKey(this.getClass())) {
            handlerStatuses.put(this.getClass(), true);
        }
    }

    public abstract HandlerInfo getHandlerInfo();

    public static void setActive(Class<? extends CropsNHNEIHandler> clazz, boolean active) {
        handlerStatuses.put(clazz, active);
    }

    public boolean isActive() {
        return handlerStatuses.get(this.getClass());
    }

    @Override
    public final void loadCraftingRecipes(String id, Object... results) {
        if (!isActive()) {
            return;
        }
        loadCraftingRecipesDo(id, results);
    }

    protected abstract void loadCraftingRecipesDo(String id, Object... results);

    @Override
    public final void loadCraftingRecipes(ItemStack result) {
        if (!isActive()) {
            return;
        }
        loadCraftingRecipesDo(result);
    }

    protected abstract void loadCraftingRecipesDo(ItemStack result);

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (!isActive()) {
            return;
        }
        loadUsageRecipesDo(ingredient);
    }

    protected abstract void loadUsageRecipesDo(ItemStack ingredient);

    protected static boolean isBadArg(ItemStack stack) {
        return stack == null || stack.getItem() == null
        // no using NEI as a getho seed scanner.
            || (stack.getItem() instanceof ItemGenericSeed && !SeedStats.getStatsFromStack(stack)
                .isAnalyzed());
    }

    protected static void drawFixesWidthLine(String line, int x, int y, int colour, boolean shadow, int maxWidth) {
        int w = GuiDraw.getStringWidth(line);
        if (w > maxWidth) {
            float scale = (float) maxWidth / (float) w;
            GL11.glPushMatrix();
            GL11.glScalef(scale, 1.0f, 1.0f);
        }
        GuiDraw.drawString(line, x, y, colour, shadow);
        if (w > maxWidth) {
            GL11.glPopMatrix();
        }
    }
}
