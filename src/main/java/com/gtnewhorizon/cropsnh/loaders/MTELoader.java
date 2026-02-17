package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.tileentity.multi.MTEIndustrialFarm;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropBreeder;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropGeneExtractor;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropManager;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropSynthesizer;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTESeedGenerator;

import gregtech.api.enums.VoltageIndex;

public class MTELoader {

    public final static int CROP_MANAGER_LV_MTE_ID = 28001;
    public final static int CROP_MANAGER_MV_MTE_ID = 28002;
    public final static int CROP_MANAGER_HV_MTE_ID = 28003;
    public final static int CROP_MANAGER_EV_MTE_ID = 28004;
    public final static int CROP_MANAGER_IV_MTE_ID = 28005;
    public final static int CROP_MANAGER_LUV_MTE_ID = 28006;
    public final static int CROP_MANAGER_ZPM_MTE_ID = 28007;
    public final static int CROP_MANAGER_UV_MTE_ID = 28008;
    public final static int CROP_MANAGER_UHV_MTE_ID = 28009;
    public final static int CROP_MANAGER_UEV_MTE_ID = 28010;
    public final static int CROP_MANAGER_UIV_MTE_ID = 28011;
    public final static int CROP_MANAGER_UMV_MTE_ID = 28012;

    public final static int SEED_GENERATOR_LV_MTE_ID = 28013;
    public final static int SEED_GENERATOR_MV_MTE_ID = 28014;
    public final static int SEED_GENERATOR_HV_MTE_ID = 28015;
    public final static int SEED_GENERATOR_EV_MTE_ID = 28016;
    public final static int SEED_GENERATOR_IV_MTE_ID = 28017;
    public final static int SEED_GENERATOR_LUV_MTE_ID = 28018;
    public final static int SEED_GENERATOR_ZPM_MTE_ID = 28019;
    public final static int SEED_GENERATOR_UV_MTE_ID = 28020;
    public final static int SEED_GENERATOR_UHV_MTE_ID = 28021;
    public final static int SEED_GENERATOR_UEV_MTE_ID = 28022;
    public final static int SEED_GENERATOR_UIV_MTE_ID = 28023;
    public final static int SEED_GENERATOR_UMV_MTE_ID = 28024;

    public final static int CROP_GENE_EXTRACTOR_EV_MTE_ID = 28037;
    public final static int CROP_GENE_EXTRACTOR_IV_MTE_ID = 28038;
    public final static int CROP_GENE_EXTRACTOR_LUV_MTE_ID = 28039;
    public final static int CROP_GENE_EXTRACTOR_ZPM_MTE_ID = 28040;
    public final static int CROP_GENE_EXTRACTOR_UV_MTE_ID = 28041;
    public final static int CROP_GENE_EXTRACTOR_UHV_MTE_ID = 28042;
    public final static int CROP_GENE_EXTRACTOR_UEV_MTE_ID = 28043;
    public final static int CROP_GENE_EXTRACTOR_UIV_MTE_ID = 28044;
    public final static int CROP_GENE_EXTRACTOR_UMV_MTE_ID = 28045;

    public final static int CROP_SYNTHESIZER_EV_MTE_ID = 28046;
    public final static int CROP_SYNTHESIZER_IV_MTE_ID = 28047;
    public final static int CROP_SYNTHESIZER_LUV_MTE_ID = 28048;
    public final static int CROP_SYNTHESIZER_ZPM_MTE_ID = 28049;
    public final static int CROP_SYNTHESIZER_UV_MTE_ID = 28050;
    public final static int CROP_SYNTHESIZER_UHV_MTE_ID = 28051;
    public final static int CROP_SYNTHESIZER_UEV_MTE_ID = 28052;
    public final static int CROP_SYNTHESIZER_UIV_MTE_ID = 28053;
    public final static int CROP_SYNTHESIZER_UMV_MTE_ID = 28054;

    public static void init() {
        CropsNHItemList.CropManager_LV
            .set(new MTECropManager(CROP_MANAGER_LV_MTE_ID, VoltageIndex.LV, "Basic Crop Manager").getStackForm(1L));
        CropsNHItemList.CropManager_MV
            .set(new MTECropManager(CROP_MANAGER_MV_MTE_ID, VoltageIndex.MV, "Advanced Crop Manager").getStackForm(1L));
        CropsNHItemList.CropManager_HV.set(
            new MTECropManager(CROP_MANAGER_HV_MTE_ID, VoltageIndex.HV, "Advanced Crop Manager II").getStackForm(1L));
        CropsNHItemList.CropManager_EV.set(
            new MTECropManager(CROP_MANAGER_EV_MTE_ID, VoltageIndex.EV, "Advanced Crop Manager III").getStackForm(1L));
        CropsNHItemList.CropManager_IV.set(
            new MTECropManager(CROP_MANAGER_IV_MTE_ID, VoltageIndex.IV, "Advanced Crop Manager IV").getStackForm(1L));
        CropsNHItemList.CropManager_LuV
            .set(new MTECropManager(CROP_MANAGER_LUV_MTE_ID, VoltageIndex.LuV, "Elite Crop Manager").getStackForm(1L));
        CropsNHItemList.CropManager_ZPM.set(
            new MTECropManager(CROP_MANAGER_ZPM_MTE_ID, VoltageIndex.ZPM, "Elite Crop Manager II").getStackForm(1L));
        CropsNHItemList.CropManager_UV
            .set(new MTECropManager(CROP_MANAGER_UV_MTE_ID, VoltageIndex.UV, "Ultimate Crop Manager").getStackForm(1L));
        CropsNHItemList.CropManager_UHV
            .set(new MTECropManager(CROP_MANAGER_UHV_MTE_ID, VoltageIndex.UHV, "Epic Crop Manager").getStackForm(1L));
        CropsNHItemList.CropManager_UEV.set(
            new MTECropManager(CROP_MANAGER_UEV_MTE_ID, VoltageIndex.UEV, "Epic Crop Manager II").getStackForm(1L));
        CropsNHItemList.CropManager_UIV.set(
            new MTECropManager(CROP_MANAGER_UIV_MTE_ID, VoltageIndex.UIV, "Epic Crop Manager III").getStackForm(1L));
        CropsNHItemList.CropManager_UMV.set(
            new MTECropManager(CROP_MANAGER_UMV_MTE_ID, VoltageIndex.UMV, "Epic Crop Manager IV").getStackForm(1L));

        CropsNHItemList.SeedGenerator_LV.set(
            new MTESeedGenerator(SEED_GENERATOR_LV_MTE_ID, VoltageIndex.LV, "Basic Seed Generator").getStackForm(1L));
        CropsNHItemList.SeedGenerator_MV.set(
            new MTESeedGenerator(SEED_GENERATOR_MV_MTE_ID, VoltageIndex.MV, "Advanced Seed Generator")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_HV.set(
            new MTESeedGenerator(SEED_GENERATOR_HV_MTE_ID, VoltageIndex.HV, "Advanced Seed Generator II")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_EV.set(
            new MTESeedGenerator(SEED_GENERATOR_EV_MTE_ID, VoltageIndex.EV, "Advanced Seed Generator III")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_IV.set(
            new MTESeedGenerator(SEED_GENERATOR_IV_MTE_ID, VoltageIndex.IV, "Advanced Seed Generator IV")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_LuV.set(
            new MTESeedGenerator(SEED_GENERATOR_LUV_MTE_ID, VoltageIndex.LuV, "Elite Seed Generator").getStackForm(1L));
        CropsNHItemList.SeedGenerator_ZPM.set(
            new MTESeedGenerator(SEED_GENERATOR_ZPM_MTE_ID, VoltageIndex.ZPM, "Elite Seed Generator II")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_UV.set(
            new MTESeedGenerator(SEED_GENERATOR_UV_MTE_ID, VoltageIndex.UV, "Ultimate Seed Replicator")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_UHV.set(
            new MTESeedGenerator(SEED_GENERATOR_UHV_MTE_ID, VoltageIndex.UHV, "Epic Seed Replicator").getStackForm(1L));
        CropsNHItemList.SeedGenerator_UEV.set(
            new MTESeedGenerator(SEED_GENERATOR_UEV_MTE_ID, VoltageIndex.UEV, "Epic Seed Replicator II")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_UIV.set(
            new MTESeedGenerator(SEED_GENERATOR_UIV_MTE_ID, VoltageIndex.UIV, "Epic Seed Replicator III")
                .getStackForm(1L));
        CropsNHItemList.SeedGenerator_UMV.set(
            new MTESeedGenerator(SEED_GENERATOR_UMV_MTE_ID, VoltageIndex.UMV, "Epic Seed Replicator IV")
                .getStackForm(1L));

        CropsNHItemList.CropBreeder_LV
            .set(new MTECropBreeder(28025, VoltageIndex.LV, "Basic Crop Breeder").getStackForm(1L));
        CropsNHItemList.CropBreeder_MV
            .set(new MTECropBreeder(28026, VoltageIndex.MV, "Advanced Crop Breeder").getStackForm(1L));
        CropsNHItemList.CropBreeder_HV
            .set(new MTECropBreeder(28027, VoltageIndex.HV, "Advanced Crop Breeder II").getStackForm(1L));
        CropsNHItemList.CropBreeder_EV
            .set(new MTECropBreeder(28028, VoltageIndex.EV, "Advanced Crop Breeder III").getStackForm(1L));
        CropsNHItemList.CropBreeder_IV
            .set(new MTECropBreeder(28029, VoltageIndex.IV, "Advanced Crop Breeder IV").getStackForm(1L));
        CropsNHItemList.CropBreeder_LuV
            .set(new MTECropBreeder(28030, VoltageIndex.LuV, "Elite Crop Breeder").getStackForm(1L));
        CropsNHItemList.CropBreeder_ZPM
            .set(new MTECropBreeder(28031, VoltageIndex.ZPM, "Elite Crop Breeder II").getStackForm(1L));
        CropsNHItemList.CropBreeder_UV
            .set(new MTECropBreeder(28032, VoltageIndex.UV, "Ultimate Crop Breeder").getStackForm(1L));
        CropsNHItemList.CropBreeder_UHV
            .set(new MTECropBreeder(28033, VoltageIndex.UHV, "Epic Crop Breeder").getStackForm(1L));
        CropsNHItemList.CropBreeder_UEV
            .set(new MTECropBreeder(28034, VoltageIndex.UEV, "Epic Crop Breeder II").getStackForm(1L));
        CropsNHItemList.CropBreeder_UIV
            .set(new MTECropBreeder(28035, VoltageIndex.UIV, "Epic Crop Breeder III").getStackForm(1L));
        CropsNHItemList.CropBreeder_UMV
            .set(new MTECropBreeder(28036, VoltageIndex.UMV, "Epic Crop Breeder IV").getStackForm(1L));

        CropsNHItemList.CropGeneExtractor_EV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_EV_MTE_ID, VoltageIndex.EV, "Crop Gene Extractor")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_IV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_IV_MTE_ID, VoltageIndex.IV, "Advanced Crop Gene Extractor")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_LuV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_LUV_MTE_ID, VoltageIndex.LuV, "Elite Crop Gene Extractor")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_ZPM.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_ZPM_MTE_ID, VoltageIndex.ZPM, "Elite Crop Gene Extractor II")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_UV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_UV_MTE_ID, VoltageIndex.UV, "Ultimate Crop Gene Extractor")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_UHV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_UHV_MTE_ID, VoltageIndex.UHV, "Epic Crop Gene Extractor")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_UEV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_UEV_MTE_ID, VoltageIndex.UEV, "Epic Crop Gene Extractor II")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_UIV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_UIV_MTE_ID, VoltageIndex.UIV, "Epic Crop Gene Extractor III")
                .getStackForm(1L));
        CropsNHItemList.CropGeneExtractor_UMV.set(
            new MTECropGeneExtractor(CROP_GENE_EXTRACTOR_UMV_MTE_ID, VoltageIndex.UMV, "Epic Crop Gene Extractor IV")
                .getStackForm(1L));

        CropsNHItemList.CropSynthesizer_EV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_EV_MTE_ID, VoltageIndex.EV, "Crop Synthesizer").getStackForm(1L));
        CropsNHItemList.CropSynthesizer_IV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_IV_MTE_ID, VoltageIndex.IV, "Advanced Crop Synthesizer")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_LuV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_LUV_MTE_ID, VoltageIndex.LuV, "Elite Crop Synthesizer")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_ZPM.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_ZPM_MTE_ID, VoltageIndex.ZPM, "Elite Crop Synthesizer II")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_UV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_UV_MTE_ID, VoltageIndex.UV, "Ultimate Crop Synthesizer")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_UHV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_UHV_MTE_ID, VoltageIndex.UHV, "Epic Crop Synthesizer")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_UEV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_UEV_MTE_ID, VoltageIndex.UEV, "Epic Crop Synthesizer II")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_UIV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_UIV_MTE_ID, VoltageIndex.UIV, "Epic Crop Synthesizer III")
                .getStackForm(1L));
        CropsNHItemList.CropSynthesizer_UMV.set(
            new MTECropSynthesizer(CROP_SYNTHESIZER_UMV_MTE_ID, VoltageIndex.UMV, "Epic Crop Synthesizer IV")
                .getStackForm(1L));

        CropsNHItemList.IndustrialFarmController
            .set(new MTEIndustrialFarm(28055, "multimachine.industrialfarm", "Industrial Farm").getStackForm(1L));
    }

}
