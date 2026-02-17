package com.gtnewhorizon.cropsnh.handler.migrations;

import static com.gtnewhorizon.cropsnh.handler.MigrationHandler.registerSimpleItemTransformer;

import javax.annotation.Nullable;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.init.CropsNHItems;
import com.gtnewhorizon.cropsnh.items.produce.ItemBerry;
import com.gtnewhorizon.cropsnh.loaders.MTELoader;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;
import com.gtnewhorizons.postea.utility.BlockInfo;

import gregtech.api.GregTechAPI;

public abstract class GT5uMigrations {

    public static void postInit() {
        TileEntityReplacementManager
            .tileEntityTransformer("BaseMetaTileEntity", (oldNBT, world, chunk) -> switch (oldNBT.getInteger("mID")) {
            // gene extractors
            case 12501, 12502, 12503 -> new BlockInfo(Blocks.air, 0);
            case 12504 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_EV_MTE_ID);
            case 12505 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_IV_MTE_ID);
            case 12506 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_LUV_MTE_ID);
            case 12507 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_ZPM_MTE_ID);
            case 12508 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_UV_MTE_ID);
            case 12509 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_UHV_MTE_ID);
            // seed generator/replicators
            case 12510 -> migrateSBMachine(MTELoader.SEED_GENERATOR_LV_MTE_ID);
            case 12511 -> migrateSBMachine(MTELoader.SEED_GENERATOR_MV_MTE_ID);
            case 12512 -> migrateSBMachine(MTELoader.SEED_GENERATOR_HV_MTE_ID);
            case 12513 -> migrateSBMachine(MTELoader.SEED_GENERATOR_EV_MTE_ID);
            case 12514 -> migrateSBMachine(MTELoader.SEED_GENERATOR_IV_MTE_ID);
            case 12515 -> migrateSBMachine(MTELoader.SEED_GENERATOR_LUV_MTE_ID);
            case 12516 -> migrateSBMachine(MTELoader.SEED_GENERATOR_ZPM_MTE_ID);
            case 12517 -> migrateSBMachine(MTELoader.SEED_GENERATOR_UV_MTE_ID);
            case 12518 -> migrateSBMachine(MTELoader.SEED_GENERATOR_UHV_MTE_ID);
            // gene synthesizers
            case 12519, 12520, 12521 -> new BlockInfo(Blocks.air, 0);
            case 12522 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_EV_MTE_ID);
            case 12523 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_IV_MTE_ID);
            case 12524 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_LUV_MTE_ID);
            case 12525 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_ZPM_MTE_ID);
            case 12526 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_UV_MTE_ID);
            case 12527 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_UHV_MTE_ID);
            // crop managers
            case 31111 -> migrateCropManager(MTELoader.CROP_MANAGER_LV_MTE_ID);
            case 31112 -> migrateCropManager(MTELoader.CROP_MANAGER_MV_MTE_ID);
            case 31113 -> migrateCropManager(MTELoader.CROP_MANAGER_HV_MTE_ID);
            case 31114 -> migrateCropManager(MTELoader.CROP_MANAGER_EV_MTE_ID);
            case 31115 -> migrateCropManager(MTELoader.CROP_MANAGER_IV_MTE_ID);
            case 31116 -> migrateCropManager(MTELoader.CROP_MANAGER_LUV_MTE_ID);
            case 31117 -> migrateCropManager(MTELoader.CROP_MANAGER_ZPM_MTE_ID);
            case 31118 -> migrateCropManager(MTELoader.CROP_MANAGER_UV_MTE_ID);
            default -> null;
            });

        final String gtMetaItem2 = ModUtils.GregTech.ID + ":gt.metaitem.02";
        // spotless:off
        registerSimpleItemTransformer(ModUtils.GoodGenerator.ID + ":saltyRoot", MaterialLeafLoader.saltyRoot);
        registerSimpleItemTransformer(gtMetaItem2, 32500, MaterialLeafLoader.plumbiliaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32501, MaterialLeafLoader.argentiaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32502, MaterialLeafLoader.indigoBlossom);
        registerSimpleItemTransformer(gtMetaItem2, 32503, MaterialLeafLoader.ferrofernLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32504, MaterialLeafLoader.auroniaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32505, "cropTea");
        registerSimpleItemTransformer(gtMetaItem2, 32510, MaterialLeafLoader.oilBerry);
        registerSimpleItemTransformer(gtMetaItem2, 32511, MaterialLeafLoader.bobsYerUncleBerry);
        registerSimpleItemTransformer(gtMetaItem2, 32512, MaterialLeafLoader.uumBerry);
        registerSimpleItemTransformer(gtMetaItem2, 32513, MaterialLeafLoader.uuaBerry);
        registerSimpleItemTransformer(gtMetaItem2, 32520, MaterialLeafLoader.milkWart);
        registerSimpleItemTransformer(gtMetaItem2, 32521, MaterialLeafLoader.bauxiaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32522, MaterialLeafLoader.titaniaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32523, MaterialLeafLoader.reactoriaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32524, MaterialLeafLoader.reactoriaStem);
        registerSimpleItemTransformer(gtMetaItem2, 32526, MaterialLeafLoader.thunderFlower);
        registerSimpleItemTransformer(gtMetaItem2, 32527, MaterialLeafLoader.nickelbackLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32528, MaterialLeafLoader.galvaniaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32529, MaterialLeafLoader.pyrolusiumLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32530, MaterialLeafLoader.copponFiber);
        registerSimpleItemTransformer(gtMetaItem2, 32531, MaterialLeafLoader.scheeliniumLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32532, MaterialLeafLoader.platinaLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32533, MaterialLeafLoader.iridineFlower);
        registerSimpleItemTransformer(gtMetaItem2, 32534, MaterialLeafLoader.osmianthFlower);
        registerSimpleItemTransformer(gtMetaItem2, 32535, MaterialLeafLoader.stargatiumLeaf);
        registerSimpleItemTransformer(gtMetaItem2, 32538, MaterialLeafLoader.micadiaFlower);
        registerSimpleItemTransformer(gtMetaItem2, 32540, MaterialLeafLoader.tineTwig);
        registerSimpleItemTransformer(gtMetaItem2, 32550, "cropChilipepper");
        registerSimpleItemTransformer(gtMetaItem2, 32551, "cropLemon");
        registerSimpleItemTransformer(gtMetaItem2, 32552, "cropTomato");
        registerSimpleItemTransformer(gtMetaItem2, 32553, CropsNHItems.berry, ItemBerry.META_MAX_TOMATO);
        registerSimpleItemTransformer(gtMetaItem2, 32554, "cropGrape");
        registerSimpleItemTransformer(gtMetaItem2, 32555, "cropOnion");
        registerSimpleItemTransformer(gtMetaItem2, 32556, "cropCucumber");
        registerSimpleItemTransformer(gtMetaItem2, 32557, MaterialLeafLoader.canolaFLower);
        // spotless:off
    }

    private static @Nullable BlockInfo migrateCropManager(int newMetaID) {
        return new BlockInfo(GregTechAPI.sBlockMachines, 1, oldNBT -> {
            NBTTagCompound newNBT = (NBTTagCompound) oldNBT.copy();
            newNBT.setInteger("mID", newMetaID);
            if (newNBT.hasKey("mFluid", Data.NBTType._object)) {
                FluidStack stack = FluidStack.loadFluidStackFromNBT(newNBT.getCompoundTag("mFluid"));
                if (stack.getFluid() == CropsNHUtils.getWeedEXFluid()) {
                    newNBT.setInteger("mWeedEx", stack.amount);
                } else {
                    newNBT.setInteger("mWater", stack.amount);
                }
                newNBT.removeTag("mFluid");
            }
            if (newNBT.hasKey("mModeAlternative")) {
                boolean altsEnabled = NBTHelper.getBoolean(newNBT, "mModeAlternative", false);
                newNBT.setBoolean("mFertilizerEnabled", altsEnabled);
                newNBT.setBoolean("mWaterEnabled", altsEnabled);
                newNBT.setBoolean("mWeedExEnabled", altsEnabled);
                newNBT.removeTag("mModeAlternative");
            }
            return newNBT;
        });
    }

    private static @Nullable BlockInfo migrateSBMachine(int newMetaID) {
        return new BlockInfo(GregTechAPI.sBlockMachines, 1, oldNBT -> {
            NBTTagCompound newNBT = (NBTTagCompound) oldNBT.copy();
            newNBT.setInteger("mID", newMetaID);
            return newNBT;
        });
    }
}
