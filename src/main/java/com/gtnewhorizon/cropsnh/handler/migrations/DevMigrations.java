package com.gtnewhorizon.cropsnh.handler.migrations;

import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.cropsnh.blocks.BlockContainerCropsNH;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;
import com.gtnewhorizons.postea.utility.BlockInfo;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.util.GTModHandler;

// TODO: REMOVE CROPSNH DEV MIGRATIONS BEFORE DAILY INCLUSION
public abstract class DevMigrations {

    public static void postInit() {
        // ID renaming
        TileEntityReplacementManager.tileEntityTransformer(
            "CropsNH:TileEntity_cropSticksTE",
            (tag, world, chunk) -> new BlockInfo(CropsNHBlocks.blockCropSticks, 0, nbt -> {
                nbt.setString("id", BlockContainerCropsNH.wrapName(Names.Objects.cropSticksTE));
                if (nbt.hasKey("crop", Data.NBTType._object)) {
                    CROPNH_DEV_MIGRATE_CROP_TAG(nbt.getCompoundTag("crop"));
                }
                return nbt;
            }));
        // ID renaming
        ItemStackReplacementManager.addItemReplacement(Reference.MOD_ID + ":" + Names.Objects.genericSeed, nbt -> {
            if (nbt.hasKey("tag", Data.NBTType._object)) {
                CROPNH_DEV_MIGRATE_CROP_TAG(nbt.getCompoundTag("tag"));
            }
            return nbt;
        });
    }

    public static void loadComplete() {
        if (ModUtils.CropsPlusPlus.isModLoaded()) {
            GTModHandler.removeRecipeByOutput(CropsNHUtils.getModItem(ModUtils.CropsPlusPlus, "itemLens", 1, 0));
            GTModHandler.removeRecipeByOutput(CropsNHUtils.getModItem(ModUtils.CropsPlusPlus, "itemSpade", 1, 0));
        }
    }

    public static boolean MissingMapping(FMLMissingMappingsEvent.MissingMapping mapping) {
        if (mapping.name.startsWith("CropsNH:")) {
            String id = mapping.name.substring("CropsNH:".length());
            if (mapping.type == GameRegistry.Type.BLOCK) {
                mapping.remap(GameRegistry.findBlock(Reference.MOD_ID, id));
            } else {
                mapping.remap(GameRegistry.findItem(Reference.MOD_ID, id));
            }
            return true;
        }
        return false;
    }

    private static void CROPNH_DEV_MIGRATE_CROP_TAG(NBTTagCompound tag) {
        if (!tag.hasKey("crop", Data.NBTType._string)) return;
        String cropId = tag.getString("crop");
        if (cropId.startsWith("CropsNH:")) {
            tag.setString("crop", Reference.MOD_ID + ":" + cropId.substring("CropsNH:".length()));
        }
    }
}
