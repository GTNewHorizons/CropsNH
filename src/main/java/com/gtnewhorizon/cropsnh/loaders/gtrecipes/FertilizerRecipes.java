package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.recipe.RecipeMaps.chemicalPlantRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.util.GTRecipeConstants.CHEMPLANT_CASING_TIER;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.ruling_0.materiallib.api.Material;
import com.ruling_0.materiallib.api.MaterialLibAPI;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;
import gregtech.api.objects.SubstituteFluidStack;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTRecipeConstants;
import gregtech.api.util.GTUtility;
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
        if (!ModUtils.Forestry.isModLoaded()) return;
        GTBeeDefinition.FERTILIZER.getSpecies()
            .addSpecialty(CropsNHItemList.fertilizer.get(1), 0.3f);
    }

    private static void addEnrichedFertilizerRecipes() {
        // enriched fertilizer
        // ez logistics puzzle where you either flood both with fertilizer items or use a solution with system like
        // function like item conduits
        lvRecipe(1, 0).itemInputs(CropsNHItemList.fertilizer.get(4))
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
                MaterialLibAPI.getStack(Materials.Calcite, Shapes.dust, 1),
                MaterialLibAPI.getStack(Materials.Carbon, Shapes.dust, 1))
            .fluidOutputs(new FluidStack(FluidRegistry.WATER, 1000))
            .addTo(RecipeMaps.electrolyzerRecipes);
    }

    private static final int TICKS_PER_FORESTRY_SOIL = 8;
    private static final int WATER_PER_FORESTRY_SOIL = 125;

    private static void addForestrySoilRecipes() {
        if (!ModUtils.Forestry.isModLoaded()) return;

        ItemStack humusDirtInput = new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE);
        ItemStack humusOutput = CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 8, 0);
        addTemplatedForestryFertilizerSoilRecipe(
            CropsNHItemList.fertilizer.get(1),
            humusDirtInput.copy(),
            humusOutput.copy());
        addTemplatedForestryFertilizerSoilRecipe(
            ItemList.FR_Fertilizer.get(1),
            humusDirtInput.copy(),
            humusOutput.copy());
        addTemplatedForestryFertilizerSoilRecipe(ItemList.FR_Compost.get(1), humusDirtInput.copy(), humusOutput.copy());
        addTemplatedForestryFertilizerSoilRecipe(ItemList.FR_Mulch.get(8), humusDirtInput, humusOutput);

        ItemStack bogEarthSandInput = new ItemStack(Blocks.sand, 1, OreDictionary.WILDCARD_VALUE);
        ItemStack bogEarthDirtInput = new ItemStack(Blocks.dirt, 1, OreDictionary.WILDCARD_VALUE);
        ItemStack bogEarthOutput = CropsNHUtils.getModItem(ModUtils.Forestry, "soil", 2, 1);
        addTemplatedForestryFertilizerSoilRecipe(bogEarthSandInput, bogEarthDirtInput, bogEarthOutput);
    }

    private static void addTemplatedForestryFertilizerSoilRecipe(ItemStack fertilizer, ItemStack dirt,
        @Nullable ItemStack output) {
        if (CropsNHUtils.isStackInvalid(output)) {
            if (CropsNHUtils.shouldPanicIfNullFound()) {
                throw new IllegalStateException("Attempted to add a foresry soil recipe for an invalid stack!");
            } else {
                try {
                    throw new Exception("CROPS NH FORESTRY SOIL RECIPE HAD NULL OUTPUT");
                } catch (Exception e) {
                    LogHelper.warn(e.getMessage());
                    e.printStackTrace();
                }
                return;
            }
        }
        recipe(16, output.stackSize * TICKS_PER_FORESTRY_SOIL).itemInputs(fertilizer, dirt)
            .circuit(1)
            .itemOutputs(output)
            .fluidInputs(GTUtility.getWater((long) output.stackSize * WATER_PER_FORESTRY_SOIL))
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
        lvRecipe(5, 0)
            .itemInputs(
                new ItemStack(Blocks.dirt, 1, 0),
                MaterialLibAPI.getStack(Materials.Wood, Shapes.dust, 2),
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

        SubstituteFluidStack waterSubstitutions = new SubstituteFluidStack(
            GTUtility.getWater(1_000),
            GTModHandler.getDistilledWater(1_000));

        addTemplatedChemicalRecipes(Materials.Calcite, waterSubstitutions, 2, 3, 2, 1, 1);
        addTemplatedChemicalRecipes(Materials.Calcium, waterSubstitutions, 3, 4, 3, 2, 2);
        addTemplatedChemicalRecipes(Materials.Apatite, waterSubstitutions, 3, 4, 3, 2, 2);
        addTemplatedChemicalRecipes(Materials.Glauconite, waterSubstitutions, 3, 4, 3, 2, 2);
        addTemplatedChemicalRecipes(Materials.GlauconiteSand, waterSubstitutions, 3, 4, 3, 2, 2);

        if (ModUtils.Natura.isModLoaded()) {

            mvRecipe(10, 0)
                .itemInputs(
                    CropsNHUtils.getModItem(ModUtils.Natura, "florasapling", 2, 6),
                    MaterialLibAPI.getStack(Materials.Apatite, Shapes.dust, 1))
                .itemOutputs(
                    CropsNHItemList.fertilizer.get(2),
                    MaterialLibAPI.getStack(Materials.Sulfur, Shapes.dust, 1))
                .fluidInputs(waterSubstitutions)
                .addTo(GTRecipeConstants.UniversalChemical);

            for (int meta = 0; meta <= 2; meta++) {
                ItemStack leafStack = CropsNHUtils.getModItem(ModUtils.Natura, "Dark Leaves", 2, meta);
                mvRecipe(10, 0).itemInputs(leafStack, MaterialLibAPI.getStack(Materials.Apatite, Shapes.dust, 1))
                    .itemOutputs(
                        CropsNHItemList.fertilizer.get(2),
                        MaterialLibAPI.getStack(Materials.Sulfur, Shapes.dust, 1))
                    .fluidInputs(waterSubstitutions)
                    .addTo(GTRecipeConstants.UniversalChemical);
            }

            mvRecipe(10, 0)
                .itemInputs(
                    CropsNHUtils.getModItem(ModUtils.Natura, "Dark Tree", 2, 0),
                    MaterialLibAPI.getStack(Materials.Apatite, Shapes.dust, 1))
                .itemOutputs(
                    CropsNHItemList.fertilizer.get(8),
                    MaterialLibAPI.getStack(Materials.TricalciumPhosphate, Shapes.dust, 1))
                .fluidInputs(waterSubstitutions)
                .addTo(GTRecipeConstants.UniversalChemical);

            mvRecipe(10, 0)
                .itemInputs(
                    CropsNHUtils.getModItem(ModUtils.Natura, "Natura.netherfood", 1, 0),
                    MaterialLibAPI.getStack(Materials.Apatite, Shapes.dust, 1))
                .itemOutputs(
                    CropsNHItemList.fertilizer.get(32),
                    MaterialLibAPI.getStack(Materials.TricalciumPhosphate, Shapes.dust, 1))
                .fluidInputs(waterSubstitutions)
                .addTo(GTRecipeConstants.UniversalChemical);
        }
    }

    public static final int SECONDS_PER_FERTILIZER = 5;

    private static void addTemplatedChemicalRecipes(Material mat, SubstituteFluidStack waterSubstitutions,
        int amountSulfur, int amountTricalcium, int amountPhosphate, int amountAsh, int amountDarkAsh) {
        lvRecipe(amountSulfur * SECONDS_PER_FERTILIZER, 0)
            .itemInputs(
                MaterialLibAPI.getStack(mat, Shapes.dust, 1),
                MaterialLibAPI.getStack(Materials.Sulfur, Shapes.dust, 1))
            .itemOutputs(CropsNHItemList.fertilizer.get(amountSulfur))
            .fluidInputs(waterSubstitutions)
            .addTo(GTRecipeConstants.UniversalChemical);

        lvRecipe(amountTricalcium * SECONDS_PER_FERTILIZER, 0)
            .itemInputs(
                MaterialLibAPI.getStack(mat, Shapes.dust, 1),
                MaterialLibAPI.getStack(Materials.TricalciumPhosphate, Shapes.dust, 1))
            .itemOutputs(CropsNHItemList.fertilizer.get(amountTricalcium))
            .fluidInputs(waterSubstitutions)
            .addTo(GTRecipeConstants.UniversalChemical);

        lvRecipe(amountPhosphate * SECONDS_PER_FERTILIZER, 0)
            .itemInputs(
                MaterialLibAPI.getStack(mat, Shapes.dust, 1),
                MaterialLibAPI.getStack(Materials.Phosphate, Shapes.dust, 1))
            .itemOutputs(CropsNHItemList.fertilizer.get(amountPhosphate))
            .fluidInputs(waterSubstitutions)
            .addTo(GTRecipeConstants.UniversalChemical);

        lvRecipe(amountAsh * SECONDS_PER_FERTILIZER, 0)
            .itemInputs(
                MaterialLibAPI.getStack(mat, Shapes.dust, 1),
                MaterialLibAPI.getStack(Materials.Ash, Shapes.dust, 3))
            .itemOutputs(CropsNHItemList.fertilizer.get(amountAsh))
            .fluidInputs(waterSubstitutions)
            .addTo(GTRecipeConstants.UniversalChemical);

        lvRecipe(amountDarkAsh * SECONDS_PER_FERTILIZER, 0)
            .itemInputs(
                MaterialLibAPI.getStack(mat, Shapes.dust, 1),
                MaterialLibAPI.getStack(Materials.DarkAsh, Shapes.dust, 1))
            .itemOutputs(CropsNHItemList.fertilizer.get(amountDarkAsh))
            .fluidInputs(waterSubstitutions)
            .addTo(GTRecipeConstants.UniversalChemical);
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
