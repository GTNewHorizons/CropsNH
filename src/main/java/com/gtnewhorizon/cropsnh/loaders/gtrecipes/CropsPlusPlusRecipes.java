package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static bartworks.API.recipe.BartWorksRecipeMaps.bacterialVatRecipes;
import static gregtech.api.recipe.RecipeMaps.brewingRecipes;
import static gregtech.api.recipe.RecipeMaps.cannerRecipes;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.chemicalReactorRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.distilleryRecipes;
import static gregtech.api.recipe.RecipeMaps.fermentingRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;
import static gregtech.api.util.GTRecipeConstants.GLASS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

import bartworks.API.enums.BioCultureEnum;
import bartworks.common.loaders.BioCultureLoader;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.ToolDictNames;
import gregtech.api.enums.materials.Materials;
import gregtech.api.material.MaterialUtils;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTUtility;

/**
 * This is an assortment of recipes taken from crops++,
 * since that mod will no longer have a reason to exist
 * once crops NH arrives on the scene
 */
public abstract class CropsPlusPlusRecipes extends BaseGTRecipeLoader {

    // TODO: CONSIDER MOVING ALL OF THIS TO CORE MOD OR GT5U

    public static void postInit() {
        addHoneyConversionRecipes();
        addBerryToSugarRecipes();
        addAlcoholRecipes();
        addDyeExtractionRecipes();
    }

    private static void addBerryToSugarRecipes() {
        GTModHandler.addShapelessCraftingRecipe(
            new ItemStack(Items.sugar, 4, 0),
            GTModHandler.RecipeBits.NOT_REMOVABLE | GTModHandler.RecipeBits.BUFFERED
                | GTModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS,
            new Object[] { ToolDictNames.craftingToolMortar, CropsNHItemList.sugarBeet.get(1) });
    }

    private static void addHoneyConversionRecipes() {
        if (!ModUtils.BiomesOPlenty.isModLoaded()) return;
        // bop honey to sugar
        lvRecipe(12, 75).itemInputs(GTUtility.getIntegratedCircuit(9))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000))
            .itemOutputs(new ItemStack(Items.sugar, 9, 0))
            .addTo(centrifugeRecipes);

        // bop honey to forestry honey
        if (ModUtils.Forestry.isModLoaded()) {
            lvRecipe(12, 75).itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("for.honey"), 1000))
                .addTo(centrifugeRecipes);
        }
    }

    private static void addAlcoholBottlingRecipes(Fluid fluid, ItemStack bottle) {
        if (fluid == null || GTUtility.isStackInvalid(bottle)) {
            throw new IllegalArgumentException("Fluid or item stack is invalid");
        }
        GTRecipeBuilder baseRecipe = recipe(1, 0, 20);

        // emptying
        baseRecipe.copy()
            .itemInputs(bottle)
            .itemOutputs(new ItemStack(Items.glass_bottle, 1, 0))
            .fluidOutputs(new FluidStack(fluid, 250))
            .addTo(cannerRecipes);

        // filling
        baseRecipe.copy()
            .itemInputs(new ItemStack(Items.glass_bottle, 1, 0))
            .fluidInputs(new FluidStack(fluid, 250))
            .itemOutputs(bottle)
            .addTo(cannerRecipes);
    }

    private static void addAlcoholRecipes() {
        addAlcoholBottlingRecipes(CropsNHFluids.FWheat, CropsNHItemList.fermentedWheatBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.Korn, CropsNHItemList.kornBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.DKorn, CropsNHItemList.doppelkornBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.FReed, CropsNHItemList.fermentedReedwaterBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.SWhine, CropsNHItemList.sugarWhineBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.Mash, CropsNHItemList.mashBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.Wash, CropsNHItemList.washBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.GHP, CropsNHItemList.highProofBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.jagi, CropsNHItemList.realJagermeisterBottle.get(1));
        addAlcoholBottlingRecipes(CropsNHFluids.njagi, CropsNHItemList.fakeJagermeisterBottle.get(1));
        for (CropRecipes.TierAcid water : new CropRecipes.TierAcid[] { CropRecipes.TierAcid.regWater,
            CropRecipes.TierAcid.distilWater }) {
            evRecipe(17, 50).itemInputs(new ItemStack(Items.sugar, 64))
                .circuit(2)
                .special(BioCultureEnum.getPetriDish(BioCultureLoader.CommonYeast))
                .fluidInputs(water.get(100))
                .fluidOutputs(new FluidStack(CropsNHFluids.GHP, 1))
                .metadata(GLASS, 3)
                .addTo(bacterialVatRecipes);
        }

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.Mash, 10))
            .fluidOutputs(new FluidStack(CropsNHFluids.Wash, 8))
            .duration(50 * SECONDS)
            .eut(2)
            .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.Wash, 20))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 8))
            .duration(50 * SECONDS)
            .eut(2)
            .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wheatyjuice"), 10))
            .fluidOutputs(new FluidStack(CropsNHFluids.FWheat, 8))
            .duration(51 * SECONDS)
            .eut(2)
            .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.reedwater"), 10))
            .fluidOutputs(new FluidStack(CropsNHFluids.FReed, 8))
            .duration(51 * SECONDS)
            .eut(2)
            .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 580), GTUtility.getWater(420))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.piratebrew"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 800), GTUtility.getWater(200))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.beer"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 45), GTUtility.getWater(955))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.darkbeer"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 75), GTUtility.getWater(925))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.cider"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 100), GTUtility.getWater(900))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 120), GTUtility.getWater(880))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 400), GTUtility.getWater(600))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 160), GTUtility.getWater(840))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 320), GTUtility.getWater(680))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 380), GTUtility.getWater(620))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.SWhine, 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 700), GTUtility.getWater(300))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.GHP, 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 750), GTUtility.getWater(250))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.jagi, 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 350), GTUtility.getWater(650))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.njagi, 1000))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 350), GTUtility.getWater(650))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 50))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
            .fluidOutputs(GTUtility.getWater(42))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 35))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
            .fluidOutputs(GTUtility.getWater(60))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 100))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 25))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 100))
            .fluidOutputs(GTUtility.getWater(68))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 100))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 30))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 100))
            .fluidOutputs(GTUtility.getWater(62))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.FWheat, 80))
            .fluidOutputs(new FluidStack(CropsNHFluids.Korn, 1))
            .duration(1 * SECONDS + 2 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(CropsNHFluids.FWheat, 95))
            .fluidOutputs(new FluidStack(CropsNHFluids.DKorn, 1))
            .duration(1 * SECONDS + 4 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(3))
            .fluidInputs(new FluidStack(CropsNHFluids.FWheat, 100))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1))
            .duration(1 * SECONDS + 8 * TICKS)
            .eut(64)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(4))
            .fluidInputs(new FluidStack(CropsNHFluids.FWheat, 200))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 3))
            .duration(1 * SECONDS + 8 * TICKS)
            .eut(64)
            .addTo(distilleryRecipes);

        mvRecipe(2, 75).itemInputs(GTUtility.getIntegratedCircuit(5))
            .fluidInputs(new FluidStack(CropsNHFluids.FWheat, 250))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 1))
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.FReed, 100))
            .fluidOutputs(new FluidStack(CropsNHFluids.SWhine, 7))
            .duration(1 * SECONDS + 2 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(4))
            .fluidInputs(new FluidStack(CropsNHFluids.FReed, 200))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 4))
            .duration(1 * SECONDS + 4 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        mvRecipe(2, 20).itemInputs(GTUtility.getIntegratedCircuit(5))
            .fluidInputs(new FluidStack(CropsNHFluids.FReed, 200))
            .fluidOutputs(MaterialUtils.fluid(Materials.Ethanol, 1))
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(4))
            .fluidInputs(new FluidStack(CropsNHFluids.Mash, 200))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("biomass"), 4))
            .duration(1 * SECONDS + 4 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.Wash, 100))
            .fluidOutputs(new FluidStack(CropsNHFluids.GHP, 6))
            .duration(1 * SECONDS + 2 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(4))
            .fluidInputs(new FluidStack(CropsNHFluids.Wash, 100))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 14))
            .duration(1 * SECONDS + 4 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Items.sugar, 32, 0),
                new ItemStack(Items.dye, 4, 1),
                new ItemStack(Items.dye, 4, 11),
                new ItemStack(Items.dye, 4, 2),
                GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 4))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 4))
            .fluidInputs(new FluidStack(CropsNHFluids.GHP, 375))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 4375))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Items.sugar, 8, 0),
                new ItemStack(Items.dye, 1, 1),
                new ItemStack(Items.dye, 1, 11),
                new ItemStack(Items.dye, 1, 2),
                GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 500))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1500))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Items.sugar, 8, 0),
                new ItemStack(Items.dye, 1, 1),
                new ItemStack(Items.dye, 1, 11),
                new ItemStack(Items.dye, 1, 2),
                GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1))
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 1000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 2000))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Items.sugar, 8, 0),
                new ItemStack(Items.dye, 1, 1),
                new ItemStack(Items.dye, 1, 11),
                new ItemStack(Items.dye, 1, 2),
                GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1))
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 750))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1750))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        if (ModUtils.PamsHarvestCraft.isModLoaded()) {
            GTValues.RA.stdBuilder()
                .itemInputs(
                    new ItemStack(Items.sugar, 8, 0),
                    new Object[] { "cropSpiceleaf", 1 },
                    new Object[] { "cropGinger", 1 },
                    new ItemStack(Items.dye, 1, 2),
                    GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 4000))
                .fluidOutputs(new FluidStack(CropsNHFluids.njagi, 5000))
                .duration(10 * TICKS)
                .eut(8)
                .addTo(mixerRecipes);
        }

        luvRecipe(0, 1)
            .itemInputs(
                OreDictHelper.getCopiedOreStack("cropChilipepper", 1),
                GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.CosmicNeutronium, 1),
                OreDictHelper.getCopiedOreStack("cropLemon", 64),
                OreDictHelper.getCopiedOreStack("cropTea", 64),
                CropsNHItemList.magicEssence.get(8),
                CropsNHItemList.spaceFlower.get(9))
            .fluidInputs(new FluidStack(CropsNHFluids.njagi, 50000))
            .fluidOutputs(new FluidStack(CropsNHFluids.jagi, 250))
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1))
            .fluidInputs(MaterialUtils.fluid(Materials.Ethanol, 1000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 2500))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1), new ItemStack(Items.sugar))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1))
            .fluidInputs(new FluidStack(CropsNHFluids.SWhine, 5000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 6000))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        // Brewery
        for (ItemStack itemStack : OreDictionary.getOres("listAllberry")) {
            GTValues.RA.stdBuilder()
                .itemInputs(itemStack.splitStack(16))
                .fluidInputs(GTUtility.getWater(750))
                .fluidOutputs(new FluidStack(CropsNHFluids.Mash, 750))
                .duration(6 * SECONDS + 8 * TICKS)
                .eut(4)
                .addTo(chemicalReactorRecipes);
        }

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(Items.sugar, 8))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.weakness"), 750))
            .fluidOutputs(new FluidStack(CropsNHFluids.Mash, 750))
            .duration(6 * SECONDS + 8 * TICKS)
            .eut(4)
            .addTo(brewingRecipes);
    }

    private static void addDyeExtractionRecipes() {
        Fluid dyeblack = FluidRegistry.getFluid("dye.chemical.dyeblack");
        Fluid dyeblue = FluidRegistry.getFluid("dye.chemical.dyeblue");
        Fluid dyepink = FluidRegistry.getFluid("dye.chemical.dyepink");
        Fluid dyeyellow = FluidRegistry.getFluid("dye.chemical.dyeyellow");
        Fluid dyegreen = FluidRegistry.getFluid("dye.chemical.dyegreen");
        Fluid dyered = FluidRegistry.getFluid("dye.chemical.dyered");
        Fluid dyepurple = FluidRegistry.getFluid("dye.chemical.dyepurple");
        Fluid dyelime = FluidRegistry.getFluid("dye.chemical.dyelime");
        Fluid dyelightgray = FluidRegistry.getFluid("dye.chemical.dyelightgray");
        Fluid dyelightblue = FluidRegistry.getFluid("dye.chemical.dyelightblue");

        for (ItemStack itemStack : OreDictionary.getOres("cropBlackberry")) {
            addDyeConversionRecipe(itemStack.splitStack(16), dyeblack);
        }

        for (ItemStack itemStack : OreDictionary.getOres("cropBlueberry")) {
            addDyeConversionRecipe(itemStack.splitStack(16), dyeblue);
        }

        for (ItemStack itemStack : OreDictionary.getOres("cropRaspberry")) {
            addDyeConversionRecipe(itemStack.splitStack(16), dyepink);
        }

        for (ItemStack itemStack : OreDictionary.getOres("cropVine")) {
            if (!itemStack.getUnlocalizedName()
                .equals("tile.Thornvines")) {
                addDyeConversionRecipe(itemStack.splitStack(16), dyegreen);
            } else {
                addDyeConversionRecipe(itemStack.splitStack(16), dyeyellow);
            }
        }

        for (ItemStack itemStack : OreDictionary.getOres("cropCacti")) {
            addDyeConversionRecipe(itemStack.splitStack(16), dyegreen);
        }

        for (ItemStack itemStack : OreDictionary.getOres("cropGooseberry")) {
            addDyeConversionRecipe(itemStack.splitStack(16), dyeyellow);
        }

        for (ItemStack itemStack : OreDictionary.getOres("cropStrawberry")) {
            addDyeConversionRecipe(itemStack.splitStack(16), dyered);
        }

        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            // bop berry
            addDyeConversionRecipe(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "food", 16, 0), dyered);
        }

        // huckleberry
        addDyeConversionRecipe(CropsNHItemList.huckleBerry.get(16), dyepurple);

        if (ModUtils.Natura.isModLoaded()) {
            // blight berry
            addDyeConversionRecipe(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 0), dyelime);

            // dusk berry
            addDyeConversionRecipe(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 1), dyelightgray);

            // sky berry
            addDyeConversionRecipe(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 2), dyelightblue);

            // sting berry
            addDyeConversionRecipe(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 3), dyelime);
        }
    }

    // region helpers

    private static void addDyeConversionRecipe(ItemStack input, Fluid dyeFluid) {
        mvRecipe(12, 0).itemInputs(input, GTOreDictUnificator.get(OrePrefixes.dust, Materials.Salt, 2))
            .fluidInputs(MaterialUtils.fluid(Materials.SulfuricAcid, 432))
            .fluidOutputs(new FluidStack(dyeFluid, 288))
            .addTo(UniversalChemical);
    }

    // endregion helpers
}
