package com.gtnewhorizon.cropsnh.handler;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.google.common.collect.LinkedHashMultimap;
import com.gtnewhorizon.cropsnh.farming.materialleaf.MaterialLeafVariant;
import com.gtnewhorizon.cropsnh.handler.migrations.CropsPlusPlusMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.DevMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.GT5uMigrations;
import com.gtnewhorizon.cropsnh.handler.migrations.IC2Migrations;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;

public class MigrationHandler {

    public static final LinkedHashMultimap<String, Function<NBTTagCompound, NBTTagCompound>> ITEM_TRANSFORMERS = LinkedHashMultimap
        .create();

    /**
     * Handles postea migrations
     */
    public static void postInit() {
        if (!ConfigurationHandler.enableMigrations) return;
        CropsPlusPlusMigrations.postInit();
        IC2Migrations.postInit();
        DevMigrations.postInit();
        GT5uMigrations.postInit();

        for (String id : MigrationHandler.ITEM_TRANSFORMERS.keySet()) {
            ItemStackReplacementManager.addItemReplacement(id, nbt -> {
                for (Function<NBTTagCompound, NBTTagCompound> handler : MigrationHandler.ITEM_TRANSFORMERS.get(id)) {
                    NBTTagCompound ret = handler.apply(nbt);
                    if (ret == null) continue;
                    return ret;
                }
                return nbt;
            });
        }
    }

    /**
     * Handles recipe removals
     */
    public static void loadComplete() {
        if (!ConfigurationHandler.enableMigrations) return;
        DevMigrations.loadComplete();
    }

    /**
     * Handles missing mapping events.
     */
    public static void onMissingMigration(FMLMissingMappingsEvent event) {
        if (!ConfigurationHandler.enableMigrations) return;
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.getAll()) {
            DevMigrations.MissingMapping(mapping);
        }
    }

    // TODO: REPLACE WITH APPROPRIATE POSTEA CALLS WHEN POSTEA PR IS MERGED

    // region migration utils

    public static void registerSimpleItemTransformer(@Nonnull String name, @Nonnull String oreDict) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, () -> OreDictHelper.getCopiedOreStack(oreDict)));
    }

    public static void registerSimpleItemTransformer(@Nonnull String name, int oldMeta, @Nonnull String oreDict) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, oldMeta, () -> OreDictHelper.getCopiedOreStack(oreDict)));
    }

    public static void registerSimpleItemTransformer(@Nonnull String name, @Nonnull Item item) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, item));
    }

    public static void registerSimpleItemTransformer(@Nonnull String name, int oldMeta,
        @Nonnull MaterialLeafVariant matLeaf) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, oldMeta, () -> matLeaf.get(1)));
    }

    public static void registerSimpleItemTransformer(@Nonnull String name, @Nonnull MaterialLeafVariant matLeaf) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, () -> matLeaf.get(1)));
    }

    public static void registerSimpleItemTransformer(@Nonnull String name, @Nonnull Item item, int newMeta) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, () -> new ItemStack(item, 1, newMeta)));
    }

    public static void registerSimpleItemTransformer(@Nonnull String name, int oldMeta, @Nonnull Item item,
        int newMeta) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, oldMeta, () -> new ItemStack(item, 1, newMeta)));
    }

    public static void registerSimpleItemTransformer(String name, @Nonnull Supplier<ItemStack> stackSupplier) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, stackSupplier));
    }

    public static void registerSimpleItemTransformer(String name, int oldMeta,
        @Nonnull Supplier<ItemStack> stackSupplier) {
        ITEM_TRANSFORMERS.put(name, nbt -> migrateItem(nbt, oldMeta, stackSupplier));
    }

    public static @Nullable NBTTagCompound migrateItem(@Nonnull NBTTagCompound nbt, int oldMeta,
        @Nonnull Supplier<ItemStack> stackSupplier) {
        // check damage
        if (!nbt.hasKey("Damage", Data.NBTType._short) || nbt.getShort("Damage") != (short) oldMeta) return null;
        return migrateItem(nbt, stackSupplier);
    }

    public static @Nullable NBTTagCompound migrateItem(@Nonnull NBTTagCompound nbt, Supplier<ItemStack> stackSupplier) {
        ItemStack stack = stackSupplier.get();
        // abort early if stack is null
        if (stack == null || stack.getItem() == null) return null;
        nbt.setShort("id", (short) Item.getIdFromItem(stack.getItem()));
        nbt.setShort("Damage", (short) CropsNHUtils.getItemMeta(stack));
        return nbt;
    }

    public static @Nullable NBTTagCompound migrateItem(@Nonnull NBTTagCompound nbt, @Nonnull Item item) {
        nbt.setShort("id", (short) Item.getIdFromItem(item));
        return nbt;
    }

    // endregion migration utils

}
