package com.gtnewhorizon.cropsnh.api;

import static gregtech.api.enums.GTValues.NI;
import static gregtech.api.util.GTRecipeBuilder.WILDCARD;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;

import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import gregtech.GTMod;
import gregtech.api.interfaces.IItemContainer;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;

public enum CropsNHItemList implements IItemContainer {

    // TOOLS
    plantLens,
    spade,
    reinforcedSpade,
    cropSticks,

    fertilizer,
    poisonPowder,
    hempHurd,
    enrichedFertilizerCell,
    fertilizerCell,
    plantCure,
    weedEX,

    emptyMug,
    coldCoffeeMug,
    darkCoffeeMug,
    coffeeMug,

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

    SeedGenerator_LV,
    SeedGenerator_MV,
    SeedGenerator_HV,
    SeedGenerator_EV,
    SeedGenerator_IV,
    SeedGenerator_LuV,
    SeedGenerator_ZPM,
    SeedGenerator_UV,
    SeedGenerator_UHV,
    SeedGenerator_UEV,
    SeedGenerator_UIV,
    SeedGenerator_UMV,

    CropBreeder_LV,
    CropBreeder_MV,
    CropBreeder_HV,
    CropBreeder_EV,
    CropBreeder_IV,
    CropBreeder_LuV,
    CropBreeder_ZPM,
    CropBreeder_UV,
    CropBreeder_UHV,
    CropBreeder_UEV,
    CropBreeder_UIV,
    CropBreeder_UMV,

    CropGeneExtractor_EV,
    CropGeneExtractor_IV,
    CropGeneExtractor_LuV,
    CropGeneExtractor_ZPM,
    CropGeneExtractor_UV,
    CropGeneExtractor_UHV,
    CropGeneExtractor_UEV,
    CropGeneExtractor_UIV,
    CropGeneExtractor_UMV,

    CropSynthesizer_EV,
    CropSynthesizer_IV,
    CropSynthesizer_LuV,
    CropSynthesizer_ZPM,
    CropSynthesizer_UV,
    CropSynthesizer_UHV,
    CropSynthesizer_UEV,
    CropSynthesizer_UIV,
    CropSynthesizer_UMV,

    // industrial farm
    IndustrialFarmController,

    // industrial farm components
    BrickedAgriculturalCasing,
    SeedBed_MV,
    SeedBed_HV,
    SeedBed_EV,
    SeedBed_IV,
    SeedBed_LuV,
    SeedBed_ZPM,
    SeedBed_UV,
    SeedBed_UHV,
    SeedBed_UEV,
    SeedBed_UIV,
    SeedBed_UMV,
    SeedBed_UXV,

    // industrial farm upgrades
    EnvironmentalEnhancementUnit_MV,
    EnvironmentalEnhancementUnit_HV,
    EnvironmentalEnhancementUnit_EV,
    EnvironmentalEnhancementUnit_IV,
    EnvironmentalEnhancementUnit_LuV,
    EnvironmentalEnhancementUnit_ZPM,
    EnvironmentalEnhancementUnit_UV,
    EnvironmentalEnhancementUnit_UHV,
    EnvironmentalEnhancementUnit_UEV,
    EnvironmentalEnhancementUnit_UIV,
    EnvironmentalEnhancementUnit_UMV,
    EnvironmentalEnhancementUnit_UXV,

    // industrial farm upgrades
    GrowthAccelerationUnit_MV,
    GrowthAccelerationUnit_HV,
    GrowthAccelerationUnit_EV,
    GrowthAccelerationUnit_IV,
    GrowthAccelerationUnit_LuV,
    GrowthAccelerationUnit_ZPM,
    GrowthAccelerationUnit_UV,
    GrowthAccelerationUnit_UHV,
    GrowthAccelerationUnit_UEV,
    GrowthAccelerationUnit_UIV,
    GrowthAccelerationUnit_UMV,
    GrowthAccelerationUnit_UXV,

    // industrial farm upgrades
    FertilizerUnit_MV,
    FertilizerUnit_HV,
    FertilizerUnit_EV,
    FertilizerUnit_IV,
    FertilizerUnit_LuV,
    FertilizerUnit_ZPM,
    FertilizerUnit_UV,
    FertilizerUnit_UHV,
    FertilizerUnit_UEV,
    FertilizerUnit_UIV,
    FertilizerUnit_UMV,
    FertilizerUnit_UXV,

    // industrial farm upgrades
    AdvancedHarvestingUnit_MV,
    AdvancedHarvestingUnit_HV,
    AdvancedHarvestingUnit_EV,
    AdvancedHarvestingUnit_IV,
    AdvancedHarvestingUnit_LuV,
    AdvancedHarvestingUnit_ZPM,
    AdvancedHarvestingUnit_UV,
    AdvancedHarvestingUnit_UHV,
    AdvancedHarvestingUnit_UEV,
    AdvancedHarvestingUnit_UIV,
    AdvancedHarvestingUnit_UMV,
    AdvancedHarvestingUnit_UXV,

    // industrial farm upgrades
    OverclockedGrowthAccelerationUnit_ZPM,
    OverclockedGrowthAccelerationUnit_UV,
    OverclockedGrowthAccelerationUnit_UHV,
    OverclockedGrowthAccelerationUnit_UEV,
    OverclockedGrowthAccelerationUnit_UIV,
    OverclockedGrowthAccelerationUnit_UMV,
    OverclockedGrowthAccelerationUnit_UXV,

    // food crops
    huckleBerry,
    sugarBeet,
    maxTomato,
    goldfish,
    gaiaWart,

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
    canolaFlower,
    copponFiber,
    galvaniaLeaf,
    indigoBlossom,
    iridineFlower,
    magicEssence,
    micadiaFlower,
    milkWart,
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
    ferrofernLeaf,
    auroniaLeaf,
    bobsYerUncleBerry,
    starWart,
    hops,
    hempStem,
    rubyneLeaf,

    environmentalModule_base,
    environmentalModule_HOT,
    environmentalModule_COLD,
    environmentalModule_SPARSE,
    environmentalModule_DENSE,
    environmentalModule_WET,
    environmentalModule_DRY,
    environmentalModule_SAVANNA,
    environmentalModule_CONIFEROUS,
    environmentalModule_JUNGLE,
    environmentalModule_SPOOKY,
    environmentalModule_DEAD,
    environmentalModule_LUSH,
    environmentalModule_NETHER,
    environmentalModule_END,
    environmentalModule_MUSHROOM,
    environmentalModule_MAGICAL,
    environmentalModule_OCEAN,
    environmentalModule_RIVER,
    environmentalModule_MESA,
    environmentalModule_FOREST,
    environmentalModule_PLAINS,
    environmentalModule_MOUNTAIN,
    environmentalModule_HILLS,
    environmentalModule_SWAMP,
    environmentalModule_SANDY,
    environmentalModule_SNOWY,
    environmentalModule_WASTELAND,
    environmentalModule_BEACH;

    private ItemStack mStack;
    private boolean mHasNotBeenSet;
    private boolean mDeprecated;
    private boolean mWarned;

    CropsNHItemList() {
        mHasNotBeenSet = true;
    }

    CropsNHItemList(boolean aDeprecated) {
        if (aDeprecated) {
            mDeprecated = true;
            mHasNotBeenSet = true;
        }
    }

    @Override
    public IItemContainer set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) return this;
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = CropsNHUtils.copyStackWithSize(aStack, 1);
        return this;
    }

    @Override
    public IItemContainer set(ItemStack aStack) {
        mHasNotBeenSet = false;
        mStack = CropsNHUtils.copyStackWithSize(aStack, 1);
        return this;
    }

    @Override
    public IItemContainer hidden() {
        codechicken.nei.api.API.hideItem(get(1L));
        return this;
    }

    @Override
    public Item getItem() {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) return null;
        return mStack.getItem();
    }

    @Override
    public Block getBlock() {
        sanityCheck();
        return GTUtility.getBlockFromItem(getItem());
    }

    @Override
    public final boolean hasBeenSet() {
        return !mHasNotBeenSet;
    }

    @Override
    public boolean isStackEqual(Object aStack) {
        return isStackEqual(aStack, false, false);
    }

    @Override
    public boolean isStackEqual(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
        if (mDeprecated && !mWarned) {
            new Exception(this + " is now deprecated").printStackTrace(GTLog.err);
            // warn only once
            mWarned = true;
        }
        if (GTUtility.isStackInvalid(aStack)) return false;
        return GTUtility.areUnificationsEqual((ItemStack) aStack, aWildcard ? getWildcard(1) : get(1), aIgnoreNBT);
    }

    @Override
    public ItemStack get(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) {
            GTLog.out.println("Object in the ItemList is null at:");
            new NullPointerException().printStackTrace(GTLog.out);
            return GTUtility.copyAmount(aAmount, aReplacements);
        }
        return CropsNHUtils.copyStackWithSize(mStack, (int) aAmount);
    }

    @Override
    public ItemStack getWildcard(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) return GTUtility.copyAmount(aAmount, aReplacements);
        return GTUtility.copyAmountAndMetaData(aAmount, WILDCARD, GTOreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getUndamaged(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) return GTUtility.copyAmount(aAmount, aReplacements);
        return GTUtility.copyAmountAndMetaData(aAmount, 0, GTOreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getAlmostBroken(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) return GTUtility.copyAmount(aAmount, aReplacements);
        return GTUtility.copyAmountAndMetaData(aAmount, mStack.getMaxDamage() - 1, GTOreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWithName(long aAmount, String aDisplayName, Object... aReplacements) {
        ItemStack rStack = get(1, aReplacements);
        if (GTUtility.isStackInvalid(rStack)) return NI;

        // CamelCase alphanumeric words from aDisplayName
        StringBuilder tCamelCasedDisplayNameBuilder = new StringBuilder();
        final String[] tDisplayNameWords = aDisplayName.split("\\W");
        for (String tWord : tDisplayNameWords) {
            if (!tWord.isEmpty()) tCamelCasedDisplayNameBuilder.append(
                tWord.substring(0, 1)
                    .toUpperCase(Locale.US));
            if (tWord.length() > 1) tCamelCasedDisplayNameBuilder.append(
                tWord.substring(1)
                    .toLowerCase(Locale.US));
        }
        if (tCamelCasedDisplayNameBuilder.length() == 0) {
            // CamelCased DisplayName is empty, so use hash of aDisplayName
            tCamelCasedDisplayNameBuilder.append(((Long) (long) aDisplayName.hashCode()));
        }

        // Construct a translation key from UnlocalizedName and CamelCased DisplayName
        final String tKey = rStack.getUnlocalizedName() + ".with." + tCamelCasedDisplayNameBuilder + ".name";

        rStack.setStackDisplayName(StatCollector.translateToLocalFormatted(tKey, aDisplayName));
        return CropsNHUtils.copyStackWithSize(rStack, (int) aAmount);
    }

    @Override
    public ItemStack getWithCharge(long aAmount, int aEnergy, Object... aReplacements) {
        ItemStack rStack = get(1, aReplacements);
        if (GTUtility.isStackInvalid(rStack)) return null;
        GTModHandler.chargeElectricItem(rStack, aEnergy, Integer.MAX_VALUE, true, false);
        return CropsNHUtils.copyStackWithSize(rStack, (int) aAmount);
    }

    @Override
    public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) return GTUtility.copyAmount(aAmount, aReplacements);
        return GTUtility.copyAmountAndMetaData(aAmount, aMetaValue, GTOreDictUnificator.get(mStack));
    }

    @Override
    public IItemContainer registerOre(Object... aOreNames) {
        sanityCheck();
        for (Object tOreName : aOreNames) GTOreDictUnificator.registerOre(tOreName, get(1));
        return this;
    }

    @Override
    public IItemContainer registerWildcardAsOre(Object... aOreNames) {
        sanityCheck();
        for (Object tOreName : aOreNames) GTOreDictUnificator.registerOre(tOreName, getWildcard(1));
        return this;
    }

    /**
     * Returns the internal stack. This method is unsafe. It's here only for quick operations. DON'T CHANGE THE RETURNED
     * VALUE!
     */
    public ItemStack getInternalStack_unsafe() {
        return mStack;
    }

    @Override
    public IItemContainer setRender(IItemRenderer aRenderer) {
        if (GTMod.proxy.isClientSide()) {
            GTMod.clientProxy().metaItemRenderer
                .registerSpecialRenderer(this.getItem(), this.getInternalStack_unsafe(), aRenderer);
        }
        return this;
    }

    private void sanityCheck() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (mDeprecated && !mWarned) {
            new Exception(this + " is now deprecated").printStackTrace(GTLog.err);
            // warn only once
            mWarned = true;
        }
    }

}
