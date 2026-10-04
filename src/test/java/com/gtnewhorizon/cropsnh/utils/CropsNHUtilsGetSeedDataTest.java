package com.gtnewhorizon.cropsnh.utils;

import static com.gtnewhorizon.cropsnh.BeforeAllHook.CROP_ALT_SEED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.CROP_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.CROP_NOT_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STACK_ALT_SEED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STACK_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STACK_NOT_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STATS_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STATS_NOT_ANALYZED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class CropsNHUtilsGetSeedDataTest {

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
        assertEquals(SeedStats.DEFAULT_ANALYZED_READONLY, seedData.getStats());
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
