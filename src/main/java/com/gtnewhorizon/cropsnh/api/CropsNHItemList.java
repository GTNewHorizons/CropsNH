package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.ItemStack;

public enum CropsNHItemList {

    // TOOLS
    magnifyingGlass,
    spade,
    cropSticks,

    // TODO: CONSIDER MOVING NAN CERTIFICATE TO GT5-U OR NH CORE MOD
    nanCertificate,
    fertilizer,
    enrichedFertilizerCell,

    // MTEs
    CropManager_LV,
    CropManager_MV,
    CropManager_HV,
    CropManager_EV,
    CropManager_IV,
    CropManager_LuV,
    CropManager_ZPM,
    CropManager_UV,
    CropManager_UHV,
    CropManager_UEV,
    CropManager_UIV,
    CropManager_UMV,
    CropManager_UXV,

    // food crops
    huckleBerry,
    sugarBeet,
    goldfish,
    terraWart,

    // Alcohol Bottles
    fermentedWheatBottle,
    kornBottle,
    doppelkornBottle,
    fermentedReedwaterBottle,
    sugarWhineBottle,
    mashBottle,
    washBottle,
    highProofBottle,
    realJagermeisterBottle,
    fakeJagermeisterBottle,

    // material leaves
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
    starwart,
    hops;

    private ItemStack stack = null;

    public void set(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack get(int count) {
        if (stack == null) {
            throw new NullPointerException("CropsNHItemList entry '" + name() + "' accessed before creation!");
        }
        ItemStack ret = this.stack.copy();
        ret.stackSize = count;
        return ret;
    }

}
