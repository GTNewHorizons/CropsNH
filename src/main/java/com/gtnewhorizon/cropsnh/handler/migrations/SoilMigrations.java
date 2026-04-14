package com.gtnewhorizon.cropsnh.handler.migrations;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

public class SoilMigrations {

    private static final ObjectOpenHashSet<String> TO_MIGRATE = new ObjectOpenHashSet<>();

    public static void postInit() {
        // when a crops gets it's soil changed to something incompatible with its previous soil, add a migration here
        // and remove it on the next major GTNH release.

        // TODO: REMOVE BEFORE THE NEXT MAJOR RELEASE OF GTNH AFTER 2.9
        // changed from stone to oil
        markForSoilMigration(CropsNHCrops.OilBerry);
    }

    public static void loadCompleted() {
        // skip migration registration if needed
        if (TO_MIGRATE.isEmpty()) return;
        TileEntityReplacementManager.tileEntityTransformer("cropsnh:cropSticksTE", (tag, world, chunk) -> {
            // if no crop, abort
            if (tag == null || !tag.hasKey(Names.NBT.crop, Constants.NBT.TAG_COMPOUND)) {
                return null;
            }
            // if broken seed data abort
            NBTTagCompound seedNBT = tag.getCompoundTag(Names.NBT.crop);
            if (seedNBT == null || !seedNBT.hasKey(Names.NBT.crop, Constants.NBT.TAG_STRING)) return null;
            // check if the seed type is marked for soil migrations
            if (!TO_MIGRATE.contains(seedNBT.getString(Names.NBT.crop))) return null;

            // set the tag and tell postea the block doesn't need to change.
            tag.setBoolean(Names.NBT.hasSoilChanged, true);;
            return null;
        });
    }

    public static void markForSoilMigration(ICropCard cc) {
        TO_MIGRATE.add(cc.getId());
    }

}
