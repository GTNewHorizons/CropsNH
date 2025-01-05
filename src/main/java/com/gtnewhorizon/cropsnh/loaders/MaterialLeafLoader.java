package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.farming.materialleaf.CopponFiberVariant;
import com.gtnewhorizon.cropsnh.farming.materialleaf.MaterialLeafVariant;
import com.gtnewhorizon.cropsnh.farming.materialleaf.PyrolusiumLeafVariant;
import com.gtnewhorizon.cropsnh.farming.materialleaf.StargatiumLeafVariant;
import com.gtnewhorizon.cropsnh.items.produce.ItemMaterialLeaf;

public class MaterialLeafLoader {

    // spotless:off
    public final static MaterialLeafVariant bauxiaLeaf        = new MaterialLeafVariant(   0, "bauxiaLeaf",        "aluminium");
    public final static MaterialLeafVariant canolaFLower      = new MaterialLeafVariant(   1, "canolaFLower",      "seedOil");
    public final static MaterialLeafVariant copponFiber       = new CopponFiberVariant(    2, "copponFiber",       "copper");
    public final static MaterialLeafVariant galvaniaLeaf      = new MaterialLeafVariant(   3, "galvaniaLeaf",      "zinc");
    public final static MaterialLeafVariant indigoBlossom     = new MaterialLeafVariant(   4, "indigoBlossom",     "blueDye");
    public final static MaterialLeafVariant iridineFlower     = new MaterialLeafVariant(   5, "iridineFlower",     "iridium");
    public final static MaterialLeafVariant magicEssence      = new MaterialLeafVariant(   6, "magicEssence",      "magic");
    public final static MaterialLeafVariant micadiaFlower     = new MaterialLeafVariant(   7, "micadiaFlower",     "mica");
    public final static MaterialLeafVariant milkwart          = new MaterialLeafVariant(   8, "milkwart",          "milk");
    public final static MaterialLeafVariant nickelbackLeaf    = new MaterialLeafVariant(   9, "nickelbackLeaf",    "nickel");
    public final static MaterialLeafVariant oilBerry          = new MaterialLeafVariant(  10, "oilBerry",          "oil");
    public final static MaterialLeafVariant osmianthFlower    = new MaterialLeafVariant(  11, "osmianthFlower",    "osmium");
    public final static MaterialLeafVariant platinaLeaf       = new MaterialLeafVariant(  12, "platinaLeaf",       "platinum");
    public final static MaterialLeafVariant pyrolusiumLeaf    = new PyrolusiumLeafVariant(13, "pyrolusiumLeaf",    "manganese");
    public final static MaterialLeafVariant reactoriaLeaf     = new MaterialLeafVariant(  14, "reactoriaLeaf",     "uranium");
    public final static MaterialLeafVariant reactoriaStem     = new MaterialLeafVariant(  15, "reactoriaStem",     "uranium");
    public final static MaterialLeafVariant scheeliniumLeaf   = new MaterialLeafVariant(  16, "scheeliniumLeaf",   "tungsten");
    public final static MaterialLeafVariant spaceFlower       = new MaterialLeafVariant(  17, "spaceFlower",       "space");
    public final static MaterialLeafVariant stargatiumLeaf    = new StargatiumLeafVariant(18, "stargatiumLeaf",    "naquadah");
    public final static MaterialLeafVariant thunderFlower     = new MaterialLeafVariant(  19, "thunderFlower",     "thorium");
    public final static MaterialLeafVariant tineTwig          = new MaterialLeafVariant(  20, "tineTwig",          "tin");
    public final static MaterialLeafVariant titaniaLeaf       = new MaterialLeafVariant(  21, "titaniaLeaf",       "titaniaLeaf");
    public final static MaterialLeafVariant uuaBerry          = new MaterialLeafVariant(  22, "uuaBerry",          "uua");
    public final static MaterialLeafVariant uumBerry          = new MaterialLeafVariant(  23, "uumBerry",          "uum");
    public final static MaterialLeafVariant saltyRoot         = new MaterialLeafVariant(  24, "saltyRoot",         "salt");
    public final static MaterialLeafVariant plumbiliaLeaf     = new MaterialLeafVariant(  25, "plumbiliaLeaf",     "lead");
    public final static MaterialLeafVariant argentiaLeaf      = new MaterialLeafVariant(  26, "argentiaLeaf",      "silver");
    public final static MaterialLeafVariant ferruLeaf         = new MaterialLeafVariant(  27, "ferruLeaf",         "iron");
    public final static MaterialLeafVariant aureliaLeaf       = new MaterialLeafVariant(  28, "aureliaLeaf",       "gold");
    public final static MaterialLeafVariant teaLeaf           = new MaterialLeafVariant(  29, "teaLeaf",           "tea");
    public final static MaterialLeafVariant bobsYerUncleBerry = new MaterialLeafVariant(  30, "bobsYerUncleBerry", "emerald");
    //spotless:on

    public static void init() {
        ItemMaterialLeaf.registerVariant(bauxiaLeaf);
        ItemMaterialLeaf.registerVariant(canolaFLower);
        ItemMaterialLeaf.registerVariant(copponFiber);
        ItemMaterialLeaf.registerVariant(galvaniaLeaf);
        ItemMaterialLeaf.registerVariant(indigoBlossom);
        ItemMaterialLeaf.registerVariant(iridineFlower);
        ItemMaterialLeaf.registerVariant(magicEssence);
        ItemMaterialLeaf.registerVariant(micadiaFlower);
        ItemMaterialLeaf.registerVariant(milkwart);
        ItemMaterialLeaf.registerVariant(nickelbackLeaf);
        ItemMaterialLeaf.registerVariant(oilBerry);
        ItemMaterialLeaf.registerVariant(osmianthFlower);
        ItemMaterialLeaf.registerVariant(platinaLeaf);
        ItemMaterialLeaf.registerVariant(pyrolusiumLeaf);
        ItemMaterialLeaf.registerVariant(reactoriaLeaf);
        ItemMaterialLeaf.registerVariant(reactoriaStem);
        ItemMaterialLeaf.registerVariant(scheeliniumLeaf);
        ItemMaterialLeaf.registerVariant(spaceFlower);
        ItemMaterialLeaf.registerVariant(stargatiumLeaf);
        ItemMaterialLeaf.registerVariant(thunderFlower);
        ItemMaterialLeaf.registerVariant(tineTwig);
        ItemMaterialLeaf.registerVariant(titaniaLeaf);
        ItemMaterialLeaf.registerVariant(uuaBerry);
        ItemMaterialLeaf.registerVariant(uumBerry);
        ItemMaterialLeaf.registerVariant(saltyRoot);
        ItemMaterialLeaf.registerVariant(plumbiliaLeaf);
        ItemMaterialLeaf.registerVariant(argentiaLeaf);
        ItemMaterialLeaf.registerVariant(ferruLeaf);
        ItemMaterialLeaf.registerVariant(aureliaLeaf);
        ItemMaterialLeaf.registerVariant(teaLeaf);
        ItemMaterialLeaf.registerVariant(bobsYerUncleBerry);
    }

}
