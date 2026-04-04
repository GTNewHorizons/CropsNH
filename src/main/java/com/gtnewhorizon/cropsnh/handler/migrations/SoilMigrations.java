package com.gtnewhorizon.cropsnh.handler.migrations;

import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;
import com.gtnewhorizons.postea.utility.BlockInfo;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

public class SoilMigrations {

    private static final ObjectOpenHashSet<String> TO_MIGRATE = new ObjectOpenHashSet<>();

    public static void postInit() {
        // when a crops gets it's soil changed to something incompatible with it's previous soil, add a migration here
        // and remove it on the next major GTNH release.
    }

    public static void loadCompleted() {
        // skip migration registration if needed
        if (TO_MIGRATE.isEmpty()) return;
        TileEntityReplacementManager.tileEntityTransformer("cropsnh:cropSticksTE", (tag, world, chunk) -> {
            // if no crop, abort
            if (tag == null || !tag.hasKey(Names.NBT.crop, Data.NBTType._object)) {
                return null;
            }
            // if broken seed data abort
            NBTTagCompound seedNBT = tag.getCompoundTag(Names.NBT.crop);
            if (seedNBT == null || !seedNBT.hasKey(Names.NBT.crop, Data.NBTType._string)) return null;
            // check if the seed type is marked for soil migrations
            if (!TO_MIGRATE.contains(seedNBT.getString(Names.NBT.crop))) return null;

            // if it's something we should migrate, migrate it
            return new BlockInfo(CropsNHBlocks.blockCropSticks, 0, (nbt) -> {
                nbt.setBoolean(Names.NBT.hasSoilChanged, true);
                return nbt;
            });
        });
    }

    public static void markForSoilMigration(ICropCard cc) {
        TO_MIGRATE.add(cc.getId());
    }

}
