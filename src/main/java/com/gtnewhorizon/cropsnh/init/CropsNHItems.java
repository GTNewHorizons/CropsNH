package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.items.CropsNHAlcoholBottle;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.items.ItemFertilizer;
import com.gtnewhorizon.cropsnh.items.ItemGenericSeed;
import com.gtnewhorizon.cropsnh.items.ItemNaNCertificate;
import com.gtnewhorizon.cropsnh.items.produce.ItemBerry;
import com.gtnewhorizon.cropsnh.items.produce.ItemGoldfish;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;
import com.gtnewhorizon.cropsnh.items.produce.ItemTerraWart;
import com.gtnewhorizon.cropsnh.items.tools.ItemDebugger;
import com.gtnewhorizon.cropsnh.items.tools.ItemMagnifyingGlass;
import com.gtnewhorizon.cropsnh.items.tools.ItemSpade;
import com.gtnewhorizon.cropsnh.loaders.MaterialLeafLoader;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import kubatech.tileentity.gregtech.multiblock.MTEExtremeIndustrialGreenhouse;

public class CropsNHItems {

    public static Item magnifyingGlass;
    public static Item debugItem;
    public static Item nanCertificate;
    public static Item genericSeed;
    public static Item terraWart;
    public static Item spade;
    public static Item goldfish;
    public static Item berry;
    public static Item bottledAlcohol;
    public static Item fertilizer;
    public static Item materialLeaf;

    public static void preInit() {
        magnifyingGlass = new ItemMagnifyingGlass();
        CropsNHItemList.magnifyingGlass.set(new ItemStack(magnifyingGlass, 1, 0));
        debugItem = new ItemDebugger();
        nanCertificate = new ItemNaNCertificate();
        CropsNHItemList.nanCertificate.set(new ItemStack(nanCertificate, 1, 0));

        terraWart = new ItemTerraWart();
        CropsNHItemList.terraWart.set(new ItemStack(terraWart, 1, 0));
        genericSeed = new ItemGenericSeed();
        spade = new ItemSpade();
        CropsNHItemList.spade.set(new ItemStack(spade, 1, 0));
        goldfish = new ItemGoldfish();
        CropsNHItemList.goldfish.set(new ItemStack(goldfish, 1, 0));
        berry = new ItemBerry();
        CropsNHItemList.huckleBerry.set(new ItemStack(berry, 1, 0));
        CropsNHItemList.sugarBeet.set(new ItemStack(berry, 1, 1));

        bottledAlcohol = new CropsNHAlcoholBottle();
        CropsNHItemList.fermentedWheatBottle.set(new ItemStack(bottledAlcohol, 1, 0));
        CropsNHItemList.kornBottle.set(new ItemStack(bottledAlcohol, 1,1));
        CropsNHItemList.doppelkornBottle.set(new ItemStack(bottledAlcohol, 1, 2));
        CropsNHItemList.fermentedReedwaterBottle.set(new ItemStack(bottledAlcohol, 1, 3));
        CropsNHItemList.sugarWhineBottle.set(new ItemStack(bottledAlcohol, 1, 4));
        CropsNHItemList.mashBottle.set(new ItemStack(bottledAlcohol, 1, 5));
        CropsNHItemList.washBottle.set(new ItemStack(bottledAlcohol, 1, 6));
        CropsNHItemList.highProofBottle.set(new ItemStack(bottledAlcohol, 1, 7));
        CropsNHItemList.realJagermeisterBottle.set(new ItemStack(bottledAlcohol, 1, 8));
        CropsNHItemList.fakeJagermeisterBottle.set(new ItemStack(bottledAlcohol, 1, 9));

        // register fertilizer
        fertilizer = new ItemFertilizer();
        CropsNHItemList.fertilizer.set(new ItemStack(fertilizer, 1, 0));
        // update EIG fert registry
        MTEExtremeIndustrialGreenhouse.addFertilizerItem(CropsNHItemList.fertilizer.get(1));

        MaterialLeafLoader.preInit();
        materialLeaf = new ItemMaterialLeaf();
        // spotless:off
        CropsNHItemList.bauxiaLeaf.set(MaterialLeafLoader.bauxiaLeaf.get());
        CropsNHItemList.canolaFLower.set(MaterialLeafLoader.canolaFLower.get());
        CropsNHItemList.copponFiber.set(MaterialLeafLoader.copponFiber.get());
        CropsNHItemList.galvaniaLeaf.set(MaterialLeafLoader.galvaniaLeaf.get());
        CropsNHItemList.indigoBlossom.set(MaterialLeafLoader.indigoBlossom.get());
        CropsNHItemList.iridineFlower.set(MaterialLeafLoader.iridineFlower.get());
        CropsNHItemList.magicEssence.set(MaterialLeafLoader.magicEssence.get());
        // crops++
        CropsNHItemList.micadiaFlower.set(MaterialLeafLoader.micadiaFlower.get());
        CropsNHItemList.milkwart.set(MaterialLeafLoader.milkwart.get());
        CropsNHItemList.nickelbackLeaf.set(MaterialLeafLoader.nickelbackLeaf.get());
        CropsNHItemList.oilBerry.set(MaterialLeafLoader.oilBerry.get());
        CropsNHItemList.osmianthFlower.set(MaterialLeafLoader.osmianthFlower.get());
        CropsNHItemList.platinaLeaf.set(MaterialLeafLoader.platinaLeaf.get());
        CropsNHItemList.pyrolusiumLeaf.set(MaterialLeafLoader.pyrolusiumLeaf.get());
        CropsNHItemList.reactoriaLeaf.set(MaterialLeafLoader.reactoriaLeaf.get());
        CropsNHItemList.reactoriaStem.set(MaterialLeafLoader.reactoriaStem.get());
        CropsNHItemList.scheeliniumLeaf.set(MaterialLeafLoader.scheeliniumLeaf.get());
        CropsNHItemList.spaceFlower.set(MaterialLeafLoader.spaceFlower.get());
        // crops+++
        CropsNHItemList.stargatiumLeaf.set(MaterialLeafLoader.stargatiumLeaf.get());
        CropsNHItemList.thunderLeaf.set(MaterialLeafLoader.thunderFlower.get());
        CropsNHItemList.tineTwig.set(MaterialLeafLoader.tineTwig.get());
        CropsNHItemList.titaniaLeaf.set(MaterialLeafLoader.titaniaLeaf.get());
        CropsNHItemList.uuaBerry.set(MaterialLeafLoader.uuaBerry.get());
        CropsNHItemList.uumBerry.set(MaterialLeafLoader.uumBerry.get());
        CropsNHItemList.saltyRoot.set(MaterialLeafLoader.saltyRoot.get());
        // ggfab needs to be updated manually
        CropsNHItemList.plumbiliaLeaf.set(MaterialLeafLoader.plumbiliaLeaf.get());
        CropsNHItemList.argentiaLeaf.set(MaterialLeafLoader.argentiaLeaf.get());
        CropsNHItemList.ferruLeaf.set(MaterialLeafLoader.ferruLeaf.get());
        CropsNHItemList.aureliaLeaf.set(MaterialLeafLoader.aureliaLeaf.get());
        CropsNHItemList.teaLeaf.set(MaterialLeafLoader.teaLeaf.get());
        CropsNHItemList.bobsYerUncleBerry.set(MaterialLeafLoader.bobsYerUncleBerry.get());
        CropsNHItemList.starwart.set(MaterialLeafLoader.starwart.get());
        // brand spankin new so no need to override anything
        CropsNHItemList.hops.set(MaterialLeafLoader.hops.get());
        //spotless:on
        LogHelper.debug("Items Registered");
    }
}
