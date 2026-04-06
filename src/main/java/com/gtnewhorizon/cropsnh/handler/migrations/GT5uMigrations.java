package com.gtnewhorizon.cropsnh.handler.migrations;

import static com.gtnewhorizon.cropsnh.handler.MigrationHandler.addOreDictItemOnlyReplacement;

import javax.annotation.Nullable;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.loaders.MTELoader;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.NBTHelper;
import com.gtnewhorizons.postea.api.BlockAccessCompat;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;
import com.gtnewhorizons.postea.utility.BlockInfo;
import com.gtnewhorizons.postea.utility.MissingMappingHandler;

import gregtech.api.GregTechAPI;

public abstract class GT5uMigrations {

    public static void postInit() {
        TileEntityReplacementManager.tileEntityTransformer("BaseMetaTileEntity", (oldNBT, world, chunk) -> {
            // need to copy meta since that controls block harvestability
            int oldBlockMeta = BlockAccessCompat.getBlockMetaAtTE(oldNBT, chunk);
            return switch (oldNBT.getInteger("mID")) {
                // gene extractors
                case 12501, 12502, 12503 -> new BlockInfo(Blocks.air, 0);
                case 12504 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_EV_MTE_ID, oldBlockMeta);
                case 12505 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_IV_MTE_ID, oldBlockMeta);
                case 12506 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_LUV_MTE_ID, oldBlockMeta);
                case 12507 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_ZPM_MTE_ID, oldBlockMeta);
                case 12508 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_UV_MTE_ID, oldBlockMeta);
                case 12509 -> migrateSBMachine(MTELoader.CROP_GENE_EXTRACTOR_UHV_MTE_ID, oldBlockMeta);
                // seed generator/replicators
                case 12510 -> migrateSBMachine(MTELoader.SEED_GENERATOR_LV_MTE_ID, oldBlockMeta);
                case 12511 -> migrateSBMachine(MTELoader.SEED_GENERATOR_MV_MTE_ID, oldBlockMeta);
                case 12512 -> migrateSBMachine(MTELoader.SEED_GENERATOR_HV_MTE_ID, oldBlockMeta);
                case 12513 -> migrateSBMachine(MTELoader.SEED_GENERATOR_EV_MTE_ID, oldBlockMeta);
                case 12514 -> migrateSBMachine(MTELoader.SEED_GENERATOR_IV_MTE_ID, oldBlockMeta);
                case 12515 -> migrateSBMachine(MTELoader.SEED_GENERATOR_LUV_MTE_ID, oldBlockMeta);
                case 12516 -> migrateSBMachine(MTELoader.SEED_GENERATOR_ZPM_MTE_ID, oldBlockMeta);
                case 12517 -> migrateSBMachine(MTELoader.SEED_GENERATOR_UV_MTE_ID, oldBlockMeta);
                case 12518 -> migrateSBMachine(MTELoader.SEED_GENERATOR_UHV_MTE_ID, oldBlockMeta);
                // gene synthesizers
                case 12519, 12520, 12521 -> new BlockInfo(Blocks.air, 0);
                case 12522 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_EV_MTE_ID, oldBlockMeta);
                case 12523 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_IV_MTE_ID, oldBlockMeta);
                case 12524 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_LUV_MTE_ID, oldBlockMeta);
                case 12525 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_ZPM_MTE_ID, oldBlockMeta);
                case 12526 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_UV_MTE_ID, oldBlockMeta);
                case 12527 -> migrateSBMachine(MTELoader.CROP_SYNTHESIZER_UHV_MTE_ID, oldBlockMeta);
                // crop managers
                case 31111 -> migrateCropManager(MTELoader.CROP_MANAGER_LV_MTE_ID, oldBlockMeta);
                case 31112 -> migrateCropManager(MTELoader.CROP_MANAGER_MV_MTE_ID, oldBlockMeta);
                case 31113 -> migrateCropManager(MTELoader.CROP_MANAGER_HV_MTE_ID, oldBlockMeta);
                case 31114 -> migrateCropManager(MTELoader.CROP_MANAGER_EV_MTE_ID, oldBlockMeta);
                case 31115 -> migrateCropManager(MTELoader.CROP_MANAGER_IV_MTE_ID, oldBlockMeta);
                case 31116 -> migrateCropManager(MTELoader.CROP_MANAGER_LUV_MTE_ID, oldBlockMeta);
                case 31117 -> migrateCropManager(MTELoader.CROP_MANAGER_ZPM_MTE_ID, oldBlockMeta);
                case 31118 -> migrateCropManager(MTELoader.CROP_MANAGER_UV_MTE_ID, oldBlockMeta);
                default -> null;
            };
        });

        final String blockMachineId = ModUtils.GregTech.ID + ":gt.blockmachines";
        // crop gene extractor
        // lv -> hv no longer exists, turning to stone because I'm not sure turning a stack to air is a good idea.
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12501, Item.getItemFromBlock(Blocks.stone), 0, true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12502, Item.getItemFromBlock(Blocks.stone), 0, true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12503, Item.getItemFromBlock(Blocks.stone), 0, true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12504, CropsNHItemList.CropGeneExtractor_EV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12505, CropsNHItemList.CropGeneExtractor_IV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12506, CropsNHItemList.CropGeneExtractor_LuV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12507, CropsNHItemList.CropGeneExtractor_ZPM.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12508, CropsNHItemList.CropGeneExtractor_UV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12509, CropsNHItemList.CropGeneExtractor_UHV.get(1), true);

        // crop synthesizers
        // lv -> hv no longer exists, turning to stone because I'm not sure turning a stack to air is a good idea.
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12519, Item.getItemFromBlock(Blocks.stone), 0, true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12520, Item.getItemFromBlock(Blocks.stone), 0, true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12521, Item.getItemFromBlock(Blocks.stone), 0, true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12522, CropsNHItemList.CropSynthesizer_EV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12523, CropsNHItemList.CropSynthesizer_IV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12524, CropsNHItemList.CropSynthesizer_LuV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12525, CropsNHItemList.CropSynthesizer_ZPM.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12526, CropsNHItemList.CropSynthesizer_UV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12527, CropsNHItemList.CropSynthesizer_UHV.get(1), true);

        // seed replicators
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12510, CropsNHItemList.SeedGenerator_LV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12511, CropsNHItemList.SeedGenerator_MV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12512, CropsNHItemList.SeedGenerator_HV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12513, CropsNHItemList.SeedGenerator_EV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12514, CropsNHItemList.SeedGenerator_IV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12515, CropsNHItemList.SeedGenerator_LuV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12516, CropsNHItemList.SeedGenerator_ZPM.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12517, CropsNHItemList.SeedGenerator_UV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 12518, CropsNHItemList.SeedGenerator_UHV.get(1), true);

        // crop managers
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31111, CropsNHItemList.CropManager_LV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31112, CropsNHItemList.CropManager_MV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31113, CropsNHItemList.CropManager_HV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31114, CropsNHItemList.CropManager_EV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31115, CropsNHItemList.CropManager_IV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31116, CropsNHItemList.CropManager_LuV.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31117, CropsNHItemList.CropManager_ZPM.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(blockMachineId, 31118, CropsNHItemList.CropManager_UV.get(1), true);

        // spotless:off
        MissingMappingHandler.addIgnore(ModUtils.GoodGenerator.ID + ":saltyRoot");
        ItemStackReplacementManager
            .addSimpleReplacement(ModUtils.GoodGenerator.ID + ":saltyRoot", CropsNHItemList.saltyRoot.get(1), true);

        final String gtMetaItem2 = ModUtils.GregTech.ID + ":gt.metaitem.02";
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32500, CropsNHItemList.plumbiliaLeaf.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32501, CropsNHItemList.argentiaLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32502, CropsNHItemList.indigoBlossom.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32503, CropsNHItemList.ferrofernLeaf.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32504, CropsNHItemList.auroniaLeaf.get(1), true);
        addOreDictItemOnlyReplacement(gtMetaItem2, 32505, "cropTea");
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32510, CropsNHItemList.oilBerry.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32511, CropsNHItemList.bobsYerUncleBerry.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32512, CropsNHItemList.uumBerry.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32513, CropsNHItemList.uuaBerry.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32520, CropsNHItemList.milkWart.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32521, CropsNHItemList.bauxiaLeaf.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32522, CropsNHItemList.titaniaLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32523, CropsNHItemList.reactoriaLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32524, CropsNHItemList.reactoriaStem.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32526, CropsNHItemList.thunderLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32527, CropsNHItemList.nickelbackLeaf.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32528, CropsNHItemList.galvaniaLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32529, CropsNHItemList.pyrolusiumLeaf.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32530, CropsNHItemList.copponFiber.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32531, CropsNHItemList.scheeliniumLeaf.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32532, CropsNHItemList.platinaLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32533, CropsNHItemList.iridineFlower.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32534, CropsNHItemList.osmianthFlower.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32535, CropsNHItemList.stargatiumLeaf.get(1), true);
        ItemStackReplacementManager
            .addSimpleReplacement(gtMetaItem2, 32538, CropsNHItemList.micadiaFlower.get(1), true);
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32540, CropsNHItemList.tineTwig.get(1), true);
        addOreDictItemOnlyReplacement(gtMetaItem2, 32551, "cropLemon");
        addOreDictItemOnlyReplacement(gtMetaItem2, 32552, "cropTomato");
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32553, CropsNHItemList.maxTomato.get(1), true);
        addOreDictItemOnlyReplacement(gtMetaItem2, 32554, "cropGrape");
        addOreDictItemOnlyReplacement(gtMetaItem2, 32555, "cropOnion");
        addOreDictItemOnlyReplacement(gtMetaItem2, 32556, "cropCucumber");
        ItemStackReplacementManager.addSimpleReplacement(gtMetaItem2, 32557, CropsNHItemList.canolaFlower.get(1), true);
        // spotless:off
    }

    private static @Nullable BlockInfo migrateCropManager(int newMetaID, int oldBlockMeta) {
        return new BlockInfo(GregTechAPI.sBlockMachines, oldBlockMeta, oldNBT -> {
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

    private static @Nullable BlockInfo migrateSBMachine(int newMetaID, int oldBlockMeta) {
        return new BlockInfo(GregTechAPI.sBlockMachines, oldBlockMeta, oldNBT -> {
            NBTTagCompound newNBT = (NBTTagCompound) oldNBT.copy();
            newNBT.setInteger("mID", newMetaID);
            return newNBT;
        });
    }
}
