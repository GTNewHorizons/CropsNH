package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static gregtech.api.enums.Mods.BiomesOPlenty;
import static gregtech.api.enums.Mods.Natura;
import static gregtech.api.recipe.RecipeMaps.brewingRecipes;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.chemicalReactorRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.distilleryRecipes;
import static gregtech.api.recipe.RecipeMaps.fermentingRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidCannerRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.init.CropsNHFluids;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.recipe.RecipeMaps;
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
        addStoneDustCompressionRecipes();
        addHoneyConversionRecipes();
        addAlcoholRecipes();
        addDyeExtractionRecipes();
    }

    private static void addStoneDustCompressionRecipes() {
        // stone lily related things

        // stone plate to stone block
        ulvRecipe(3, 75).itemInputs(Materials.Stone.getPlates(9))
            .itemOutputs(new ItemStack(Blocks.stone))
            .addTo(RecipeMaps.compressorRecipes);

        // marble dust to marble block
        ulvRecipe(3, 75).itemInputs(Materials.Marble.getDust(9))
            .itemOutputs(Materials.Marble.getBlocks(1))
            .addTo(RecipeMaps.compressorRecipes);

        // red granite dust to red granite plate
        ulvRecipe(3, 75).itemInputs(Materials.GraniteRed.getDust(1))
            .itemOutputs(Materials.GraniteRed.getPlates(1))
            .addTo(RecipeMaps.compressorRecipes);

        // red granite plate to red granite block
        ulvRecipe(3, 75).itemInputs(Materials.GraniteRed.getPlates(9))
            .itemOutputs(new ItemStack(GregTechAPI.sBlockGranites, 1, 8))
            .addTo(RecipeMaps.compressorRecipes);

        // black granite dust to black granite plate
        ulvRecipe(3, 75).itemInputs(Materials.GraniteBlack.getDust(1))
            .itemOutputs(Materials.GraniteBlack.getPlates(1))
            .addTo(RecipeMaps.compressorRecipes);

        // black granite plates to black granite block
        ulvRecipe(3, 75).itemInputs(Materials.GraniteBlack.getPlates(9))
            .itemOutputs(new ItemStack(GregTechAPI.sBlockGranites))
            .addTo(RecipeMaps.compressorRecipes);
    }

    private static void addHoneyConversionRecipes() {
        if (!Mods.BiomesOPlenty.isModLoaded()) return;
        // bop honey to sugar
        lvRecipe(12, 75).itemInputs(GTUtility.getIntegratedCircuit(9))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000))
            .itemOutputs(new ItemStack(Items.sugar, 9, 0))
            .addTo(centrifugeRecipes);

        // bop honey to forestry honey
        if (Mods.Forestry.isModLoaded()) {
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
            .addTo(fluidCannerRecipes);

        // filling
        baseRecipe.copy()
            .itemInputs(new ItemStack(Items.glass_bottle, 1, 0))
            .fluidInputs(new FluidStack(fluid, 250))
            .itemOutputs(bottle)
            .addTo(fluidCannerRecipes);
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
            .fluidOutputs(Materials.Ethanol.getFluid(580), Materials.Water.getFluid(420))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.piratebrew"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(800), Materials.Water.getFluid(200))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.beer"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(45), Materials.Water.getFluid(955))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.darkbeer"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(75), Materials.Water.getFluid(925))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.cider"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(100), Materials.Water.getFluid(900))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(120), Materials.Water.getFluid(880))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(400), Materials.Water.getFluid(600))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(160), Materials.Water.getFluid(840))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(320), Materials.Water.getFluid(680))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(380), Materials.Water.getFluid(620))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.SWhine, 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(700), Materials.Water.getFluid(300))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.GHP, 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(750), Materials.Water.getFluid(250))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.jagi, 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(350), Materials.Water.getFluid(650))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(new FluidStack(CropsNHFluids.njagi, 1000))
            .fluidOutputs(Materials.Ethanol.getFluid(350), Materials.Water.getFluid(650))
            .duration(4 * SECONDS)
            .eut(180)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
            .fluidOutputs(Materials.Ethanol.getFluid(50))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
            .fluidOutputs(Materials.Water.getFluid(42))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
            .fluidOutputs(Materials.Ethanol.getFluid(35))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
            .fluidOutputs(Materials.Water.getFluid(60))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 100))
            .fluidOutputs(Materials.Ethanol.getFluid(25))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(CropsNHFluids.Korn, 100))
            .fluidOutputs(Materials.Water.getFluid(68))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 100))
            .fluidOutputs(Materials.Ethanol.getFluid(30))
            .duration(16 * TICKS)
            .eut(24)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 100))
            .fluidOutputs(Materials.Water.getFluid(62))
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
            .fluidOutputs(Materials.Ethanol.getFluid(1))
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
            .fluidOutputs(Materials.Ethanol.getFluid(1))
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
                Materials.Water.getCells(4))
            .itemOutputs(Materials.Empty.getCells(4))
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
                Materials.Water.getCells(1))
            .itemOutputs(Materials.Empty.getCells(1))
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
                Materials.Water.getCells(1))
            .itemOutputs(Materials.Empty.getCells(1))
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
                Materials.Water.getCells(1))
            .itemOutputs(Materials.Empty.getCells(1))
            .fluidInputs(new FluidStack(CropsNHFluids.DKorn, 750))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1750))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        if (Mods.PamsHarvestCraft.isModLoaded()) {
            GTValues.RA.stdBuilder()
                .itemInputs(
                    new ItemStack(Items.sugar, 8, 0),
                    new Object[] { "cropSpiceleaf", 1 },
                    new Object[] { "cropGinger", 1 },
                    new ItemStack(Items.dye, 1, 2),
                    Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 4000))
                .fluidOutputs(new FluidStack(CropsNHFluids.njagi, 5000))
                .duration(10 * TICKS)
                .eut(8)
                .addTo(mixerRecipes);
        }

        luvRecipe(0, 1)
            .itemInputs(
                ItemList.Crop_Drop_Chilly.get(1),
                Materials.CosmicNeutronium.getDustTiny(1),
                ItemList.Crop_Drop_Lemon.get(64),
                CropsNHItemList.teaLeaf.get(64),
                CropsNHItemList.magicEssence.get(8),
                CropsNHItemList.spaceFlower.get(9))
            .fluidInputs(new FluidStack(CropsNHFluids.njagi, 50000))
            .fluidOutputs(new FluidStack(CropsNHFluids.jagi, 250))
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(Materials.Water.getCells(1))
            .itemOutputs(Materials.Empty.getCells(1))
            .fluidInputs(Materials.Ethanol.getFluid(1000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 2500))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(Materials.Water.getCells(1), new ItemStack(Items.sugar))
            .itemOutputs(Materials.Empty.getCells(1))
            .fluidInputs(new FluidStack(CropsNHFluids.SWhine, 5000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 6000))
            .duration(10 * TICKS)
            .eut(8)
            .addTo(mixerRecipes);

        // Brewery
        for (ItemStack itemStack : OreDictionary.getOres("listAllberry")) {
            GTValues.RA.stdBuilder()
                .itemInputs(itemStack.splitStack(16))
                .fluidInputs(Materials.Water.getFluid(750))
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

        if (BiomesOPlenty.isModLoaded()) {
            // bop berry
            addDyeConversionRecipe(getModItem(Mods.BiomesOPlenty.ID, "food", 16, 0), dyered);
        }

        // huckleberry
        addDyeConversionRecipe(CropsNHItemList.huckleBerry.get(16), dyepurple);

        if (Natura.isModLoaded()) {
            // blight berry
            addDyeConversionRecipe(getModItem(Natura.ID, "berry.nether", 16, 0), dyelime);

            // dusk berry
            addDyeConversionRecipe(getModItem(Natura.ID, "berry.nether", 16, 1), dyelightgray);

            // sky berry
            addDyeConversionRecipe(getModItem(Natura.ID, "berry.nether", 16, 2), dyelightblue);

            // sting berry
            addDyeConversionRecipe(getModItem(Natura.ID, "berry.nether", 16, 3), dyelime);
        }
    }

    // region helpers

    private static void addDyeConversionRecipe(ItemStack input, Fluid dyeFluid) {
        mvRecipe(12, 0).itemInputs(input, Materials.Salt.getDust(2))
            .fluidInputs(Materials.SulfuricAcid.getFluid(432))
            .fluidOutputs(new FluidStack(dyeFluid, 288))
            .addTo(UniversalChemical);
    }

    // endregion helpers
}
