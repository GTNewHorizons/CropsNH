package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import gregtech.api.util.GTRecipeConstants;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gtPlusPlus.core.item.ModItems;
import mods.natura.common.NContent;

public class ChemicalReactorRecipes {

    public static void postInit() {
        addFertilizerItemRecipes();
        addEnrichedFertilizerRecipes();
    }

    private static void addEnrichedFertilizerRecipes() {
        // enriched fertilizer
        // ez logistics puzzle where you either flood both with fertilizer items or use a solution with system like
        // function like item conduits
        GTValues.RA.stdBuilder()
            .itemInputs(CropsNHItemList.fertilizer.get(4))
            .fluidInputs(new FluidStack(ModItems.fluidFertBasic, 1000))
            .fluidOutputs(new FluidStack(CropsNHFluids.enrichedFertilizer, 1000))
            .duration(SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(GTRecipeConstants.UniversalChemical);

        // cell only recipe for sb
        GTValues.RA.stdBuilder()
            .itemInputs(
                CropsNHItemList.fertilizer.get(4),
                FluidContainerRegistry
                    .fillFluidContainer(new FluidStack(ModItems.fluidFertBasic, 1000), ItemList.Cell_Empty.get(1)))
            .itemOutputs(CropsNHItemList.enrichedFertilizerCell.get(1))
            .duration(SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(RecipeMaps.chemicalReactorRecipes);
    }

    private static void addFertilizerItemRecipes() {
        // TODO: REMOVE EXISTING RECIPES FROM GT5U
        for (Fluid tFluid : new Fluid[] { FluidRegistry.WATER, GTModHandler.getDistilledWater(1L)
            .getFluid() }) {
            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(1))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(5 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(1))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(5 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(20 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(20 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(20 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(4))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(20 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(3))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(15 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.GlauconiteSand, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1))
                .itemOutputs(CropsNHItemList.fertilizer.get(2))
                .fluidInputs(new FluidStack(tFluid, 1000))
                .duration(10 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(GTRecipeConstants.UniversalChemical);

            // TODO: REMOVE EXISTING RECIPES FROM NH CORE MOD
            if (Mods.Natura.isModLoaded()) {

                GTValues.RA.stdBuilder()
                    .itemInputs(
                        new ItemStack(NContent.floraSapling, 2, 6),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(2),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .duration(10 * SECONDS)
                    .eut(TierEU.RECIPE_MV)
                    .addTo(GTRecipeConstants.UniversalChemical);

                GTValues.RA.stdBuilder()
                    .itemInputs(
                        new ItemStack(NContent.darkLeaves, 8, 0),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(2),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .duration(10 * SECONDS)
                    .eut(TierEU.RECIPE_MV)
                    .addTo(GTRecipeConstants.UniversalChemical);

                GTValues.RA.stdBuilder()
                    .itemInputs(
                        new ItemStack(NContent.darkTree, 2, 0),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(8),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .duration(10 * SECONDS)
                    .eut(TierEU.RECIPE_MV)
                    .addTo(GTRecipeConstants.UniversalChemical);

                GTValues.RA.stdBuilder()
                    .itemInputs(
                        new ItemStack(NContent.potashApple, 1, 0),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(32),
                        GTOreDictUnificator.get(OrePrefixes.dust, Materials.TricalciumPhosphate, 1L))
                    .fluidInputs(new FluidStack(tFluid, 1000))
                    .duration(10 * SECONDS)
                    .eut(TierEU.RECIPE_MV)
                    .addTo(GTRecipeConstants.UniversalChemical);
            }
        }
    }

}
