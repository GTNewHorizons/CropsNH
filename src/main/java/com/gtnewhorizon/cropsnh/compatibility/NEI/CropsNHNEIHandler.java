package com.gtnewhorizon.cropsnh.compatibility.NEI;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class CropsNHNEIHandler extends TemplateRecipeHandler {

    private static final HashMap<Class<? extends CropsNHNEIHandler>, Boolean> handlerStatuses = new HashMap<>();

    public CropsNHNEIHandler() {
        if (!handlerStatuses.containsKey(this.getClass())) {
            handlerStatuses.put(this.getClass(), true);
        }
    }

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
}
