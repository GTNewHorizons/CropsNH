package com.gtnewhorizon.cropsnh.init;

import net.minecraft.item.ItemStack;

public enum CropsNHItemList {

    // TOOLS
    magnifyingGlass,
    spade,
    // TODO: CONSIDER MOVING NAN CERTIFICATE TO GT5-U OR GTNH-COREMOD
    nanCertificate,
    // food crops
    huckleBerry,
    sugarBeet,
    goldfish,
    terraWart,
    // material crops
    bauxiaLeaf,
    canolaFLower,
    copponFiber,
    galvaniaLeaf,
    indigoBlossom,
    iridineFlower,
    magicEssence,
    micadiaFlower,
    milkwart,
    nickelbackLeaf,
    oilBerry,
    osmianthFlower,
    platinaLeaf,
    pyrolusiumLeaf,
    reactoriaLeaf,
    reactoriaStem,
    scheeliniumLeaf,
    spaceFlower,
    stargatiumLeaf,
    thunderLeaf,
    tineTwig,
    titaniaLeaf,
    uuaBerry,
    uumBerry,
    saltyRoot,
    plumbiliaLeaf,
    argentiaLeaf,
    ferruLeaf,
    aureliaLeaf,
    teaLeaf,
    bobsYerUncleBerry,
    starwart;

    private ItemStack stack = null;

    public void set(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack get(int count) {
        if (stack == null) {
            throw new NullPointerException("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        ItemStack ret = this.stack.copy();
        ret.stackSize = count;
        return ret;
    }

}
