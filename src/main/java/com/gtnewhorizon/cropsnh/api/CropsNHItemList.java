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
    sulfurDopedGalvaniaResidue,
    sulfurDopedPlumbiliaResidue,

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
    thiosulfineFlower,

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

    private ItemStack stack;
    private boolean hasNotBeenSet;
    private boolean deprecated;
    private boolean warned;

    CropsNHItemList() {
        this.hasNotBeenSet = true;
    }

    CropsNHItemList(boolean deprecated) {
        if (deprecated) {
            this.deprecated = true;
            this.hasNotBeenSet = true;
        }
    }

    @Override
    public IItemContainer set(Item item) {
        this.hasNotBeenSet = false;
        if (item == null) return this;
        ItemStack stack = new ItemStack(item, 1, 0);
        this.stack = CropsNHUtils.copyStackWithSize(stack, 1);
        return this;
    }

    @Override
    public IItemContainer set(ItemStack stack) {
        this.hasNotBeenSet = false;
        this.stack = CropsNHUtils.copyStackWithSize(stack, 1);
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
        if (GTUtility.isStackInvalid(this.stack)) return null;
        return this.stack.getItem();
    }

    @Override
    public Block getBlock() {
        sanityCheck();
        return GTUtility.getBlockFromItem(getItem());
    }

    @Override
    public final boolean hasBeenSet() {
        return !this.hasNotBeenSet;
    }

    @Override
    public boolean isStackEqual(Object stack) {
        return isStackEqual(stack, false, false);
    }

    @Override
    public boolean isStackEqual(Object stack, boolean wildcard, boolean ignoreNBT) {
        if (this.deprecated && !this.warned) {
            new Exception(this + " is now deprecated").printStackTrace(GTLog.err);
            // warn only once
            this.warned = true;
        }
        if (GTUtility.isStackInvalid(stack)) return false;
        return GTUtility.areUnificationsEqual((ItemStack) stack, wildcard ? getWildcard(1) : get(1), ignoreNBT);
    }

    @Override
    public ItemStack get(long amount, Object... replacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(this.stack)) {
            GTLog.out.println("Object in the ItemList is null at:");
            new NullPointerException().printStackTrace(GTLog.out);
            return GTUtility.copyAmount(amount, replacements);
        }
        return CropsNHUtils.copyStackWithSize(this.stack, (int) amount);
    }

    @Override
    public ItemStack getWildcard(long amount, Object... replacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(this.stack)) return GTUtility.copyAmount(amount, replacements);
        return GTUtility.copyAmountAndMetaData(amount, WILDCARD, GTOreDictUnificator.get(this.stack));
    }

    @Override
    public ItemStack getUndamaged(long amount, Object... replacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(this.stack)) return GTUtility.copyAmount(amount, replacements);
        return GTUtility.copyAmountAndMetaData(amount, 0, GTOreDictUnificator.get(this.stack));
    }

    @Override
    public ItemStack getAlmostBroken(long amount, Object... replacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(this.stack)) return GTUtility.copyAmount(amount, replacements);
        return GTUtility
            .copyAmountAndMetaData(amount, this.stack.getMaxDamage() - 1, GTOreDictUnificator.get(this.stack));
    }

    @Override
    public ItemStack getWithName(long amount, String displayName, Object... replacements) {
        ItemStack stack = get(1, replacements);
        if (GTUtility.isStackInvalid(stack)) return NI;

        // CamelCase alphanumeric words from aDisplayName
        StringBuilder camelCasedDisplayNameBuilder = new StringBuilder();
        final String[] displayNameWords = displayName.split("\\W");
        for (String word : displayNameWords) {
            if (!word.isEmpty()) camelCasedDisplayNameBuilder.append(
                word.substring(0, 1)
                    .toUpperCase(Locale.US));
            if (word.length() > 1) camelCasedDisplayNameBuilder.append(
                word.substring(1)
                    .toLowerCase(Locale.US));
        }
        if (camelCasedDisplayNameBuilder.length() == 0) {
            // CamelCased DisplayName is empty, so use hash of aDisplayName
            camelCasedDisplayNameBuilder.append(((Long) (long) displayName.hashCode()));
        }

        // Construct a translation key from UnlocalizedName and CamelCased DisplayName
        final String key = stack.getUnlocalizedName() + ".with." + camelCasedDisplayNameBuilder + ".name";

        stack.setStackDisplayName(StatCollector.translateToLocalFormatted(key, displayName));
        return CropsNHUtils.copyStackWithSize(stack, (int) amount);
    }

    @Override
    public ItemStack getWithCharge(long amount, int energy, Object... replacements) {
        ItemStack stack = get(1, replacements);
        if (GTUtility.isStackInvalid(stack)) return null;
        GTModHandler.chargeElectricItem(stack, energy, Integer.MAX_VALUE, true, false);
        return CropsNHUtils.copyStackWithSize(stack, (int) amount);
    }

    @Override
    public ItemStack getWithDamage(long amount, long metaValue, Object... replacements) {
        sanityCheck();
        if (GTUtility.isStackInvalid(this.stack)) return GTUtility.copyAmount(amount, replacements);
        return GTUtility.copyAmountAndMetaData(amount, metaValue, GTOreDictUnificator.get(this.stack));
    }

    @Override
    public IItemContainer registerOre(Object... oreNames) {
        sanityCheck();
        for (Object oreName : oreNames) GTOreDictUnificator.registerOre(oreName, get(1));
        return this;
    }

    @Override
    public IItemContainer registerWildcardAsOre(Object... oreNames) {
        sanityCheck();
        for (Object tOreName : oreNames) GTOreDictUnificator.registerOre(tOreName, getWildcard(1));
        return this;
    }

    /**
     * Returns the internal stack. This method is unsafe. It's here only for quick operations. DON'T CHANGE THE RETURNED
     * VALUE!
     */
    public ItemStack getInternalStack_unsafe() {
        return this.stack;
    }

    @Override
    public IItemContainer setRender(IItemRenderer renderer) {
        if (GTMod.proxy.isClientSide()) {
            GTMod.clientProxy().metaItemRenderer
                .registerSpecialRenderer(this.getItem(), this.getInternalStack_unsafe(), renderer);
        }
        return this;
    }

    private void sanityCheck() {
        if (this.hasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (this.deprecated && !this.warned) {
            new Exception(this + " is now deprecated").printStackTrace(GTLog.err);
            // warn only once
            this.warned = true;
        }
    }

}
