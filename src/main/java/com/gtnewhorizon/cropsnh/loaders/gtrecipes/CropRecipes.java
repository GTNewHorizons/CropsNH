package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import static bartworks.API.recipe.BartWorksRecipeMaps.bacterialVatRecipes;
import static gregtech.api.recipe.RecipeMaps.*;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;
import static gregtech.api.util.GTRecipeConstants.GLASS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;
import static gregtech.common.items.ItemComb.Voltage;
import static net.minecraftforge.fluids.FluidRegistry.getFluidStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.IMaterialLeafVariant;
import com.gtnewhorizon.cropsnh.handler.CropsNHFurnaceFuelHandler;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import bartworks.common.loaders.BioCultureLoader;
import bartworks.common.loaders.BioItemList;
import bartworks.system.material.Werkstoff;
import bartworks.system.material.WerkstoffLoader;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTRecipeConstants;
import gregtech.api.util.GTUtility;

public abstract class CropRecipes extends BaseGTRecipeLoader {

    private static final int PURIFIED_RECIPE_CIRCUIT = 1;
    private static final int IMPURE_DUST_RECIPE_CIRCUIT = 2;
    public static final int DEFAULT_ORE_DUPLICATION_ORE_AMOUNT = 4;
    public static final int DEFAULT_ORE_CONVERSION_LEAF_AMOUNT = 4;

    public enum TierAcid {

        regWater("water"),
        distilWater("distilled water"),
        /** Sulfuric Acid **/
        t1("Sulfuric Acid"),
        /** Hydrochloric Acid **/
        t2("Hydrochloric Acid"),
        /** Formic Acid **/
        t3("Formic Acid"),
        /** Hydrofluoric Acid **/
        t4("Hydrofluoric Acid"),
        /** Nitric Acid **/
        t5("Nitric Acid"),
        /** Phthalic Acid **/
        t6("Phthalic Acid");

        /**
         * @param unused name exists to make IDEA give propper doc
         */
        TierAcid(String unused) {}

        private FluidStack fluid = null;
        private NBTTagCompound tag = null;

        public void set(FluidStack fluid) {
            if (fluid == null || fluid.getFluid() == null) {
                throw new IllegalArgumentException("fluid stack is null or fluid is null.");
            }
            if (this.fluid != null) {
                throw new IllegalStateException("Tired acid fluid is already set.");
            }
            this.fluid = fluid;
        }

        public FluidStack get() {
            if (this.fluid == null) {
                throw new IllegalStateException("Fluid hasn't been set yet");
            }
            return this.fluid.copy();
        }

        public FluidStack get(int amount) {
            FluidStack out = this.get();
            out.amount = amount;
            return out;
        }
    }

    public static void postInit() {
        TierAcid.distilWater.set(GTModHandler.getDistilledWater(250L));
        TierAcid.regWater.set(new FluidStack(FluidRegistry.WATER, 1000));
        // load tier acid enum
        TierAcid.t1.set(Materials.SulfuricAcid.getFluid(384));
        TierAcid.t2.set(Materials.HydrochloricAcid.getFluid(384 * 2));
        TierAcid.t3.set(WerkstoffLoader.FormicAcid.getFluidOrGas(384 * 3));
        TierAcid.t4.set(Materials.HydrofluoricAcid.getFluid(384 * 4));
        TierAcid.t5.set(Materials.NitricAcid.getFluid(384 * 5));
        // amount is same as tier 5 on purpose
        TierAcid.t6.set(Materials.PhthalicAcid.getFluid(384 * 5));
        // load them recipes
        addOreConversionRecipes();
        addOreMultiplicationRecipes();
        addPlantBallRecipes();
        addCopponRecipes();
        addCanolaRecipes();
        addMagicEssenceRecipes();
        addMicadiaRecipes();
        addMilkWartRecipes();
        addOilBerryRecipes();
        addTineRecipes();
        addUUABerryRecipes();
        addUUMBerryRecipes();
        addSaltyRootRecipes();
        addGlowingCoralRecipes();
        addNetherBerryBrewingRecipes();
        addSpaceFlowerRecipes();
        addSugarBeetRecipes();
        addGoldfishRecipes();
        addHempStemRecipes();
        addGaiaWartRecipes();
        addHopsRecipes();
        addCoffeeRecipes();
        addRubyneRecipes();
    }

    private static void addCoffeeRecipes() {
        GTModHandler.addShapelessCraftingRecipe(
            GTOreDictUnificator.get(OrePrefixes.dust, Materials.Coffee, 1L),
            new Object[] { "craftingToolMortar", "cropCoffee" });
    }

    private static void addHopsRecipes() {
        recipe(4, 6, 40).itemInputs(CropsNHItemList.hops.get(1L))
            .fluidInputs(getFluidStack("potion.wheatyjuice", 750))
            .fluidOutputs(getFluidStack("potion.wheatyhopsjuice", 750))
            .addTo(brewingRecipes);

        for (TierAcid water : new TierAcid[] { TierAcid.regWater, TierAcid.distilWater }) {
            recipe(4, 6, 40).itemInputs(CropsNHItemList.hops.get(1L))
                .fluidInputs(water.get(750))
                .fluidOutputs(getFluidStack("potion.hopsjuice", 750))
                .addTo(brewingRecipes);

            lvRecipe(30, 0)
                .itemInputs(
                    new ItemStack(Items.sugar, 4),
                    CropsNHItemList.hops.get(16L),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Wheat, 8L))
                .special(BioItemList.getPetriDish(BioCultureLoader.BeerYeast))
                .fluidInputs(water.get(100))
                .fluidOutputs(FluidRegistry.getFluidStack("potion.beer", 5))
                .metadata(GLASS, 3)
                .addTo(bacterialVatRecipes);

            GTValues.RA.stdBuilder()
                .itemInputs(
                    CropsNHItemList.hops.get(32L),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Wheat, 16L))
                .special(BioItemList.getPetriDish(BioCultureLoader.BeerYeast))
                .fluidInputs(water.get(100))
                .fluidOutputs(FluidRegistry.getFluidStack("potion.darkbeer", 10))
                .metadata(GLASS, 3)
                .duration(30 * SECONDS)
                .eut(TierEU.RECIPE_LV)
                .addTo(bacterialVatRecipes);
        }

    }

    private static void addGaiaWartRecipes() {
        recipe(5, 14, 40).itemInputs(CropsNHItemList.gaiaWart.get(1))
            .circuit(1)
            .fluidOutputs(Materials.Methane.getGas(36))
            .addTo(centrifugeRecipes);
    }

    private static void addPlantBallRecipes() {
        GTRecipeBuilder baseMossRecipe = lvRecipe(3, 25);
        for (IMaterialLeafVariant variant : ItemMaterialLeaf.getRegisteredVariants()) {
            ItemStack input = variant.get()
                .copy();
            input.stackSize = 4;
            // TODO: REPLACE PLANT BALL WITH WHAT EVER ENDS UP REPLACING IT DOWN THE LINE
            lvRecipe(3, 25).itemInputs(input)
                .itemOutputs(ItemList.IC2_PlantballCompressed.get(1L))
                .addTo(compressorRecipes);
        }

        if (ModUtils.BiomesOPlenty.isModLoaded()) {
            lvRecipe(3, 25).itemInputs(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "treeMoss", 4, 0))
                .itemOutputs(ItemList.IC2_Plantball.get(1L))
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(compressorRecipes);
        }
    }

    private static void addOreMultiplicationRecipes() {
        createOreDuplicationRecipe(MaterialLeafLoader.bauxiaLeaf, Materials.Aluminium);
        createOreDuplicationRecipe(MaterialLeafLoader.bauxiaLeaf, Materials.Bauxite);

        createOreDuplicationRecipe(MaterialLeafLoader.copponFiber, Materials.Copper);
        createOreDuplicationRecipe(MaterialLeafLoader.copponFiber, Materials.Tetrahedrite);
        createOreDuplicationRecipe(MaterialLeafLoader.copponFiber, Materials.Chalcopyrite);
        createOreDuplicationRecipe(MaterialLeafLoader.copponFiber, Materials.Malachite);
        createOreDuplicationRecipe(MaterialLeafLoader.copponFiber, Materials.Pyrite);
        createOreDuplicationRecipe(MaterialLeafLoader.copponFiber, Materials.Stibnite);

        createOreDuplicationRecipe(MaterialLeafLoader.tineTwig, Materials.Tin);
        createOreDuplicationRecipe(MaterialLeafLoader.tineTwig, Materials.Cassiterite);
        createOreDuplicationRecipe(MaterialLeafLoader.tineTwig, Materials.CassiteriteSand);

        createOreDuplicationRecipe(MaterialLeafLoader.plumbiliaLeaf, Materials.Lead);
        createOreDuplicationRecipe(MaterialLeafLoader.plumbiliaLeaf, Materials.Galena);

        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.Iron);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.Magnetite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.BrownLimonite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.YellowLimonite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.VanadiumMagnetite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.BandedIron);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.Pyrite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferrofernLeaf, Materials.MeteoricIron);

        createOreDuplicationRecipe(MaterialLeafLoader.nickelbackLeaf, Materials.Nickel);
        createOreDuplicationRecipe(MaterialLeafLoader.nickelbackLeaf, Materials.Garnierite);
        createOreDuplicationRecipe(MaterialLeafLoader.nickelbackLeaf, Materials.Pentlandite);
        createOreDuplicationRecipe(MaterialLeafLoader.nickelbackLeaf, Materials.Cobaltite);
        createOreDuplicationRecipe(MaterialLeafLoader.nickelbackLeaf, Materials.Wulfenite);
        createOreDuplicationRecipe(MaterialLeafLoader.nickelbackLeaf, Materials.Powellite);

        createOreDuplicationRecipe(MaterialLeafLoader.galvaniaLeaf, Materials.Zinc);
        createOreDuplicationRecipe(MaterialLeafLoader.galvaniaLeaf, Materials.Sphalerite);
        createOreDuplicationRecipe(MaterialLeafLoader.galvaniaLeaf, Materials.Sulfur);

        createOreDuplicationRecipe(MaterialLeafLoader.argentiaLeaf, Materials.Silver);
        createOreDuplicationRecipe(MaterialLeafLoader.argentiaLeaf, Materials.Galena);

        createOreDuplicationRecipe(MaterialLeafLoader.auroniaLeaf, Materials.Gold);
        createOreDuplicationRecipe(MaterialLeafLoader.auroniaLeaf, Materials.Magnetite);

        createOreDuplicationRecipe(MaterialLeafLoader.micadiaFlower, Materials.Mica);

        createOreDuplicationRecipe(MaterialLeafLoader.pyrolusiumLeaf, Materials.Manganese);
        createOreDuplicationRecipe(MaterialLeafLoader.pyrolusiumLeaf, Materials.Grossular);
        createOreDuplicationRecipe(MaterialLeafLoader.pyrolusiumLeaf, Materials.Spessartine);
        createOreDuplicationRecipe(MaterialLeafLoader.pyrolusiumLeaf, Materials.Pyrolusite);
        createOreDuplicationRecipe(MaterialLeafLoader.pyrolusiumLeaf, Materials.Tantalite);

        createOreDuplicationRecipe(MaterialLeafLoader.titaniaLeaf, Materials.Titanium);
        createOreDuplicationRecipe(MaterialLeafLoader.titaniaLeaf, Materials.Ilmenite);
        createOreDuplicationRecipe(MaterialLeafLoader.titaniaLeaf, Materials.Bauxite);
        createOreDuplicationRecipe(MaterialLeafLoader.titaniaLeaf, Materials.Rutile);

        createOreDuplicationRecipe(MaterialLeafLoader.scheeliniumLeaf, Materials.Scheelite);
        createOreDuplicationRecipe(MaterialLeafLoader.scheeliniumLeaf, Materials.Tungstate);
        createOreDuplicationRecipe(MaterialLeafLoader.scheeliniumLeaf, Materials.Lithium);
        createOreDuplicationRecipe(MaterialLeafLoader.scheeliniumLeaf, Materials.Tungsten);

        createOreDuplicationRecipe(MaterialLeafLoader.platinaLeaf, Materials.Platinum);
        createOreDuplicationRecipe(MaterialLeafLoader.platinaLeaf, Materials.Cooperite);
        createOreDuplicationRecipe(MaterialLeafLoader.platinaLeaf, Materials.Palladium);
        createOreDuplicationRecipe(MaterialLeafLoader.platinaLeaf, Materials.Neodymium);
        createOreDuplicationRecipe(MaterialLeafLoader.platinaLeaf, Materials.Bastnasite);

        createOreDuplicationRecipe(MaterialLeafLoader.iridineFlower, Materials.Iridium);

        createOreDuplicationRecipe(MaterialLeafLoader.osmianthFlower, Materials.Osmium);

        createOreDuplicationRecipe(MaterialLeafLoader.reactoriaLeaf, Materials.Pitchblende);

        createOreDuplicationRecipe(MaterialLeafLoader.reactoriaStem, Materials.Uraninite);
        createOreDuplicationRecipe(MaterialLeafLoader.reactoriaStem, Materials.Uranium);
        createOreDuplicationRecipe(MaterialLeafLoader.reactoriaStem, Materials.Pitchblende);
        createOreDuplicationRecipe(MaterialLeafLoader.reactoriaStem, Materials.Uranium235);

        createOreDuplicationRecipe(MaterialLeafLoader.thunderFlower, Materials.Thorium);
        createOreDuplicationRecipe(MaterialLeafLoader.thunderFlower, WerkstoffLoader.Thorianit);

        createOreDuplicationRecipe(MaterialLeafLoader.stargatiumLeaf, Materials.Naquadah);
        createOreDuplicationRecipe(MaterialLeafLoader.stargatiumLeaf, Materials.NaquadahEnriched);
        createOreDuplicationRecipe(MaterialLeafLoader.stargatiumLeaf, Materials.Naquadria);

        createOreDuplicationRecipe(MaterialLeafLoader.bobsYerUncleBerry, Materials.Emerald);
        createOreDuplicationRecipe(MaterialLeafLoader.bobsYerUncleBerry, Materials.Beryllium);

        createOreDuplicationRecipe(MaterialLeafLoader.rubyneLeaf, Materials.Ruby);

        if (ModUtils.GalacticraftCore.isModLoaded()) {
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.MeteoricIron, Voltage.HV);
            createOreDuplicationRecipe(
                MaterialLeafLoader.spaceFlower.get(9),
                CropsNHUtils.getModItem(ModUtils.GalacticraftCore, "item.meteoricIronRaw", 1, 0),
                Materials.MeteoricIron,
                Voltage.HV);
        }

        if (ModUtils.GalacticraftMars.isModLoaded()) {
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.Desh, Voltage.HV);
            createOreDuplicationRecipe(
                MaterialLeafLoader.spaceFlower.get(9),
                CropsNHUtils.getModItem(ModUtils.GalacticraftMars, "item.null", 1, 0),
                Materials.Desh,
                Voltage.HV);
        }

        if (ModUtils.GalaxySpace.isModLoaded()) {
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.Oriharukon, Voltage.HV);
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.Ledox, Voltage.HV);
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.CallistoIce, Voltage.HV);
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.BlackPlutonium, Voltage.LuV);
            createOreDuplicationRecipe(MaterialLeafLoader.spaceFlower.get(9), Materials.DeepIron, Voltage.LuV);
        }

    }

    private static void addOreConversionRecipes() {

        mvRecipe(Voltage.MV.getSimpleTime() * 3).itemInputs(MaterialLeafLoader.micadiaFlower.get(1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Mica, 1))
            .addTo(extractorRecipes);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.copponFiber.get(9))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Copper, 1) },
                new int[] { 50_00 })
            .addTo(extractorRecipes);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.tineTwig.get(9))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Tin, 1) },
                new int[] { 50_00 })
            .addTo(extractorRecipes);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.plumbiliaLeaf.get(9))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Lead, 1) },
                new int[] { 50_00 })
            .addTo(extractorRecipes);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.argentiaLeaf.get(9))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Silver, 1) },
                new int[] { 50_00 })
            .addTo(extractorRecipes);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.ferrofernLeaf.get(9))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1) },
                new int[] { 50_00 })
            .addTo(extractorRecipes);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.rubyneLeaf.get(9))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ruby, 1) },
                new int[] { 50_00 })
            .addTo(extractorRecipes);

        createOreConversionRecipe(MaterialLeafLoader.copponFiber, Voltage.LV, Materials.Copper, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.galvaniaLeaf, Voltage.LV, Materials.Zinc, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.nickelbackLeaf, Voltage.LV, Materials.Nickel, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.pyrolusiumLeaf, Voltage.LV, Materials.Manganese, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.plumbiliaLeaf, Voltage.LV, Materials.Lead, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.tineTwig, Voltage.LV, Materials.Tin, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.argentiaLeaf, Voltage.LV, Materials.Silver, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.ferrofernLeaf, Voltage.LV, Materials.Iron, TierAcid.t1);

        createOreConversionRecipe(MaterialLeafLoader.auroniaLeaf, Voltage.LV, Materials.Gold, TierAcid.t2);
        // emeralds are needed for mv sensors/emitters so slightly higher reqs
        createOreConversionRecipe(MaterialLeafLoader.bobsYerUncleBerry, Voltage.LV, Materials.Emerald, TierAcid.t2);
        createOreConversionRecipe(MaterialLeafLoader.bauxiaLeaf, Voltage.MV, Materials.Bauxite, TierAcid.t2);
        // Ruby juice needs hydrochloric acid so keep acids consistent for ruby recipes.
        createOreConversionRecipe(MaterialLeafLoader.rubyneLeaf, Voltage.LV, Materials.Ruby, TierAcid.t2);

        createOreConversionRecipe(
            MaterialLeafLoader.platinaLeaf,
            Voltage.HV,
            WerkstoffLoader.PTConcentrate.getFluidOrGas(1000),
            TierAcid.t3,
            Voltage.HV.getComplexTime() * 10);

        createOreConversionRecipe(MaterialLeafLoader.scheeliniumLeaf, Voltage.EV, Materials.Scheelite, TierAcid.t5);
        createOreConversionRecipe(MaterialLeafLoader.reactoriaLeaf, Voltage.EV, Materials.Pitchblende, TierAcid.t5);
        createOreConversionRecipe(MaterialLeafLoader.reactoriaStem, Voltage.EV, Materials.Uranium, TierAcid.t6);
        createOreConversionRecipe(MaterialLeafLoader.thunderFlower, Voltage.EV, Materials.Thorium, TierAcid.t3);
        createOreConversionRecipe(MaterialLeafLoader.titaniaLeaf, Voltage.EV, Materials.Titanium, TierAcid.t6);
        createOreConversionRecipe(MaterialLeafLoader.starWart, Voltage.EV, Materials.NetherStar, TierAcid.t6);

        createOreConversionRecipe(
            MaterialLeafLoader.osmianthFlower,
            Voltage.IV,
            WerkstoffLoader.AcidicOsmiumSolution.getFluidOrGas(1000),
            TierAcid.t6,
            Voltage.HV.getComplexTime() * 17);
        createOreConversionRecipe(
            MaterialLeafLoader.iridineFlower,
            Voltage.IV,
            WerkstoffLoader.AcidicIridiumSolution.getFluidOrGas(1000),
            TierAcid.t6,
            Voltage.HV.getComplexTime() * 14);
        createOreConversionRecipe(MaterialLeafLoader.stargatiumLeaf, Voltage.IV, Materials.Naquadah, TierAcid.t6);

    }

    private static void addCopponRecipes() {

        recipe(2, 15, 0).itemInputs(MaterialLeafLoader.copponFiber.get(4), GTUtility.getIntegratedCircuit(1))
            .itemOutputs(new ItemStack(Blocks.wool, 1, 1))
            .addTo(assemblerRecipes);
    }

    private static void addCanolaRecipes() {
        // canola oil extraction
        recipe(2, 1, 60).itemInputs(MaterialLeafLoader.canolaFLower.get(1))
            .fluidOutputs(Materials.SeedOil.getFluid(125))
            .addTo(fluidExtractionRecipes);
    }

    private static void addMagicEssenceRecipes() {
        if (!ModUtils.Thaumcraft.isModLoaded()) return;
        Item thaumResourceItem = GameRegistry.findItem(ModUtils.Thaumcraft.ID, "ItemResource");
        // salis mundus extraction
        ulvRecipe(3, 20).itemInputs(MaterialLeafLoader.magicEssence.get(1))
            .itemOutputs(new ItemStack(thaumResourceItem, 16, 14))
            .addTo(RecipeMaps.extractorRecipes);
        // iron to thaumium conversion
        mvRecipe(12, 0).itemInputs(new Object[] { "dustIron", 4 }, CropsNHItemList.magicEssence.get(1))
            .fluidInputs(TierAcid.t1.get(1000))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Thaumium, 4))
            .addTo(GTRecipeConstants.UniversalChemical);
        // thaumium to void conversion
        hvRecipe(12, 0)
            .itemInputs(
                new Object[] { "dustThaumium", 4 },
                CropsNHItemList.magicEssence.get(2),
                new ItemStack(thaumResourceItem, 1, 17))
            .fluidInputs(TierAcid.t2.get(4000))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Void, 4))
            .addTo(multiblockChemicalReactorRecipes);
        if (ModUtils.TaintedMagic.isModLoaded()) {
            // void to shadow metal conversion
            ivRecipe(12, 0).itemInputs(new Object[] { "dustVoid", 4 }, CropsNHItemList.magicEssence.get(4))
                .fluidInputs(TierAcid.t4.get(16_000))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Shadow, 4))
                .addTo(GTRecipeConstants.UniversalChemical);
            if (ModUtils.ThaumicTinkerer.isModLoaded() && ModUtils.BloodMagic.isModLoaded()) {
                // shadow metal to ichorium conversion
                luvRecipe(90, 0)
                    .itemInputs(
                        new Object[] { "dustVoid", 4 },
                        CropsNHItemList.magicEssence.get(8),
                        new Object[] { "ingotOsmiridium", 1 },
                        CropsNHUtils.getModItem(ModUtils.BloodMagic, "bloodMagicBaseItems", 4, 28),
                        CropsNHUtils.getModItem(ModUtils.BloodMagic, "bloodMagicBaseItems", 4, 29))
                    .fluidInputs(TierAcid.t6.get(190_000))
                    .itemOutputs(GTOreDictUnificator.get(OrePrefixes.ingot, Materials.Ichorium, 1))
                    .addTo(multiblockChemicalReactorRecipes);
            }
        }
        if (ModUtils.GalacticraftCore.isModLoaded()) {
            ivRecipe(90, 0).itemInputs(CropsNHItemList.spaceFlower.get(16), CropsNHItemList.magicEssence.get(4))
                .fluidInputs(
                    Materials.Platinum.getMolten(288),
                    Materials.MeteoricIron.getMolten(144),
                    TierAcid.t5.get(64_000))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Mytryl, 1))
                .addTo(multiblockChemicalReactorRecipes);
        }
        // Magic essence from salis
        hvRecipe(7, 50).itemInputs(new ItemStack(thaumResourceItem, 64, 14))
            .fluidInputs(Materials.Void.getMolten(144 * 16))
            .itemOutputs(CropsNHItemList.magicEssence.get(1))
            .addTo(autoclaveRecipes);

        // inert prim perl crafting recipe
        if (ModUtils.NewHorizonsCoreMod.isModLoaded() && ModUtils.WitchingGadgets.isModLoaded()) {
            evRecipe(5 * 60, 0).itemInputs(CropsNHItemList.magicEssence.get(64))
                .fluidInputs(Materials.Ichorium.getMolten(144 * 3))
                .itemOutputs(CropsNHUtils.getModItem(ModUtils.NewHorizonsCoreMod, "PrimordialPearlFragment", 3))
                .addTo(autoclaveRecipes);
        }
    }

    private static void addMicadiaRecipes() {
        CropsNHFurnaceFuelHandler.FUEL_VALUE_MAP.put(CropsNHItemList.micadiaFlower.get(1), 100);
    }

    private static void addMilkWartRecipes() {
        // milk fluid extraction
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.milkWart.get(1))
            .itemOutputs(new ItemStack[] { Materials.Milk.getDust(1) }, new int[] { 10_00 })
            .fluidOutputs(Materials.Milk.getFluid(150))
            .duration(6 * SECONDS + 8 * TICKS)
            .eut(4)
            .addTo(fluidExtractionRecipes);
        // milk powder extraction
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.milkWart.get(1))
            .itemOutputs(Materials.Milk.getDust(1))
            .eut(4)
            .duration(6 * GTRecipeBuilder.SECONDS + 8 * GTRecipeBuilder.TICKS)
            .addTo(RecipeMaps.extractorRecipes);
    }

    private static void addOilBerryRecipes() {
        FluidStack[] fluids = new FluidStack[] { Materials.OilLight.getFluid(100), Materials.Oil.getFluid(100),
            // raw oil
            Materials.OilMedium.getFluid(100), Materials.OilHeavy.getFluid(100) };

        for (int i = 0; i < fluids.length; i++) {
            FluidStack sourceFluid = fluids[i];
            // oil extraction
            recipe(4, 6, 40).itemInputs(MaterialLeafLoader.oilBerry.get(1), GTUtility.getIntegratedCircuit(i + 1))
                .fluidOutputs(sourceFluid.copy())
                .addTo(fluidExtractionRecipes);

            // oil conversion, density up
            if (i > 0) {
                FluidStack targetFluid = fluids[i - 1].copy();
                sourceFluid.amount = 1000;
                targetFluid.amount = 1000;
                lvRecipe(0, 50).itemInputs(MaterialLeafLoader.oilBerry.get(1), GTUtility.getIntegratedCircuit(1))
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);

                // bulk recipe for tps reasons
                sourceFluid.amount = 64_000;
                targetFluid.amount = 64_000;
                lvRecipe(0, 50 * 64).itemInputs(MaterialLeafLoader.oilBerry.get(1), GTUtility.getIntegratedCircuit(3))
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);
            }

            // oil conversion, density down
            if (i < fluids.length - 1) {
                FluidStack targetFluid = fluids[i + 1].copy();
                sourceFluid.amount = 1000;
                targetFluid.amount = 1000;
                lvRecipe(0, 50).itemInputs(MaterialLeafLoader.oilBerry.get(1), GTUtility.getIntegratedCircuit(2))
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);

                // bulk recipe for tps reasons
                sourceFluid.amount = 64_000;
                targetFluid.amount = 64_000;
                lvRecipe(0, 50 * 64).itemInputs(MaterialLeafLoader.oilBerry.get(1), GTUtility.getIntegratedCircuit(4))
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);
            }
        }
    }

    private static void addTineRecipes() {
        CropsNHFurnaceFuelHandler.FUEL_VALUE_MAP.put(CropsNHItemList.tineTwig.get(1), 100);

        ulvRecipe(3, 75).itemInputs(MaterialLeafLoader.tineTwig.get(2))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1))
            .addTo(maceratorRecipes);
    }

    private static void addUUABerryRecipes() {
        recipe(4, 6, 40).itemInputs(MaterialLeafLoader.uuaBerry.get(1))
            .fluidOutputs(Materials.UUAmplifier.getFluid(4))
            .addTo(fluidExtractionRecipes);
    }

    private static void addUUMBerryRecipes() {
        recipe(4, 6, 40).itemInputs(MaterialLeafLoader.uumBerry.get(1))
            .fluidOutputs(Materials.UUMatter.getFluid(4))
            .addTo(fluidExtractionRecipes);
    }

    private static void addSaltyRootRecipes() {
        lvRecipe(5, 0).itemInputs(MaterialLeafLoader.saltyRoot.get(1))
            .fluidInputs(new FluidStack(FluidRegistry.WATER, 100))
            .itemOutputs(
                new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dust, Materials.Salt, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.RockSalt, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 1),
                    GTOreDictUnificator.get(OrePrefixes.dust, Materials.Borax, 1) },
                new int[] { 95_00, 80_00, 50_00, 5_00 })
            .addTo(chemicalBathRecipes);
    }

    private static void addGlowingCoralRecipes() {
        if (!ModUtils.BiomesOPlenty.isModLoaded()) {
            return;
        }
        // glowing coral to sunnarium
        ivRecipe(60 * 5 + 16, 0).itemInputs(CropsNHUtils.getModItem(ModUtils.BiomesOPlenty, "coral1", 32, 15))
            .fluidInputs(Materials.UUMatter.getFluid(1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Sunnarium, 2))
            .requiresCleanRoom()
            .addTo(RecipeMaps.autoclaveRecipes);
    }

    private static void addNetherBerryBrewingRecipes() {
        if (!ModUtils.Natura.isModLoaded()) return;

        ulvRecipe(3, 40).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 0))
            .fluidInputs(Materials.Water.getFluid(750))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.regen"), 750))
            .addTo(brewingRecipes);

        ulvRecipe(3, 40).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 1))
            .fluidInputs(Materials.Water.getFluid(750))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.nightvision"), 750))
            .addTo(brewingRecipes);

        ulvRecipe(3, 40).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 2))
            .fluidInputs(Materials.Water.getFluid(750))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.speed"), 750))
            .addTo(brewingRecipes);

        ulvRecipe(3, 40).itemInputs(CropsNHUtils.getModItem(ModUtils.Natura, "berry.nether", 16, 3))
            .fluidInputs(Materials.Water.getFluid(750))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.strength"), 750))
            .addTo(brewingRecipes);
    }

    private static void addSpaceFlowerRecipes() {
        if (!ModUtils.GalacticraftCore.isModLoaded()) return;
        // space flower to uum
        recipe(4, 6, 40).itemInputs(MaterialLeafLoader.spaceFlower.get(1))
            // it's growth time should make it worst than using uum berries
            .fluidOutputs(Materials.UUMatter.getFluid(4))
            .addTo(fluidExtractionRecipes);

        // iron to meteoric iron
        hvRecipe(12, 0).itemInputs(new Object[] { "dustIron", 4 }, MaterialLeafLoader.spaceFlower.get(1))
            .fluidInputs(TierAcid.t2.get())
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.MeteoricIron, 4))
            .addTo(UniversalChemical);

        // steel to meteoric steel
        hvRecipe(12, 0).itemInputs(new Object[] { "dustSteel", 4 }, MaterialLeafLoader.spaceFlower.get(1))
            .fluidInputs(TierAcid.t2.get())
            .itemOutputs(Materials.MeteoricSteel.getDust(4))
            .addTo(UniversalChemical);

        if (ModUtils.GalacticraftMars.isModLoaded()) {
            // titanium to desh
            hvRecipe(12, 0).itemInputs(new Object[] { "dustTitanium", 4 }, MaterialLeafLoader.spaceFlower.get(4))
                .fluidInputs(TierAcid.t4.get())
                .itemOutputs(Materials.Oriharukon.getDust(4))
                .addTo(UniversalChemical);
        }

        if (ModUtils.GalaxySpace.isModLoaded()) {
            // bismuth to oriharukon
            hvRecipe(12, 0).itemInputs(new Object[] { "dustBismuth", 4 }, MaterialLeafLoader.spaceFlower.get(8))
                .fluidInputs(TierAcid.t4.get())
                .itemOutputs(Materials.Oriharukon.getDust(4))
                .addTo(UniversalChemical);

            // ice to callistoIce
            ivRecipe(12, 0).itemInputs(new Object[] { "dustIce", 4 }, MaterialLeafLoader.spaceFlower.get(8))
                .fluidInputs(TierAcid.t5.get())
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.CallistoIce, 4))
                .addTo(UniversalChemical);

            // lead to ledox
            ivRecipe(12, 0).itemInputs(new Object[] { "dustLead", 4 }, MaterialLeafLoader.spaceFlower.get(8))
                .fluidInputs(TierAcid.t5.get())
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Ledox, 4))
                .addTo(UniversalChemical);

            if (ModUtils.Avaritia.isModLoaded()) {
                // crustal matrix ingot to mysterious crystal.
                zpmRecipe(12, 0)
                    .itemInputs(
                        CropsNHUtils.getModItem(ModUtils.Avaritia, "Resource", 1, 1),
                        MaterialLeafLoader.spaceFlower.get(16))
                    .fluidInputs(TierAcid.t6.get())
                    .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.MysteriousCrystal, 4))
                    .addTo(UniversalChemical);
            }

            // meteoric iron to deep iron
            zpmRecipe(6, 0).itemInputs(new Object[] { "dustMeteoricIron", 4 }, MaterialLeafLoader.spaceFlower.get(16))
                .fluidInputs(TierAcid.t6.get())
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.DeepIron, 4))
                .addTo(UniversalChemical);

            // pu241 to black plutonium
            zpmRecipe(6, 0).itemInputs(new Object[] { "dustPlutonium241", 4 }, MaterialLeafLoader.spaceFlower.get(64))
                .fluidInputs(TierAcid.t6.get())
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.BlackPlutonium, 4))
                .addTo(UniversalChemical);
        }
    }

    private static void addSugarBeetRecipes() {
        ulvRecipe(3, 40).itemInputs(CropsNHItemList.sugarBeet.get(1))
            .itemOutputs(new ItemStack(Items.sugar, 8, 0))
            .addTo(extractorRecipes);
    }

    private static void addGoldfishRecipes() {
        // While some may fear it its unending cries, few know their cause. Most believed it's auric namesake brought it
        // unending joy. Oh, wrong were they. Its oiled body, once revered by the masses and believed to house untold
        // amounts of the finest metal, hid a curse that would bring unending pain. It spends its life ceaselessly
        // praying for help. Its plight remains unanswered. Death remains its only salvation.

        // fluid extraction
        ulvRecipe(0, 80).itemInputs(CropsNHItemList.goldfish.get(1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.nugget, Materials.Mercury, 1))
            .outputChances(1_00)
            .fluidOutputs(Materials.FishOil.getFluid(100))
            .addTo(fluidExtractionRecipes);

        // maceration
        ulvRecipe(0, 15).itemInputs(CropsNHItemList.goldfish.get(1))
            .itemOutputs(
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.MeatRaw, 1),
                GTOreDictUnificator.get(OrePrefixes.nugget, Materials.Mercury, 1))
            .outputChances(100_00, 1_00)
            .addTo(maceratorRecipes);
    }

    private static void addHempStemRecipes() {
        // Retting the stems in water to detach it from the fibers.
        ulvRecipe(1, 0).itemInputs(CropsNHItemList.hempStem.get(1))
            .itemOutputs(new ItemStack(Items.string, 6, 0))
            .fluidInputs(new FluidStack(FluidRegistry.WATER, 100))
            .addTo(chemicalBathRecipes);

        // macerate the sticks for plant balls (mangled fibers) and hurds for the casings
        ulvRecipe(1, 0).itemInputs(CropsNHItemList.hempStem.get(1))
            .itemOutputs(CropsNHItemList.hempHurd.get(1), ItemList.IC2_PlantballCompressed.get(1L))
            .outputChances(100_00, 25_00)
            .addTo(maceratorRecipes);

        // IF casing recipe
        mvRecipe(5, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.frameGt, Materials.Aluminium, 1),
                CropsNHItemList.hempHurd.get(4),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Quicklime, 1))
            .fluidInputs(new FluidStack(FluidRegistry.WATER, 1000))
            .itemOutputs(CropsNHItemList.BrickedAgriculturalCasing.get(1))
            .addTo(assemblerRecipes);
    }

    private static void addRubyneRecipes() {
        // Crushed ruby
        mvRecipe(3, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.crushed, Materials.Ruby, 1),
                GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(1))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(1000))
            .fluidOutputs(Materials.RubyJuice.getFluid(4000))
            .addTo(mixerRecipes);

        // Crushed Purified
        mvRecipe(3, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.Ruby, 1),
                GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(1))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(1000))
            .fluidOutputs(Materials.RubyJuice.getFluid(4000))
            .addTo(mixerRecipes);

        // Impure Dust
        mvRecipe(3, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.dustImpure, Materials.Ruby, 1),
                GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(1))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(1000))
            .fluidOutputs(Materials.RubyJuice.getFluid(4000))
            .addTo(mixerRecipes);

        // Purified Dust
        mvRecipe(3, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.dustPure, Materials.Ruby, 1),
                GTOreDictUnificator.get(OrePrefixes.dustTiny, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(1))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(1000))
            .fluidOutputs(Materials.RubyJuice.getFluid(4000))
            .addTo(mixerRecipes);

        // HV bulk versions
        hvRecipe(4, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.crushed, Materials.Ruby, 9),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(9))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(9000))
            .fluidOutputs(Materials.RubyJuice.getFluid(36000))
            .addTo(mixerRecipes);

        hvRecipe(4, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.Ruby, 9),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(9))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(9000))
            .fluidOutputs(Materials.RubyJuice.getFluid(36000))
            .addTo(mixerRecipes);

        hvRecipe(4, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.dustImpure, Materials.Ruby, 9),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(9))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(9000))
            .fluidOutputs(Materials.RubyJuice.getFluid(36000))
            .addTo(mixerRecipes);

        hvRecipe(4, 0)
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.dustPure, Materials.Ruby, 9),
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.SodiumHydroxide, 1),
                CropsNHItemList.rubyneLeaf.get(9))
            .fluidInputs(Materials.HydrochloricAcid.getFluid(9000))
            .fluidOutputs(Materials.RubyJuice.getFluid(36000))
            .addTo(mixerRecipes);
    }

    // region ore conversion helpers

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, Werkstoff ore,
        TierAcid catalyst) {
        if (ore == null) {
            throw new IllegalArgumentException("ore cannot be null.");
        }
        createOreConversionRecipe(variant, voltage, ore.getBridgeMaterial(), catalyst);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, FluidStack outputFluid,
        TierAcid catalyst) {
        createOreConversionRecipe(variant, voltage, outputFluid, catalyst, -1);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, FluidStack outputFluid,
        TierAcid catalyst, int duration) {
        createOreConversionRecipe(
            variant,
            DEFAULT_ORE_CONVERSION_LEAF_AMOUNT,
            voltage,
            outputFluid,
            catalyst,
            duration);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int leafAmount, Voltage voltage,
        FluidStack outputFluid, TierAcid catalyst, int duration) {
        if (variant == null || voltage == null || outputFluid == null) {
            throw new IllegalArgumentException("variant, voltage and outputFluid cannot be null.");
        }
        if (leafAmount <= 0) {
            throw new IllegalArgumentException("leafAmount must be greater than zero.");
        }

        ItemStack[] leaf = new ItemStack[] { variant.get(leafAmount) };
        FluidStack[] acid = catalyst == null ? null : new FluidStack[] { catalyst.get(voltage.getFluidAmount()) };

        createOreConversionRecipe(voltage, duration, leaf, acid, null, null, new FluidStack[] { outputFluid });
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, Materials ore,
        TierAcid catalyst) {
        createOreConversionRecipe(
            variant,
            DEFAULT_ORE_CONVERSION_LEAF_AMOUNT,
            voltage,
            ore,
            DEFAULT_ORE_CONVERSION_LEAF_AMOUNT,
            catalyst,
            -1);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int amount, Voltage voltage,
        Materials ore, TierAcid catalyst, int duration) {
        createOreConversionRecipe(variant, amount, voltage, ore, amount, catalyst, duration);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int leafAmount, Voltage voltage,
        Materials ore, int oreAmount, TierAcid catalyst, int duration) {
        if (variant == null || voltage == null || ore == null) {
            throw new IllegalArgumentException("variant, voltage and outputFluid cannot be null.");
        }
        if (leafAmount <= 0) {
            throw new IllegalArgumentException("leafAmount must be greater than zero.");
        }
        if (oreAmount <= 0) {
            throw new IllegalArgumentException("oreAmount must be greater than zero.");
        }

        ItemStack leaf = variant.get(leafAmount);
        ItemStack[] purified = new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.crushedPurified, ore, oreAmount) };
        ItemStack[] impureDust = new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.dustImpure, ore, oreAmount) };
        FluidStack[] acid = catalyst == null ? null : new FluidStack[] { catalyst.get(voltage.getFluidAmount()) };

        createOreConversionRecipe(
            voltage,
            duration,
            new ItemStack[] { leaf, GTUtility.getIntegratedCircuit(PURIFIED_RECIPE_CIRCUIT) },
            acid,
            purified,
            null,
            null);
        createOreConversionRecipe(
            voltage,
            duration,
            new ItemStack[] { leaf, GTUtility.getIntegratedCircuit(IMPURE_DUST_RECIPE_CIRCUIT) },
            acid,
            impureDust,
            null,
            null);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, ItemStack ore,
        TierAcid catalyst) {
        createOreConversionRecipe(variant, DEFAULT_ORE_CONVERSION_LEAF_AMOUNT, voltage, ore, catalyst, -1);
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int leafAmount, Voltage voltage,
        ItemStack ore, TierAcid catalyst, int duration) {
        if (variant == null || voltage == null || ore == null) {
            throw new IllegalArgumentException("variant, voltage and outputFluid cannot be null.");
        }
        if (leafAmount <= 0) {
            throw new IllegalArgumentException("leafAmount must be greater than zero.");
        }

        ItemStack[] leaf = new ItemStack[] { variant.get(leafAmount) };
        ItemStack[] purified = new ItemStack[] { ore };
        FluidStack[] acid = catalyst == null ? null : new FluidStack[] { catalyst.get(voltage.getFluidAmount()) };

        createOreConversionRecipe(voltage, duration, leaf, acid, purified, null, null);
    }

    public static void createOreConversionRecipe(Voltage voltage, int duration, ItemStack[] itemInputs,
        FluidStack[] fluidInputs, ItemStack[] itemOutputs, int[] chances, FluidStack[] fluidOutputs) {
        if (voltage == null || itemInputs == null || itemInputs.length <= 0) {
            throw new IllegalArgumentException("variant, voltage itemInputs cannot be null.");
        }
        if (duration <= 0) {
            duration = voltage.getComplexTime();
        }
        if (fluidInputs != null && fluidInputs.length <= 0) {
            fluidInputs = null;
        }
        if (fluidOutputs != null && fluidOutputs.length <= 0) {
            fluidOutputs = null;
        }
        if (itemOutputs != null) {
            if (itemOutputs.length <= 0) {
                chances = null;
                itemOutputs = null;
            } else if (chances != null) {
                if (chances.length <= 0) {
                    chances = null;
                } else if (chances.length != itemOutputs.length) {
                    throw new IllegalArgumentException(
                        "output chance is doesn't have the right number of entries (" + itemOutputs.length + ")");
                }
            }
        }
        if (itemOutputs == null && fluidOutputs == null) {
            throw new IllegalArgumentException("no output");
        }

        GTRecipeBuilder builder = GTValues.RA.stdBuilder()
            .eut(voltage.getChemicalEnergy())
            .duration(voltage.getComplexTime())
            .itemInputs(itemInputs);

        if (fluidInputs != null) {
            builder.fluidInputs(fluidInputs);
        }
        if (itemOutputs != null) {
            builder.itemOutputs(itemOutputs);
            if (chances != null) {
                builder.outputChances(chances);
            }
        }
        if (fluidOutputs != null) {
            builder.fluidOutputs(fluidOutputs);
        }

        if (fluidInputs == null && itemOutputs != null && itemInputs.length == 1 && itemOutputs.length == 1) {
            builder.addTo(extractorRecipes);
        } else if (fluidInputs == null && fluidOutputs != null && itemInputs.length == 1 && fluidOutputs.length == 1) {
            builder.addTo(fluidExtractionRecipes);
        } else {
            builder.addTo(hammerRecipes);
        }

    }

    // endregion ore conversion helpers

    // region ore duplication helpers

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, Werkstoff oreType) {
        if (oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        createOreDuplicationRecipe(variant, oreType.getBridgeMaterial());
    }

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, Werkstoff oreType, Voltage voltage) {
        if (oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        createOreDuplicationRecipe(variant, oreType.getBridgeMaterial(), voltage);
    }

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, Materials oreType) {
        createOreDuplicationRecipe(variant, oreType, Voltage.LV);
    }

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, Materials oreType, Voltage voltage) {
        if (variant == null || oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        ItemStack leaf = variant.get(1);
        createOreDuplicationRecipe(leaf, oreType, voltage);
    }

    public static void createOreDuplicationRecipe(ItemStack leaf, Materials oreType, Voltage voltage) {
        if (leaf == null || oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        ItemStack crushed = GTOreDictUnificator.get(OrePrefixes.crushed, oreType, 1);
        createOreDuplicationRecipe(leaf, crushed, oreType, voltage);
    }

    public static void createOreDuplicationRecipe(ItemStack leaf, ItemStack crushed, Materials oreType,
        Voltage voltage) {
        if (leaf == null || crushed == null || oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        ItemStack purified = GTOreDictUnificator
            .get(OrePrefixes.crushedPurified, oreType, DEFAULT_ORE_DUPLICATION_ORE_AMOUNT);
        ItemStack impureDust = GTOreDictUnificator
            .get(OrePrefixes.dustImpure, oreType, DEFAULT_ORE_DUPLICATION_ORE_AMOUNT);
        FluidStack byproduct = null;
        if (!oreType.mOreByProducts.isEmpty()) {
            byproduct = oreType.mOreByProducts.get(0)
                .getMolten(144);
        }
        // TODO: ask around about the circuit thing since technically with the ghost slot this is feasible.
        createOreDuplicationRecipe(
            new ItemStack[] { leaf, crushed },
            purified,
            byproduct,
            voltage,
            chemicalReactorRecipes);
        createOreDuplicationRecipe(
            new ItemStack[] { leaf, crushed, GTUtility.getIntegratedCircuit(PURIFIED_RECIPE_CIRCUIT) },
            purified,
            byproduct,
            voltage,
            multiblockChemicalReactorRecipes);
        createOreDuplicationRecipe(
            new ItemStack[] { leaf, crushed, GTUtility.getIntegratedCircuit(IMPURE_DUST_RECIPE_CIRCUIT) },
            impureDust,
            byproduct,
            voltage,
            multiblockChemicalReactorRecipes);
    }

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, ItemStack crushed, Materials oreType,
        Voltage voltage) {
        if (variant == null || crushed == null || oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        ItemStack leaf = variant.get(1);
        ItemStack purified = GTOreDictUnificator
            .get(OrePrefixes.crushedPurified, oreType, DEFAULT_ORE_DUPLICATION_ORE_AMOUNT);
        FluidStack byproduct = null;
        if (!oreType.mOreByProducts.isEmpty()) {
            byproduct = oreType.mOreByProducts.get(0)
                .getMolten(144);
        }

        createOreDuplicationRecipe(new ItemStack[] { leaf, crushed }, purified, byproduct, voltage, UniversalChemical);
    }

    public static void createOreDuplicationRecipe(ItemStack[] inputs, ItemStack purified, FluidStack byproduct,
        Voltage voltage, IRecipeMap recipeMap) {
        if (inputs == null || purified == null) {
            throw new IllegalArgumentException("inputs and purified can't be null");
        }
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == null) {
                throw new IllegalArgumentException("input #" + i + " is null!");
            }
        }

        // create recipe base
        GTRecipeBuilder builder = GTValues.RA.stdBuilder()
            .eut(voltage.getChemicalEnergy())
            .duration(voltage.getComplexTime())
            .itemInputs(inputs)
            .itemOutputs(purified);

        // add byproduct if one is available
        if (byproduct != null) {
            builder.fluidOutputs(byproduct);
        }

        // reg water takes longer to wash
        builder.copy()
            .fluidInputs(TierAcid.regWater.get())
            .addTo(recipeMap);

        // distill water is speedy mc-gee
        builder.duration(Math.max(1, builder.getDuration() / 4))
            .fluidInputs(TierAcid.distilWater.get())
            .addTo(recipeMap);
    }

    // endregion ore duplication helpers
}
