package com.gtnewhorizon.cropsnh.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.items.CropsNHAlcoholBottle;
import com.gtnewhorizon.cropsnh.items.ItemEnvironmentalModule;
import com.gtnewhorizon.cropsnh.items.ItemFertilizer;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.items.ItemHempHurd;
import com.gtnewhorizon.cropsnh.items.ItemMug;
import com.gtnewhorizon.cropsnh.items.ItemPoisonPowder;
import com.gtnewhorizon.cropsnh.items.produce.ItemBerry;
import com.gtnewhorizon.cropsnh.items.produce.ItemGaiaWart;
import com.gtnewhorizon.cropsnh.items.produce.ItemGoldfish;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;
import com.gtnewhorizon.cropsnh.items.tools.ItemPlantCure;
import com.gtnewhorizon.cropsnh.items.tools.ItemPlantLens;
import com.gtnewhorizon.cropsnh.items.tools.ItemReinforcedSpade;
import com.gtnewhorizon.cropsnh.items.tools.ItemSpade;
import com.gtnewhorizon.cropsnh.items.tools.ItemWeedEX;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import gregtech.api.enums.ItemList;

public class CropsNHItems {

    public static Item plantLens;
    public static Item genericSeed;
    public static Item gaiaWart;
    public static Item spade;
    public static Item reinforcedSpade;
    public static Item plantCure;
    public static Item weedEX;
    public static Item goldfish;
    public static Item berry;
    public static Item mug;
    public static Item bottledAlcohol;
    public static Item fertilizer;
    public static Item poisonPowder;
    public static Item hempHurd;
    public static Item materialLeaf;
    public static Item environmentalModule;

    public static void preInit() {
        genericSeed = new ItemGenericSeed();
        CropsNHItemList.plantLens.set(new ItemStack(plantLens = new ItemPlantLens(), 1, 0));
        CropsNHItemList.gaiaWart.set(new ItemStack(gaiaWart = new ItemGaiaWart(), 1, 0));
        CropsNHItemList.spade.set(new ItemStack(spade = new ItemSpade(), 1, 0));
        CropsNHItemList.reinforcedSpade.set(new ItemStack(reinforcedSpade = new ItemReinforcedSpade(), 1, 0));
        CropsNHItemList.plantCure.set(new ItemStack(plantCure = new ItemPlantCure(), 1));
        CropsNHItemList.goldfish.set(new ItemStack(goldfish = new ItemGoldfish(), 1, 0));
        CropsNHItemList.fertilizer.set(new ItemStack(fertilizer = new ItemFertilizer(), 1, 0));
        CropsNHItemList.poisonPowder.set(new ItemStack(poisonPowder = new ItemPoisonPowder(), 1, 0));
        CropsNHItemList.hempHurd.set(new ItemStack(hempHurd = new ItemHempHurd(), 1, 0));

        CropsNHItemList.weedEX.set(new ItemStack(weedEX = new ItemWeedEX(), 1));
        FluidContainerRegistry.registerFluidContainer(
            new FluidContainerRegistry.FluidContainerData(
                CropsNHUtils.getWeedEXFluid(Constants.WEEDEX_CAPACITY),
                CropsNHItemList.weedEX.get(1),
                ItemList.Spray_Empty.get(1)));

        mug = new ItemMug();
        CropsNHItemList.emptyMug.set(new ItemStack(mug, 1, ItemMug.META_STONE_MUG));
        CropsNHItemList.coldCoffeeMug.set(new ItemStack(mug, 1, ItemMug.META_COLD_COFFEE));
        CropsNHItemList.darkCoffeeMug.set(new ItemStack(mug, 1, ItemMug.META_DARK_COFFEE));
        CropsNHItemList.coffeeMug.set(new ItemStack(mug, 1, ItemMug.META_COFFEE));

        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("potion.coffee", 500),
            CropsNHItemList.darkCoffeeMug.get(1),
            CropsNHItemList.emptyMug.get(1));
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("potion.latte", 500),
            CropsNHItemList.coffeeMug.get(1),
            CropsNHItemList.emptyMug.get(1));

        berry = new ItemBerry();
        CropsNHItemList.huckleBerry.set(new ItemStack(berry, 1, ItemBerry.META_HUCKLE));
        CropsNHItemList.sugarBeet.set(new ItemStack(berry, 1, ItemBerry.META_SUGARBEET));
        CropsNHItemList.maxTomato.set(new ItemStack(berry, 1, ItemBerry.META_MAX_TOMATO));

        bottledAlcohol = new CropsNHAlcoholBottle();
        CropsNHItemList.fermentedWheatBottle.set(new ItemStack(bottledAlcohol, 1, 0));
        CropsNHItemList.kornBottle.set(new ItemStack(bottledAlcohol, 1, 1));
        CropsNHItemList.doppelkornBottle.set(new ItemStack(bottledAlcohol, 1, 2));
        CropsNHItemList.fermentedReedwaterBottle.set(new ItemStack(bottledAlcohol, 1, 3));
        CropsNHItemList.sugarWhineBottle.set(new ItemStack(bottledAlcohol, 1, 4));
        CropsNHItemList.mashBottle.set(new ItemStack(bottledAlcohol, 1, 5));
        CropsNHItemList.washBottle.set(new ItemStack(bottledAlcohol, 1, 6));
        CropsNHItemList.highProofBottle.set(new ItemStack(bottledAlcohol, 1, 7));
        CropsNHItemList.realJagermeisterBottle.set(new ItemStack(bottledAlcohol, 1, 8));
        CropsNHItemList.fakeJagermeisterBottle.set(new ItemStack(bottledAlcohol, 1, 9));

        MaterialLeafLoader.preInit();
        materialLeaf = new ItemMaterialLeaf();
        // spotless:off
        CropsNHItemList.bauxiaLeaf.set(MaterialLeafLoader.bauxiaLeaf.get());
        CropsNHItemList.canolaFlower.set(MaterialLeafLoader.canolaFLower.get());
        CropsNHItemList.copponFiber.set(MaterialLeafLoader.copponFiber.get());
        CropsNHItemList.galvaniaLeaf.set(MaterialLeafLoader.galvaniaLeaf.get());
        CropsNHItemList.indigoBlossom.set(MaterialLeafLoader.indigoBlossom.get());
        CropsNHItemList.iridineFlower.set(MaterialLeafLoader.iridineFlower.get());
        CropsNHItemList.magicEssence.set(MaterialLeafLoader.magicEssence.get());
        CropsNHItemList.micadiaFlower.set(MaterialLeafLoader.micadiaFlower.get());
        CropsNHItemList.milkWart.set(MaterialLeafLoader.milkWart.get());
        CropsNHItemList.nickelbackLeaf.set(MaterialLeafLoader.nickelbackLeaf.get());
        CropsNHItemList.oilBerry.set(MaterialLeafLoader.oilBerry.get());
        CropsNHItemList.osmianthFlower.set(MaterialLeafLoader.osmianthFlower.get());
        CropsNHItemList.platinaLeaf.set(MaterialLeafLoader.platinaLeaf.get());
        CropsNHItemList.pyrolusiumLeaf.set(MaterialLeafLoader.pyrolusiumLeaf.get());
        CropsNHItemList.reactoriaLeaf.set(MaterialLeafLoader.reactoriaLeaf.get());
        CropsNHItemList.reactoriaStem.set(MaterialLeafLoader.reactoriaStem.get());
        CropsNHItemList.scheeliniumLeaf.set(MaterialLeafLoader.scheeliniumLeaf.get());
        CropsNHItemList.spaceFlower.set(MaterialLeafLoader.spaceFlower.get());
        CropsNHItemList.stargatiumLeaf.set(MaterialLeafLoader.stargatiumLeaf.get());
        CropsNHItemList.thunderLeaf.set(MaterialLeafLoader.thunderFlower.get());
        CropsNHItemList.tineTwig.set(MaterialLeafLoader.tineTwig.get());
        CropsNHItemList.titaniaLeaf.set(MaterialLeafLoader.titaniaLeaf.get());
        CropsNHItemList.uuaBerry.set(MaterialLeafLoader.uuaBerry.get());
        CropsNHItemList.uumBerry.set(MaterialLeafLoader.uumBerry.get());
        CropsNHItemList.saltyRoot.set(MaterialLeafLoader.saltyRoot.get());
        CropsNHItemList.plumbiliaLeaf.set(MaterialLeafLoader.plumbiliaLeaf.get());
        CropsNHItemList.argentiaLeaf.set(MaterialLeafLoader.argentiaLeaf.get());
        CropsNHItemList.ferrofernLeaf.set(MaterialLeafLoader.ferrofernLeaf.get());
        CropsNHItemList.auroniaLeaf.set(MaterialLeafLoader.auroniaLeaf.get());
        CropsNHItemList.bobsYerUncleBerry.set(MaterialLeafLoader.bobsYerUncleBerry.get());
        CropsNHItemList.starWart.set(MaterialLeafLoader.starWart.get());
        CropsNHItemList.hops.set(MaterialLeafLoader.hops.get());
        CropsNHItemList.hempStem.set(MaterialLeafLoader.hempStem.get());
        CropsNHItemList.rubyneLeaf.set(MaterialLeafLoader.rubyneLeaf.get());
        //spotless:on

        environmentalModule = new ItemEnvironmentalModule();
        // spotless:off
        CropsNHItemList.environmentalModule_base.set(new ItemStack(environmentalModule, 1, 0));
        CropsNHItemList.environmentalModule_HOT.set(ItemEnvironmentalModule.registerVariant(1, BiomeDictionary.Type.HOT));
        CropsNHItemList.environmentalModule_COLD.set(ItemEnvironmentalModule.registerVariant(2, BiomeDictionary.Type.COLD));
        CropsNHItemList.environmentalModule_SPARSE.set(ItemEnvironmentalModule.registerVariant(3, BiomeDictionary.Type.SPARSE));
        CropsNHItemList.environmentalModule_DENSE.set(ItemEnvironmentalModule.registerVariant(4, BiomeDictionary.Type.DENSE));
        CropsNHItemList.environmentalModule_WET.set(ItemEnvironmentalModule.registerVariant(5, BiomeDictionary.Type.WET));
        CropsNHItemList.environmentalModule_DRY.set(ItemEnvironmentalModule.registerVariant(6, BiomeDictionary.Type.DRY));
        CropsNHItemList.environmentalModule_SAVANNA.set(ItemEnvironmentalModule.registerVariant(7, BiomeDictionary.Type.SAVANNA));
        CropsNHItemList.environmentalModule_CONIFEROUS.set(ItemEnvironmentalModule.registerVariant(8, BiomeDictionary.Type.CONIFEROUS));
        CropsNHItemList.environmentalModule_JUNGLE.set(ItemEnvironmentalModule.registerVariant(9, BiomeDictionary.Type.JUNGLE));
        CropsNHItemList.environmentalModule_SPOOKY.set(ItemEnvironmentalModule.registerVariant(10, BiomeDictionary.Type.SPOOKY));
        CropsNHItemList.environmentalModule_DEAD.set(ItemEnvironmentalModule.registerVariant(11, BiomeDictionary.Type.DEAD));
        CropsNHItemList.environmentalModule_LUSH.set(ItemEnvironmentalModule.registerVariant(12, BiomeDictionary.Type.LUSH));
        CropsNHItemList.environmentalModule_NETHER.set(ItemEnvironmentalModule.registerVariant(13, BiomeDictionary.Type.NETHER));
        CropsNHItemList.environmentalModule_END.set(ItemEnvironmentalModule.registerVariant(14, BiomeDictionary.Type.END));
        CropsNHItemList.environmentalModule_MUSHROOM.set(ItemEnvironmentalModule.registerVariant(15, BiomeDictionary.Type.MUSHROOM));
        CropsNHItemList.environmentalModule_MAGICAL.set(ItemEnvironmentalModule.registerVariant(16, BiomeDictionary.Type.MAGICAL));
        CropsNHItemList.environmentalModule_OCEAN.set(ItemEnvironmentalModule.registerVariant(17, BiomeDictionary.Type.OCEAN));
        CropsNHItemList.environmentalModule_RIVER.set(ItemEnvironmentalModule.registerVariant(18, BiomeDictionary.Type.RIVER));
        CropsNHItemList.environmentalModule_MESA.set(ItemEnvironmentalModule.registerVariant(19, BiomeDictionary.Type.MESA));
        CropsNHItemList.environmentalModule_FOREST.set(ItemEnvironmentalModule.registerVariant(20, BiomeDictionary.Type.FOREST));
        CropsNHItemList.environmentalModule_PLAINS.set(ItemEnvironmentalModule.registerVariant(21, BiomeDictionary.Type.PLAINS));
        CropsNHItemList.environmentalModule_MOUNTAIN.set(ItemEnvironmentalModule.registerVariant(22, BiomeDictionary.Type.MOUNTAIN));
        CropsNHItemList.environmentalModule_HILLS.set(ItemEnvironmentalModule.registerVariant(23, BiomeDictionary.Type.HILLS));
        CropsNHItemList.environmentalModule_SWAMP.set(ItemEnvironmentalModule.registerVariant(24, BiomeDictionary.Type.SWAMP));
        CropsNHItemList.environmentalModule_SANDY.set(ItemEnvironmentalModule.registerVariant(25, BiomeDictionary.Type.SANDY));
        CropsNHItemList.environmentalModule_SNOWY.set(ItemEnvironmentalModule.registerVariant(26, BiomeDictionary.Type.SNOWY));
        CropsNHItemList.environmentalModule_WASTELAND.set(ItemEnvironmentalModule.registerVariant(27, BiomeDictionary.Type.WASTELAND));
        CropsNHItemList.environmentalModule_BEACH.set(ItemEnvironmentalModule.registerVariant(28, BiomeDictionary.Type.BEACH));
        //spotless:on
        LogHelper.debug("Items Registered");
    }
}
