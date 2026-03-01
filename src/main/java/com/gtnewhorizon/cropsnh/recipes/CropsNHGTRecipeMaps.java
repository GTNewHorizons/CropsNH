package com.gtnewhorizon.cropsnh.recipes;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropMutation;
import com.gtnewhorizon.cropsnh.init.CropsNHUITextures;
import com.gtnewhorizon.cropsnh.recipes.backends.CropBreederBackend;
import com.gtnewhorizon.cropsnh.recipes.backends.CropGeneExtractorBackend;
import com.gtnewhorizon.cropsnh.recipes.backends.CropSynthesizerBackend;
import com.gtnewhorizon.cropsnh.recipes.backends.SeedGeneratorBackend;
import com.gtnewhorizon.cropsnh.recipes.frontends.CropBreederFrontend;
import com.gtnewhorizon.cropsnh.recipes.frontends.CropGeneExtractorFrontend;
import com.gtnewhorizon.cropsnh.recipes.frontends.CropSynthesizerFrontend;
import com.gtnewhorizon.cropsnh.recipes.frontends.SeedGeneratorFrontend;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropSynthesizer;

import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBuilder;
import gregtech.api.recipe.RecipeMetadataKey;
import gregtech.api.recipe.metadata.SimpleRecipeMetadataKey;

public class CropsNHGTRecipeMaps {

    public static final RecipeMetadataKey<ICropCard> CROPSNH_CROP_METADATAKEY = SimpleRecipeMetadataKey
        .create(ICropCard.class, Reference.MOD_ID + "_crop");
    public static final RecipeMetadataKey<ICropMutation> CROPSNH_CROP_MUTATION_METADATAKEY = SimpleRecipeMetadataKey
        .create(ICropMutation.class, Reference.MOD_ID + "_crop_mutation");

    public static final RecipeMap<SeedGeneratorBackend> fakeSeedGeneratorRecipes = RecipeMapBuilder
        .of(Reference.MOD_ID + ".recipes.seedGenerator", SeedGeneratorBackend::new)
        .maxIO(2, 1, 1, 0)
        .minInputs(1, 1)
        .frontend(SeedGeneratorFrontend::new)
        .disableRegisterNEI()
        .slotOverlays((index, isFluid, isOutput, isSpecial) -> {
            if (!isOutput && !isFluid && index == 0) {
                return CropsNHUITextures.OVERLAY_SLOT_SEED;
            }
            return null;
        })
        .build();

    public static final RecipeMap<CropBreederBackend> fakeCropBreederRecipeMap = RecipeMapBuilder
        .of(Reference.MOD_ID + ".recipes.cropBreeder", CropBreederBackend::new)
        .maxIO(6, 1, 1, 0)
        .minInputs(2, 1)
        .frontend(CropBreederFrontend::new)
        .disableRegisterNEI()
        .slotOverlays((index, isFluid, isOutput, isSpecial) -> {
            if (isOutput && !isFluid && index == 0) {
                return CropsNHUITextures.OVERLAY_SLOT_SEED;
            }
            return null;
        })
        .build();

    public static final RecipeMap<CropGeneExtractorBackend> fakeCropGeneExtractorRecipeMap = RecipeMapBuilder
        .of(Reference.MOD_ID + ".recipes.cropGeneExtractor", CropGeneExtractorBackend::new)
        .maxIO(2, 1, 0, 0)
        .minInputs(2, 0)
        .useSpecialSlot()
        .frontend(CropGeneExtractorFrontend::new)
        .disableRegisterNEI()
        .slotOverlays((index, isFluid, isOutput, isSpecial) -> {
            if (!isOutput && !isFluid && index == 0) {
                return CropsNHUITextures.OVERLAY_SLOT_SEED;
            }
            if (isOutput && !isFluid && index == 0) {
                return GTUITextures.OVERLAY_SLOT_DATA_ORB;
            }
            return null;
        })
        .build();

    public static final RecipeMap<CropSynthesizerBackend> fakeCropSynthesizerRecipeMap = RecipeMapBuilder
        .of(Reference.MOD_ID + ".recipes.cropSynthesizer", CropSynthesizerBackend::new)
        .maxIO(4, 1, 1, 0)
        .minInputs(4, 1)
        .frontend(CropSynthesizerFrontend::new)
        .amperage(MTECropSynthesizer.AMPERAGE)
        .disableRegisterNEI()
        .slotOverlays((index, isFluid, isOutput, isSpecial) -> {
            if (!isFluid && !isSpecial) {
                return isOutput ? CropsNHUITextures.OVERLAY_SLOT_SEED : GTUITextures.OVERLAY_SLOT_DATA_ORB;
            }
            return null;
        })
        .build();
}
