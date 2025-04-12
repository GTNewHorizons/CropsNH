package com.gtnewhorizon.cropsnh.loaders.gtrecipes;

import bartworks.system.material.Werkstoff;
import bartworks.system.material.WerkstoffLoader;
import cofh.core.util.energy.FurnaceFuelHandler;
import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.api.IMaterialLeafVariant;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeBuilder;
import gregtech.api.util.GTRecipeConstants;
import gregtech.api.util.GTUtility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.common.config.ConfigItems;

import static gregtech.common.items.ItemComb.Voltage;
import static gregtech.api.recipe.RecipeMaps.autoclaveRecipes;
import static gregtech.api.recipe.RecipeMaps.compressorRecipes;
import static gregtech.api.recipe.RecipeMaps.extractorRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.hammerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

public class CropRecipes {

    public enum TierAcid {
        regWater("water"),
        distilWater("distilled water"),
        t1("Sulfuric Acid"),
        t2("Hydrochloric Acid"),
        t3("Formic Acid"),
        t4("Hydrofluoric Acid"),
        t5("Nitric Acid"),
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
        addMilkwartRecipes();
        addOilBerryRecipes();
        addTineRecipes();
    }

    private static void addPlantBallRecipes() {
        for (IMaterialLeafVariant variant : ItemMaterialLeaf.getRegisteredVariants()) {
            ItemStack input = variant.get()
                .copy();
            input.stackSize = 4;
            // TODO: REPLACE PLANT BALL WITH WHAT EVER ENDS UP REPLACING IT DOWN THE LINE
            GTValues.RA.stdBuilder()
                .itemInputs(input, GTUtility.getIntegratedCircuit(24))
                .itemOutputs(ItemList.IC2_PlantballCompressed.get(1L))
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

        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.Iron);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.Magnetite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.BrownLimonite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.YellowLimonite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.VanadiumMagnetite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.BandedIron);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.Pyrite);
        createOreDuplicationRecipe(MaterialLeafLoader.ferruLeaf, Materials.MeteoricIron);

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

        createOreDuplicationRecipe(MaterialLeafLoader.aureliaLeaf, Materials.Gold);
        createOreDuplicationRecipe(MaterialLeafLoader.aureliaLeaf, Materials.Magnetite);

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

        createOreDuplicationRecipe(MaterialLeafLoader.stargatiumLeaf, Materials.Naquadah);
        createOreDuplicationRecipe(MaterialLeafLoader.stargatiumLeaf, Materials.NaquadahEnriched);
        createOreDuplicationRecipe(MaterialLeafLoader.stargatiumLeaf, Materials.Naquadria);

        createOreDuplicationRecipe(MaterialLeafLoader.bobsYerUncleBerry, Materials.Emerald);
        createOreDuplicationRecipe(MaterialLeafLoader.bobsYerUncleBerry, Materials.Beryllium);
    }

    private static void addOreConversionRecipes() {

        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.micadiaFlower.get(1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Mica, 1))
            .eut(TierEU.HV)
            .duration(Voltage.HV.getSimpleTime())
            .addTo(extractorRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.tineTwig.get(1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Tin, 1))
            .eut(TierEU.HV)
            .duration(Voltage.ULV.getSimpleTime())
            .addTo(extractorRecipes);

        createOreConversionRecipe(MaterialLeafLoader.copponFiber, Voltage.LV, Materials.Copper, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.galvaniaLeaf, Voltage.LV, Materials.Zinc, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.nickelbackLeaf, Voltage.LV, Materials.Nickel, TierAcid.t1);
        createOreConversionRecipe(MaterialLeafLoader.pyrolusiumLeaf, Voltage.LV, Materials.Manganese, TierAcid.t1);

        createOreConversionRecipe(MaterialLeafLoader.bauxiaLeaf, Voltage.LV, Materials.Bauxite, TierAcid.t2);

        createOreConversionRecipe(MaterialLeafLoader.platinaLeaf, Voltage.HV, WerkstoffLoader.PTConcentrate.getFluidOrGas(1000), TierAcid.t3, Voltage.HV.getComplexTime() * 10);

        createOreConversionRecipe(MaterialLeafLoader.scheeliniumLeaf, Voltage.EV, Materials.Scheelite, TierAcid.t5);
        createOreConversionRecipe(MaterialLeafLoader.reactoriaLeaf, Voltage.EV, Materials.Pitchblende, TierAcid.t5);
        createOreConversionRecipe(MaterialLeafLoader.reactoriaStem, Voltage.EV, Materials.Uranium, TierAcid.t6);
        createOreConversionRecipe(MaterialLeafLoader.thunderFlower, Voltage.EV, Materials.Thorium, TierAcid.t6);

        createOreConversionRecipe(MaterialLeafLoader.osmianthFlower, Voltage.IV, WerkstoffLoader.AcidicOsmiumSolution.getFluidOrGas(1000), TierAcid.t6, Voltage.HV.getComplexTime() * 17);
        createOreConversionRecipe(MaterialLeafLoader.iridineFlower, Voltage.IV, WerkstoffLoader.AcidicIridiumSolution.getFluidOrGas(1000), TierAcid.t6, Voltage.HV.getComplexTime() * 14);
        createOreConversionRecipe(MaterialLeafLoader.stargatiumLeaf, Voltage.IV, Materials.Naquadah, TierAcid.t6);

    }

    private static void addCopponRecipes() {
        ulvRecipe(3, 75)
            .itemInputs(MaterialLeafLoader.copponFiber.get(9))
            .itemOutputs(new ItemStack[] {GTOreDictUnificator.get(OrePrefixes.dust, Materials.Copper, 1)}, new int[] { 20_00 })
            .addTo(extractorRecipes);

        recipe(2, 15,0)
            .itemInputs(MaterialLeafLoader.copponFiber.get(4), GTUtility.getIntegratedCircuit(1))
            .itemOutputs(new ItemStack(Blocks.wool, 1, 1))
            .addTo(compressorRecipes);
    }

    private static void addCanolaRecipes() {
        // canola oil extraction
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.canolaFLower.get(1))
            .fluidOutputs(Materials.SeedOil.getFluid(125))
            .duration(1 * SECONDS + 12 * TICKS)
            .eut(2)
            .addTo(fluidExtractionRecipes);
    }

    private static void addMagicEssenceRecipes() {
        if (!Mods.Thaumcraft.isModLoaded()) return;
        // salis mundus extraction
        ulvRecipe(3, 20)
            .itemInputs(MaterialLeafLoader.magicEssence.get(1))
            .itemOutputs(new ItemStack(ConfigItems.itemResource, 16, 14))
            .addTo(RecipeMaps.extractorRecipes);
        // iron to thaumium conversion
        mvRecipe(12, 0)
            .itemInputs(new Object[] { "dustIron", 4}, CropsNHItemList.magicEssence.get(1))
            .fluidInputs(TierAcid.t1.get(1000))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Thaumium, 4))
            .addTo(GTRecipeConstants.UniversalChemical);
        // thaumium to void conversion
        hvRecipe(12,0)
            .itemInputs(
                new Object[] { "dustThaumium", 4},
                CropsNHItemList.magicEssence.get(2),
                new ItemStack(ConfigItems.itemResource, 1, 17)
            )
            .fluidInputs(TierAcid.t2.get(4000))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Void, 4))
            .addTo(GTRecipeConstants.UniversalChemical);
        if (Mods.TaintedMagic.isModLoaded()) {
            // void to shadow metal conversion
            ivRecipe(12,0)
                .itemInputs(new Object[] { "dustVoid", 4}, CropsNHItemList.magicEssence.get(4))
                .fluidInputs(TierAcid.t4.get(16_000))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Shadow, 4))
                .addTo(GTRecipeConstants.UniversalChemical);
            if (Mods.ThaumicTinkerer.isModLoaded() && Mods.BloodMagic.isModLoaded()) {
                // shadow metal to ichorium conversion
                luvRecipe(90,0)
                    .itemInputs(
                        new Object[] { "dustVoid", 4},
                        CropsNHItemList.magicEssence.get(8),
                        new Object[] { "ingotOsmiridium", 1},
                        getModItem(Mods.BloodMagic.ID, "bloodMagicBaseItems", 4,28),
                        getModItem(Mods.BloodMagic.ID, "bloodMagicBaseItems", 4,29)
                    )
                    .fluidInputs(TierAcid.t6.get(190_000))
                    .itemOutputs(GTOreDictUnificator.get(OrePrefixes.ingot, Materials.Ichorium, 1))
                    .addTo(GTRecipeConstants.UniversalChemical);
            }
        }
        if (Mods.GalacticraftCore.isModLoaded()) {
            ivRecipe(90,0)
                .itemInputs(
                    CropsNHItemList.spaceFlower.get(16),
                    CropsNHItemList.magicEssence.get(4)
                )
                .fluidInputs(
                    Materials.Platinum.getMolten(288),
                    Materials.MeteoricIron.getMolten(144),
                    TierAcid.t5.get(64_000)
                )
                .addTo(multiblockChemicalReactorRecipes);
        }
        // Magic essence from salis
        hvRecipe(7, 50)
            .itemInputs(new ItemStack(ConfigItems.itemResource, 64, 14))
            .fluidInputs(Materials.Void.getMolten(144 * 16))
            .itemOutputs(CropsNHItemList.magicEssence.get(1))
            .addTo(autoclaveRecipes);

        // inert prim perl crafting recipe
        if (Mods.NewHorizonsCoreMod.isModLoaded() && Mods.WitchingGadgets.isModLoaded()) {
            evRecipe(5 * 60, 0)
                .itemInputs(CropsNHItemList.magicEssence.get(64))
                .fluidInputs(Materials.Ichorium.getMolten(144 * 3))
                .itemOutputs(getModItem(Mods.NewHorizonsCoreMod.ID, "item.PrimordialPerlFragment", 3))
                .addTo(autoclaveRecipes);
        }
    }

    private static void addMicadiaRecipes() {
        FurnaceFuelHandler.registerFuel(CropsNHItemList.micadiaFlower.get(1), 240);
    }

    private static void addMilkwartRecipes() {
        // milk fluid extraction
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.milkwart.get(1))
            .itemOutputs(new ItemStack[] { Materials.Milk.getDust(1) }, new int[] { 10_00 })
            .fluidOutputs(Materials.Milk.getFluid(150))
            .duration(6 * SECONDS + 8 * TICKS)
            .eut(4)
            .addTo(fluidExtractionRecipes);
        // milk powder extraction
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialLeafLoader.milkwart.get(1))
            .itemOutputs(GTOreDictUnificator.get(OrePrefixes.dust, Materials.Milk, 0))
            .eut(4)
            .duration(6 * GTRecipeBuilder.SECONDS + 8 * GTRecipeBuilder.TICKS)
            .addTo(RecipeMaps.extractorRecipes);
    }

    private static void addOilBerryRecipes() {
        FluidStack[] fluids = new FluidStack[] {
            Materials.OilLight.getFluid(100),
            Materials.Oil.getFluid(100),
            // raw oil
            Materials.OilMedium.getFluid(100),
            Materials.OilHeavy.getFluid(100)
        };

        for (int i = 0; i < fluids.length; i++) {
            FluidStack sourceFluid = fluids[i];
            // oil extraction
            recipe(4, 6, 40)
                .itemInputs(
                    MaterialLeafLoader.oilBerry.get(1),
                    GTUtility.getIntegratedCircuit(i + 1)
                )
                .fluidOutputs(sourceFluid.copy())
                .addTo(fluidExtractionRecipes);

            // oil conversion, density up
            if (i > 0) {
                FluidStack targetFluid = fluids[i - 1].copy();
                sourceFluid.amount = 1000;
                targetFluid.amount = 1000;
                lvRecipe(0, 50)
                    .itemInputs(
                        MaterialLeafLoader.oilBerry.get(1),
                        GTUtility.getIntegratedCircuit(1)
                    )
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);

                // bulk recipe for tps reasons
                sourceFluid.amount = 64_000;
                targetFluid.amount = 64_000;
                lvRecipe(0, 50 * 64)
                    .itemInputs(
                        MaterialLeafLoader.oilBerry.get(1),
                        GTUtility.getIntegratedCircuit(3)
                    )
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);
            }

            // oil conversion, density down
            if (i < fluids.length - 1) {
                FluidStack targetFluid = fluids[i + 1].copy();
                sourceFluid.amount = 1000;
                targetFluid.amount = 1000;
                lvRecipe(0, 50)
                    .itemInputs(
                        MaterialLeafLoader.oilBerry.get(1),
                        GTUtility.getIntegratedCircuit(2)
                    )
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);

                // bulk recipe for tps reasons
                sourceFluid.amount = 64_000;
                targetFluid.amount = 64_000;
                lvRecipe(0, 50 * 64)
                    .itemInputs(
                        MaterialLeafLoader.oilBerry.get(1),
                        GTUtility.getIntegratedCircuit(4)
                    )
                    .fluidInputs(sourceFluid.copy())
                    .fluidOutputs(targetFluid.copy())
                    .addTo(GTRecipeConstants.UniversalChemical);
            }
        }
    }

    private static void addTineRecipes() {
        FurnaceFuelHandler.registerFuel(CropsNHItemList.tineTwig.get(1), 100);
    }

    // region helpers
    public static final int DEFAULT_ORE_CONVERSION_LEAF_AMOUNT = 4;
    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, Werkstoff ore, TierAcid catalyst) {
        if (ore == null ) {
            throw new IllegalArgumentException("ore cannot be null.");
        }
        createOreConversionRecipe(variant, voltage, ore.getBridgeMaterial(), catalyst);
    }



    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, FluidStack outputFluid, TierAcid catalyst) {
        createOreConversionRecipe(variant, voltage, outputFluid, catalyst, -1);
    }
    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, FluidStack outputFluid, TierAcid catalyst, int duration) {
        createOreConversionRecipe(variant, DEFAULT_ORE_CONVERSION_LEAF_AMOUNT, voltage, outputFluid, catalyst, duration);
    }
    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int leafAmount, Voltage voltage, FluidStack outputFluid, TierAcid catalyst, int duration) {
        if (variant == null || voltage == null || outputFluid == null ) {
            throw new IllegalArgumentException("variant, voltage and outputFluid cannot be null.");
        }
        if (leafAmount <= 0) {
            throw new IllegalArgumentException("leafAmount must be greater than zero.");
        }

        ItemStack[] leaf = new ItemStack[] { variant.get(leafAmount) };
        FluidStack[] acid = catalyst == null ? null : new FluidStack[] { catalyst.get(voltage.getFluidAmount()) };

        createOreConversionRecipe(voltage, duration, leaf, acid, null, null, new FluidStack[] {outputFluid} );
    }



    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, Materials ore, TierAcid catalyst) {
        createOreConversionRecipe(variant, DEFAULT_ORE_CONVERSION_LEAF_AMOUNT, voltage, ore, DEFAULT_ORE_CONVERSION_LEAF_AMOUNT, catalyst, -1);
    }
    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int amount, Voltage voltage, Materials ore, TierAcid catalyst, int duration) {
        createOreConversionRecipe(variant, amount, voltage, ore, amount, catalyst, duration);
    }
    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int leafAmount, Voltage voltage, Materials ore, int oreAmount, TierAcid catalyst, int duration) {
        if (variant == null || voltage == null || ore == null ) {
            throw new IllegalArgumentException("variant, voltage and outputFluid cannot be null.");
        }
        if (leafAmount <= 0) {
            throw new IllegalArgumentException("leafAmount must be greater than zero.");
        }
        if (oreAmount <= 0) {
            throw new IllegalArgumentException("oreAmount must be greater than zero.");
        }

        ItemStack[] leaf = new ItemStack[] {variant.get(leafAmount)};
        ItemStack[] purified = new ItemStack[] { GTOreDictUnificator.get(OrePrefixes.crushedPurified, ore, 4) };
        FluidStack[] acid = catalyst == null ? null : new FluidStack[] { catalyst.get(voltage.getFluidAmount()) };

        createOreConversionRecipe(voltage, duration, leaf, acid, purified, null, null );
    }

    public static void createOreConversionRecipe(IMaterialLeafVariant variant, Voltage voltage, ItemStack ore, TierAcid catalyst) {
        createOreConversionRecipe(variant, DEFAULT_ORE_CONVERSION_LEAF_AMOUNT, voltage, ore, catalyst, -1);
    }
    public static void createOreConversionRecipe(IMaterialLeafVariant variant, int leafAmount, Voltage voltage, ItemStack ore, TierAcid catalyst, int duration) {
        if (variant == null || voltage == null || ore == null ) {
            throw new IllegalArgumentException("variant, voltage and outputFluid cannot be null.");
        }
        if (leafAmount <= 0) {
            throw new IllegalArgumentException("leafAmount must be greater than zero.");
        }

        ItemStack[] leaf = new ItemStack[] {variant.get(leafAmount)};
        ItemStack[] purified = new ItemStack[] { ore };
        FluidStack[] acid = catalyst == null ? null : new FluidStack[] { catalyst.get(voltage.getFluidAmount()) };

        createOreConversionRecipe(voltage, duration, leaf, acid, purified, null, null );
    }

    public static void createOreConversionRecipe(Voltage voltage, int duration, ItemStack[] itemInputs, FluidStack[] fluidInputs, ItemStack[] itemOutputs, int[] chances, FluidStack[] fluidOutputs) {
        if (voltage == null || itemInputs == null || itemInputs.length <= 0) {
            throw new IllegalArgumentException("variant, voltage itemInputs cannot be null.");
        }
        if (duration <= 0) {
            duration = voltage.getComplexTime();
        }
        if (fluidInputs != null && fluidInputs.length <= 0) {
            fluidInputs = null;
        }
        if (fluidOutputs != null && fluidOutputs.length <= 0)  {
            fluidOutputs = null;
        }
        if (itemOutputs != null) {
            if (itemOutputs.length <= 0) {
                chances = null;
                itemOutputs = null;
            }
            else if (chances != null) {
                if (chances.length <= 0) {
                    chances = null;
                }
                else if (chances.length != itemOutputs.length) {
                    throw new IllegalArgumentException("output chance is doesn't have the right number of entries (" + itemOutputs.length + ")");
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
            builder.fluidOutputs(fluidInputs);
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
        }
        else if (fluidInputs == null && fluidOutputs != null && itemInputs.length == 1 && fluidOutputs.length == 1) {
            builder.addTo(fluidExtractionRecipes);
        }
        else {
            builder.addTo(hammerRecipes);
        }

    }

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, Werkstoff oreType) {
        if (oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        createOreDuplicationRecipe(variant, oreType.getBridgeMaterial());
    }

    public static void createOreDuplicationRecipe(IMaterialLeafVariant variant, Materials oreType) {
        if (variant == null || oreType == null) {
            throw new IllegalArgumentException("no argument can be null");
        }
        ItemStack leaf = variant.get(1);
        ItemStack crushed = GTOreDictUnificator.get(OrePrefixes.crushed, oreType, 1);
        ItemStack purified = GTOreDictUnificator.get(OrePrefixes.crushedPurified, oreType, 4);
        FluidStack byproduct = null;
        if (!oreType.mOreByProducts.isEmpty()) {
            byproduct = oreType.mOreByProducts.get(0).getMolten(144);
        }

        createOreDuplicationRecipe(leaf, crushed, purified, byproduct);
    }

    public static void createOreDuplicationRecipe(ItemStack leaf, ItemStack crushed, ItemStack purified, FluidStack byproduct) {
        if (crushed == null || purified == null) {
            throw new IllegalArgumentException("crushed and purified can't be null");
        }

        // create recipe base
        GTRecipeBuilder builder = GTValues.RA.stdBuilder()
            .eut(TierEU.ULV * 3)
            .duration(4 * SECONDS + 16 * TICKS)
            .itemInputs(leaf, crushed)
            .itemOutputs(purified);

        // add byproduct if one is available
        if (byproduct != null) {
            builder.fluidOutputs(byproduct);
        }

        // reg water takes longer to wash
        builder.copy()
            .fluidInputs(TierAcid.regWater.get())
            .addTo(UniversalChemical);

        // distill water is speedy mc-gee
        builder
            .duration(Math.max(1, builder.getDuration() / 4))
            .fluidInputs(TierAcid.distilWater.get())
            .addTo(UniversalChemical);
    }

    private static GTRecipeBuilder recipe(long eut, int seconds, int fraction) {
        int duration = seconds * SECONDS + fraction / 5;
        return GTValues.RA.stdBuilder().eut(eut).duration(duration);
    }
    private static GTRecipeBuilder ulvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_ULV, seconds, fraction);
    }
    private static GTRecipeBuilder lvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_LV, seconds, fraction);
    }
    private static GTRecipeBuilder mvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_MV, seconds, fraction);
    }
    private static GTRecipeBuilder hvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_HV, seconds, fraction);
    }
    private static GTRecipeBuilder evRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_EV, seconds, fraction);
    }
    private static GTRecipeBuilder ivRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_IV, seconds, fraction);
    }
    private static GTRecipeBuilder luvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_LuV, seconds, fraction);
    }
    private static GTRecipeBuilder zpmRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_ZPM, seconds, fraction);
    }
    private static GTRecipeBuilder uvRecipe(int seconds, int fraction) {
        return recipe(TierEU.RECIPE_UV, seconds, fraction);
    }

    private static ItemStack getModItem(String modId, String name, int amount) {
        return getModItem(modId, name, amount, 0);
    }

    private static final ItemStack missing = new ItemStack(Blocks.fire).setStackDisplayName("Missing!");
    private static ItemStack getModItem(String modId, String name, int amount, int meta) {
        return GTModHandler.getModItem(modId, name, amount, meta, missing);
    }

    // endregion helpers
}
