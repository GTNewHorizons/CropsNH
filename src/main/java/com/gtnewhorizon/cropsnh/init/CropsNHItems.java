package com.gtnewhorizon.cropsnh.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

public class CropsNHItems {

    public static Item magnifyingGlass;
    public static Item debugItem;
    public static Item nanCertificate;
    public static Item genericSeed;
    public static Item terraWart;
    public static Item spade;
    public static Item goldfish;
    public static Item berry;
    public static Item materialLeaf;

    public static void preInit() {
        magnifyingGlass = new ItemMagnifyingGlass();
        CropsNHItemList.magnifyingGlass.set(new ItemStack(magnifyingGlass, 1, 0));
        debugItem = new ItemDebugger();
        nanCertificate = new ItemNaNCertificate();

        terraWart = new ItemTerraWart();
        CropsNHItemList.terraWart.set(new ItemStack(terraWart, 1, 0));
        genericSeed = new ItemGenericSeed();
        spade = new ItemSpade();
        CropsNHItemList.spade.set(new ItemStack(spade, 1, 0));
        goldfish = new ItemGoldfish();
        CropsNHItemList.goldfish.set(new ItemStack(goldfish, 1, 0));
        berry = new ItemBerry();
        CropsNHItemList.huckleBerry.set(new ItemStack(materialLeaf, 1, 0));
        CropsNHItemList.sugarBeet.set(new ItemStack(materialLeaf, 1, 1));

        MaterialLeafLoader.init();
        materialLeaf = new ItemMaterialLeaf();
        // spotless:off
        CropsNHItemList.bauxiaLeaf.set(       new ItemStack(materialLeaf, 1, MaterialLeafLoader.bauxiaLeaf.getId()));
        CropsNHItemList.canolaFLower.set(     new ItemStack(materialLeaf, 1, MaterialLeafLoader.canolaFLower.getId()));
        CropsNHItemList.copponFiber.set(      new ItemStack(materialLeaf, 1, MaterialLeafLoader.copponFiber.getId()));
        CropsNHItemList.galvaniaLeaf.set(     new ItemStack(materialLeaf, 1, MaterialLeafLoader.galvaniaLeaf.getId()));
        CropsNHItemList.indigoBlossom.set(    new ItemStack(materialLeaf, 1, MaterialLeafLoader.indigoBlossom.getId()));
        CropsNHItemList.iridineFlower.set(    new ItemStack(materialLeaf, 1, MaterialLeafLoader.iridineFlower.getId()));
        CropsNHItemList.magicEssence.set(     new ItemStack(materialLeaf, 1, MaterialLeafLoader.magicEssence.getId()));
        CropsNHItemList.micadiaFlower.set(    new ItemStack(materialLeaf, 1, MaterialLeafLoader.micadiaFlower.getId()));
        CropsNHItemList.milkwart.set(         new ItemStack(materialLeaf, 1, MaterialLeafLoader.milkwart.getId()));
        CropsNHItemList.nickelbackLeaf.set(   new ItemStack(materialLeaf, 1, MaterialLeafLoader.nickelbackLeaf.getId()));
        CropsNHItemList.oilBerry.set(         new ItemStack(materialLeaf, 1, MaterialLeafLoader.oilBerry.getId()));
        CropsNHItemList.osmianthFlower.set(   new ItemStack(materialLeaf, 1, MaterialLeafLoader.osmianthFlower.getId()));
        CropsNHItemList.platinaLeaf.set(      new ItemStack(materialLeaf, 1, MaterialLeafLoader.platinaLeaf.getId()));
        CropsNHItemList.pyrolusiumLeaf.set(   new ItemStack(materialLeaf, 1, MaterialLeafLoader.pyrolusiumLeaf.getId()));
        CropsNHItemList.reactoriaLeaf.set(    new ItemStack(materialLeaf, 1, MaterialLeafLoader.reactoriaLeaf.getId()));
        CropsNHItemList.reactoriaStem.set(    new ItemStack(materialLeaf, 1, MaterialLeafLoader.reactoriaStem.getId()));
        CropsNHItemList.scheeliniumLeaf.set(  new ItemStack(materialLeaf, 1, MaterialLeafLoader.scheeliniumLeaf.getId()));
        CropsNHItemList.spaceFlower.set(      new ItemStack(materialLeaf, 1, MaterialLeafLoader.spaceFlower.getId()));
        CropsNHItemList.stargatiumLeaf.set(   new ItemStack(materialLeaf, 1, MaterialLeafLoader.stargatiumLeaf.getId()));
        CropsNHItemList.thunderLeaf.set(      new ItemStack(materialLeaf, 1, MaterialLeafLoader.thunderFlower.getId()));
        CropsNHItemList.tineTwig.set(         new ItemStack(materialLeaf, 1, MaterialLeafLoader.tineTwig.getId()));
        CropsNHItemList.titaniaLeaf.set(      new ItemStack(materialLeaf, 1, MaterialLeafLoader.titaniaLeaf.getId()));
        CropsNHItemList.uuaBerry.set(         new ItemStack(materialLeaf, 1, MaterialLeafLoader.uuaBerry.getId()));
        CropsNHItemList.uumBerry.set(         new ItemStack(materialLeaf, 1, MaterialLeafLoader.uumBerry.getId()));
        CropsNHItemList.saltyRoot.set(        new ItemStack(materialLeaf, 1, MaterialLeafLoader.saltyRoot.getId()));
        CropsNHItemList.plumbiliaLeaf.set(    new ItemStack(materialLeaf, 1, MaterialLeafLoader.plumbiliaLeaf.getId()));
        CropsNHItemList.argentiaLeaf.set(     new ItemStack(materialLeaf, 1, MaterialLeafLoader.argentiaLeaf.getId()));
        CropsNHItemList.ferruLeaf.set(        new ItemStack(materialLeaf, 1, MaterialLeafLoader.ferruLeaf.getId()));
        CropsNHItemList.aureliaLeaf.set(      new ItemStack(materialLeaf, 1, MaterialLeafLoader.aureliaLeaf.getId()));
        CropsNHItemList.teaLeaf.set(          new ItemStack(materialLeaf, 1, MaterialLeafLoader.teaLeaf.getId()));
        CropsNHItemList.bobsYerUncleBerry.set(new ItemStack(materialLeaf, 1, MaterialLeafLoader.bobsYerUncleBerry.getId()));
        CropsNHItemList.starwart.set(         new ItemStack(materialLeaf, 1, MaterialLeafLoader.starwart.getId()));
        CropsNHItemList.hops.set(             new ItemStack(materialLeaf, 1, MaterialLeafLoader.hops.getId()));
        //spotless:on
        LogHelper.debug("Items Registered");
    }
}
