package com.gtnewhorizon.cropsnh.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.TestCrop;
import com.gtnewhorizon.cropsnh.api.CropCard;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class CropsNHUtilsGetSeedDataTest {

    private static final Item ITEM_ALT_SEED = new Item();
    private static final ItemStack STACK_ALT_SEED = new ItemStack(ITEM_ALT_SEED, 1, 0);
    private static final CropCard CROP_ALT_SEED = new TestCrop("CropsNHUtilsGetSeedDataTest_testing_with_alt_seed");
    private static final CropCard CROP_ANALYZED = new TestCrop("CropsNHUtilsGetSeedDataTest_testing_analyzed");
    private static final SeedStats STATS_ANALYZED = new SeedStats((byte) 10, (byte) 15, (byte) 20, true);
    private static final CropCard CROP_NOT_ANALYZED = new TestCrop("CropsNHUtilsGetSeedDataTest_testing_not_analyzed");
    private static final SeedStats STATS_NOT_ANALYZED = new SeedStats((byte) 11, (byte) 16, (byte) 21, false);
    private static final ItemStack STACK_ANALYZED = CROP_ANALYZED.getSeedItem(STATS_ANALYZED);
    private static final ItemStack STACK_NOT_ANALYZED = CROP_NOT_ANALYZED.getSeedItem(STATS_NOT_ANALYZED);

    @BeforeAll
    public static void beforeAll() {
        CROP_ALT_SEED.addAlternateSeed(STACK_ALT_SEED);
        CropRegistry.instance.register(CROP_ALT_SEED);
        CropRegistry.instance.register(CROP_NOT_ANALYZED);
        CropRegistry.instance.register(CROP_ANALYZED);
    }

    public static Stream<Arguments> altSeedOnlyAnalyzedOnlyPermutations() {
        return Stream
            .of(arguments(false, false), arguments(true, false), arguments(false, true), arguments(true, true));
    }

    // region alt seed

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_RejectsAltSeedWhenAcceptAllowAltSeedIsFalse(boolean analyzedOnly) {
        assertNull(CropsNHUtils.getSeedData(STACK_ALT_SEED, false, analyzedOnly));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_AcceptsAltSeedWhenAcceptAllowAltSeedIsTrue(boolean analyzedOnly) {
        assertNotNull(CropsNHUtils.getSeedData(STACK_ALT_SEED, true, analyzedOnly));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_AcceptedAltSeedHasDefaultAnalyzedStats(boolean analyzedOnly) {
        ISeedData seedData = CropsNHUtils.getSeedData(STACK_ALT_SEED, true, analyzedOnly);
        assertNotNull(seedData, "Seed data should not be null, test cannot proceed");
        assertEquals(SeedStats.DEFAULT_ANALYZED, seedData.getStats());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_AcceptedAltSeedResolvesToExpectedCrop(boolean analyzedOnly) {
        ISeedData seedData = CropsNHUtils.getSeedData(STACK_ALT_SEED, true, analyzedOnly);
        assertNotNull(seedData, "Seed data should not be null, test cannot proceed");
        assertSame(CROP_ALT_SEED, seedData.getCrop());
    }

    // endregion alt seed

    // region not analyzed seed

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_NotAnalyzedGenericSeedIsRejectedWhenAnalyzedOnlyIsTrue(boolean allowAltSeed) {
        assertNull(CropsNHUtils.getSeedData(STACK_NOT_ANALYZED, allowAltSeed, true));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_NotAnalyzedGenericSeedIsAcceptedWhenAnalyzedOnlyIsFalse(boolean allowAltSeed) {
        assertNotNull(CropsNHUtils.getSeedData(STACK_NOT_ANALYZED, allowAltSeed, false));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_NotAnalyzedSeedHasExpectedStats(boolean allowAltSeed) {
        ISeedData seedData = CropsNHUtils.getSeedData(STACK_NOT_ANALYZED, allowAltSeed, false);
        assertNotNull(seedData, "Seed data should not be null, test cannot proceed");
        assertEquals(STATS_NOT_ANALYZED, seedData.getStats());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getSeedData_NotAnalyzedSeedHasExpectedCrop(boolean allowAltSeed) {
        ISeedData seedData = CropsNHUtils.getSeedData(STACK_NOT_ANALYZED, allowAltSeed, false);
        assertNotNull(seedData, "Seed data should not be null, test cannot proceed");
        assertSame(CROP_NOT_ANALYZED, seedData.getCrop());
    }

    // endregion not analyzed seed

    // region analyzed seed

    @ParameterizedTest
    @MethodSource("altSeedOnlyAnalyzedOnlyPermutations")
    public void GetSeedData_AnalyzedGenericSeedIsAlwaysAccepted(boolean allowAltSeeds, boolean analyzedOnly) {
        assertNotNull(CropsNHUtils.getSeedData(STACK_ANALYZED, allowAltSeeds, analyzedOnly));
    }

    @ParameterizedTest
    @MethodSource("altSeedOnlyAnalyzedOnlyPermutations")
    public void getSeedData_AnalyzedSeedHasExpectedStats(boolean allowAltSeed, boolean analyzedOnly) {
        ISeedData seedData = CropsNHUtils.getSeedData(STACK_ANALYZED, allowAltSeed, analyzedOnly);
        assertNotNull(seedData, "Seed data should not be null, test cannot proceed");
        assertEquals(STATS_ANALYZED, seedData.getStats());
    }

    @ParameterizedTest
    @MethodSource("altSeedOnlyAnalyzedOnlyPermutations")
    public void getSeedData_AnalyzedSeedHasExpectedCrop(boolean allowAltSeed, boolean analyzedOnly) {
        ISeedData seedData = CropsNHUtils.getSeedData(STACK_ANALYZED, allowAltSeed, analyzedOnly);
        assertNotNull(seedData, "Seed data should not be null, test cannot proceed");
        assertSame(CROP_ANALYZED, seedData.getCrop());
    }

    // endregion analyzed seed

    @ParameterizedTest
    @MethodSource("altSeedOnlyAnalyzedOnlyPermutations")
    public void getSeedData_nullStackReturnsNullSafely(boolean allowAltSeed, boolean analyzedOnly) {
        assertNull(CropsNHUtils.getSeedData(null, allowAltSeed, analyzedOnly));
    }

}
