package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.util.GTRecipeConstants.CHEMPLANT_CASING_TIER;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.chemicalPlantRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeConstants;
import gregtech.loaders.misc.GTBeeDefinition;
import gtPlusPlus.core.fluids.GTPPFluids;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;

public abstract class FertilizerRecipes extends BaseGTRecipeLoader {

    public static void postInit() {
        addEnrichedFertilizerRecipes();
        addChemicalReactorRecipes();
        addRecyclingRecipes();
        addFluidConversionRecipes();
        addForestrySoilRecipes();
        addNaturaExtractorRecipes();
        addChemplantRecipes();
        addBeeCompat();
    }

    private static void addBeeCompat() {
        GTBeeDefinition.FERTILIZER.getSpecies()
            .addSpecialty(CropsNHItemList.fertilizer.get(1), 0.3f);
    }

    private static void addEnrichedFertilizerRecipes() {
        // enriched fertilizer
        // ez logistics puzzle where you either flood both with fertilizer items or use a solution with system like
        // function like item conduits
        lvRecipe(5, 0).itemInputs(CropsNHItemList.fertilizer.get(4))
            .fluidInputs(new FluidStack(CropsNHFluids.fertilizer, 1000))
            .fluidOutputs(new FluidStack(CropsNHFluids.enrichedFertilizer, 1000))
            .addTo(GTRecipeConstants.UniversalChemical);

        // cell only recipe for sb
        lvRecipe(1, 0)
            .itemInputs(
                CropsNHItemList.fertilizer.get(4),
                FluidContainerRegistry
                    .fillFluidContainer(new FluidStack(CropsNHFluids.fertilizer, 1000), ItemList.Cell_Empty.get(1)))
            .itemOutputs(CropsNHItemList.enrichedFertilizerCell.get(1))
            .addTo(RecipeMaps.chemicalReactorRecipes);
    }

    private static void addFluidConversionRecipes() {
        // fert to liquid
        recipe(16, 1, 0).itemInputs(CropsNHItemList.fertilizer.get(1))
            .fluidOutputs(new FluidStack(CropsNHFluids.fertilizer, Constants.FERTILIZER_ITEM_FLUID_VALUE))
            .addTo(RecipeMaps.fluidExtractionRecipes);

        if (ModUtils.Forestry.isModLoaded()) {
            recipe(16, 0, 25).itemInputs(ItemList.FR_Fertilizer.get(1))
                .fluidOutputs(new FluidStack(CropsNHFluids.fertilizer, 36))
                .addTo(fluidExtractionRecipes);
        }

        // liquid to fert
        recipe(16, 2, 0).circuit(1)
            .fluidInputs(new FluidStack(CropsNHFluids.fertilizer, Constants.FERTILIZER_ITEM_FLUID_VALUE))
            .itemOutputs(CropsNHItemList.fertilizer.get(1))
            .addTo(RecipeMaps.centrifugeRecipes);
    }

    private static void addRecyclingRecipes() {
        lvRecipe(5, 0).itemInputs(CropsNHItemList.fertilizer.get(1))
            .itemOutputs(
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1))
            .fluidOutputs(new FluidStack(FluidRegistry.WATER, 1000))
            .addTo(RecipeMaps.electrolyzerRecipes);
    }

    private static void addForestrySoilRecipes() {
        if (!ModUtils.Forestry.isModLoaded()) return;

        recipe(16, 3, 20).itemInputs(CropsNHItemList.fertilizer.get(1), new ItemStack(Blocks.dirt, 8, 32767))
            .circuit(1)
            .itemOutputs(CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 8, 0))
            .fluidInputs(Materials.Water.getFluid(1_000))
            .addTo(mixerRecipes);

        recipe(16, 3, 20).itemInputs(ItemList.FR_Fertilizer.get(1), new ItemStack(Blocks.dirt, 8, 32767))
            .circuit(1)
            .itemOutputs(CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 8, 0))
            .fluidInputs(Materials.Water.getFluid(1_000))
            .addTo(mixerRecipes);

        recipe(16, 3, 20).itemInputs(ItemList.FR_Compost.get(1), new ItemStack(Blocks.dirt, 8, 32767))
            .circuit(1)
            .itemOutputs(CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 8, 0))
            .fluidInputs(Materials.Water.getFluid(1_000))
            .addTo(mixerRecipes);

        recipe(16, 3, 20).itemInputs(ItemList.FR_Mulch.get(8), new ItemStack(Blocks.dirt, 8, 32767))
            .circuit(1)
            .itemOutputs(CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 8, 0))
            .fluidInputs(Materials.Water.getFluid(1_000))
            .addTo(mixerRecipes);

        recipe(16, 0, 80).itemInputs(new ItemStack(Blocks.sand, 1, 32767), new ItemStack(Blocks.dirt, 1, 32767))
            .circuit(1)
            .itemOutputs(CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 2, 1))
            .fluidInputs(Materials.Water.getFluid(250))
            .addTo(mixerRecipes);
    }

    private static void addChemplantRecipes() {
        if (ModUtils.Forestry.isModLoaded()) {
            recipe(60, 30, 0).itemInputs(GregtechItemList.GreenAlgaeBiomass.get(16), GregtechItemList.Compost.get(8))
                .circuit(11)
                .itemOutputs(ItemList.FR_Fertilizer.get(32))
                .fluidInputs(new FluidStack(GTPPFluids.Urea, 200))
                .metadata(CHEMPLANT_CASING_TIER, 1)
                .addTo(chemicalPlantRecipes);
        }

        recipe(60, 30, 0).itemInputs(GregtechItemList.GreenAlgaeBiomass.get(16), GregtechItemList.Compost.get(8))
            .circuit(12)
            .itemOutputs(CropsNHItemList.fertilizer.get(32))
            .fluidInputs(new FluidStack(GTPPFluids.Urea, 200))
            .metadata(CHEMPLANT_CASING_TIER, 1)
            .addTo(chemicalPlantRecipes);
    }

    private static void addChemicalReactorRecipes() {
        // TODO: REMOVE RECIPES FROM NH CORE MOD
        lvRecipe(5, 0)
            .itemInputs(
                new ItemStack(Blocks.dirt, 1, 0),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2L),
                new ItemStack(Blocks.sand, 4, 0))
            .itemOutputs(CropsNHItemList.fertilizer.get(4))
            .fluidInputs(new FluidStack(FluidRegistry.WATER, 1000))
            .addTo(RecipeMaps.mixerRecipes);

        // fertilized dirt
        if (ModUtils.RandomThings.isModLoaded()) {
            lvRecipe(5, 0).itemInputs(new ItemStack(Blocks.dirt, 1, 0), CropsNHItemList.fertilizer.get(2))
                .itemOutputs(CropsNHUtils.getModItem(ModUtils.RandomThings, "fertilizedDirt", 1, 0))
                .fluidInputs(FluidRegistry.getFluidStack("water", 1000))
                .addTo(UniversalChemical);
        }

        // TODO: REMOVE EXISTING RECIPES FROM GT5U
        for (Fluid tFluid : new Fluid[] { FluidRegistry.WATER, GTModHandler.getDistilledWater(1L)
            .getFluid() }) {

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(5, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(1))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(5, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.AshDark, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(1))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(20, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.AshDark, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(20, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.AshDark, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(20, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.AshDark, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(20, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(15, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            lvRecipe(10, 0)
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.AshDark, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .addTo(GTRecipeConstants.UniversalChemical);

            if (ModUtils.Natura.isModLoaded()) {

                mvRecipe(10, 0)
                    .itemInputs(
                        CropsNHUtils.getModItem(ModUtils.Natura, "florasapling", 2, 6),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(2),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .addTo(GTRecipeConstants.UniversalChemical);

                for (int meta = 0; meta <= 2; meta++) {
                    ItemStack leafStack = CropsNHUtils.getModItem(ModUtils.Natura, "Dark Leaves", 2, meta);
                    mvRecipe(10, 0)
                        .itemInputs(leafStack, GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                        .itemOutputs(
                            CropsNHItemList.fertilizer.get(2),
                            GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L))
                        .fluidInputs(new FluidStack(tFluid, 1000))
                        .addTo(GTRecipeConstants.UniversalChemical);
                }

                mvRecipe(10, 0)
                    .itemInputs(
                        CropsNHUtils.getModItem(ModUtils.Natura, "Dark Tree", 2, 0),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(8),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .addTo(GTRecipeConstants.UniversalChemical);

                mvRecipe(10, 0)
                    .itemInputs(
                        CropsNHUtils.getModItem(ModUtils.Natura, "Natura.netherfood", 1, 0),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(32),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .addTo(GTRecipeConstants.UniversalChemical);
            }
        }
    }

    private static void addNaturaExtractorRecipes() {
        if (!ModUtils.Natura.isModLoaded()) return;

        mvRecipe(5, 0).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "florasapling", 2, 6))
            .itemOutputs(CropsNHItemList.fertilizer.get(1))
            .addTo(RecipeMaps.extractorRecipes);

        for (int meta = 0; meta <= 2; meta++) {
            ItemStack leafStack = CropsNHUtils.getModItem(ModUtils.Natura, "Dark Leaves", 8, meta);
            mvRecipe(5, 0).itemInputs(leafStack)
                .itemOutputs(CropsNHItemList.fertilizer.get(1))
                .addTo(RecipeMaps.extractorRecipes);
        }

        mvRecipe(5, 0).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "Dark Tree", 2, 0))
            .itemOutputs(CropsNHItemList.fertilizer.get(1))
            .addTo(RecipeMaps.extractorRecipes);
        mvRecipe(5, 0).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "Natura.netherfood", 1, 0))
            .itemOutputs(CropsNHItemList.fertilizer.get(4))
            .addTo(RecipeMaps.extractorRecipes);
    }

}
