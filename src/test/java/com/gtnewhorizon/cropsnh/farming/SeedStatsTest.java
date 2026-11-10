package com.gtnewhorizon.cropsnh.farming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.IntRangeBounds;
import com.gtnewhorizon.cropsnh.IntRangeValueSource;
import com.gtnewhorizon.cropsnh.TestCrop;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.reference.Names;

import junit.framework.AssertionFailedError;

public class SeedStatsTest {

    private static final byte EXPECTED_GROWTH = 10;
    private static final byte EXPECTED_GAIN = 20;
    private static final byte EXPECTED_RESISTANCE = 30;

    private static final byte EXPECTED_GROWTH_OTHER = 5;
    private static final byte EXPECTED_GAIN_OTHER = 15;
    private static final byte EXPECTED_RESISTANCE_OTHER = 25;

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void getGain_correctValue(final boolean analyzed) {
        final SeedStats seedStats = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, analyzed);
        assertEquals(EXPECTED_GROWTH, seedStats.getGrowth());
        assertEquals(EXPECTED_GAIN, seedStats.getGain());
        assertEquals(EXPECTED_RESISTANCE, seedStats.getResistance());
        assertEquals(analyzed, seedStats.isAnalyzed());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void setAnalyzed_canAnalyze(final boolean analyzed) {
        final SeedStats seedStats = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, analyzed);
        seedStats.setAnalyzed(true);
        assertTrue(seedStats.isAnalyzed());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void setAnalyzed_canUnAnalyze(final boolean analyzed) {
        final SeedStats seedStats = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, analyzed);
        seedStats.setAnalyzed(false);
        assertFalse(seedStats.isAnalyzed());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void equals(boolean isAnalyzed) {
        final SeedStats expected = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, isAnalyzed);
        final SeedStats identical = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, isAnalyzed);
        final Object notStats = new Object();
        final SeedStats growthChanged = new SeedStats(
            EXPECTED_GROWTH_OTHER,
            EXPECTED_GAIN,
            EXPECTED_RESISTANCE,
            isAnalyzed);
        final SeedStats gainChanged = new SeedStats(
            EXPECTED_GROWTH,
            EXPECTED_GAIN_OTHER,
            EXPECTED_RESISTANCE,
            isAnalyzed);
        final SeedStats resistChanged = new SeedStats(
            EXPECTED_GROWTH,
            EXPECTED_GAIN,
            EXPECTED_RESISTANCE_OTHER,
            isAnalyzed);
        final SeedStats analyzedChanged = new SeedStats(
            EXPECTED_GROWTH,
            EXPECTED_GAIN,
            EXPECTED_RESISTANCE,
            !isAnalyzed);
        // copy should be good
        assertEquals(expected, identical);
        // should not equal non ISeedStat object
        assertNotEquals(expected, notStats);
        // should not equal when values differ
        assertNotEquals(expected, growthChanged);
        assertNotEquals(expected, gainChanged);
        assertNotEquals(expected, resistChanged);
        assertNotEquals(expected, analyzedChanged);
        // analyzed changed should be equal after flipping the state
        analyzedChanged.setAnalyzed(!analyzedChanged.isAnalyzed());
        assertEquals(analyzedChanged, expected);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void copy_CopyIsEqual(boolean isAnalyzed) {
        final SeedStats expected = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, isAnalyzed);
        assertEquals(expected, expected.copy());
    }

    @Test
    public void copy_IsNotSame() {
        assertNotSame(SeedStats.DEFAULT_ANALYZED_READONLY, SeedStats.DEFAULT_ANALYZED_READONLY.copy());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void nbt_SerializeAndDeserializes(boolean isAnalyzed) {
        final SeedStats expected = new SeedStats(EXPECTED_GROWTH, EXPECTED_GAIN, EXPECTED_RESISTANCE, isAnalyzed);
        final NBTTagCompound nbt = new NBTTagCompound();
        expected.writeToNBT(nbt);
        assertEquals(expected, SeedStats.readFromNBT(nbt));
    }

    @Test
    public void readFromNBT_DefaultsToUnanalyzedWhenParamIsNull() {
        assertEquals(SeedStats.DEFAULT_NOT_ANALYZED_READONLY, SeedStats.readFromNBT(null));
    }

    @Test
    public void readFromNBT_DefaultsToUnanalyzedWhenParamIsEmptyTag() {
        assertEquals(SeedStats.DEFAULT_NOT_ANALYZED_READONLY, SeedStats.readFromNBT(new NBTTagCompound()));
    }

    @Test
    public void readOnlyDefaults_BothUseTheSameStatValues() {
        final SeedStats invertedAnalyzed = SeedStats.DEFAULT_ANALYZED_READONLY.copy();
        final SeedStats invertedNotAnalyzed = SeedStats.DEFAULT_NOT_ANALYZED_READONLY.copy();
        invertedAnalyzed.setAnalyzed(!invertedAnalyzed.isAnalyzed());
        invertedNotAnalyzed.setAnalyzed(!invertedNotAnalyzed.isAnalyzed());
        // inverted should not be equal to old analyze
        assertNotEquals(SeedStats.DEFAULT_ANALYZED_READONLY, invertedAnalyzed);
        assertNotEquals(SeedStats.DEFAULT_NOT_ANALYZED_READONLY, invertedNotAnalyzed);
        // inverted should equal other
        assertEquals(SeedStats.DEFAULT_NOT_ANALYZED_READONLY, invertedAnalyzed);
        assertEquals(SeedStats.DEFAULT_ANALYZED_READONLY, invertedNotAnalyzed);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void analyzedOnlyCtor_UsesSameStatsAsReadOnlyDefaults(boolean isAnalyzed) {
        // Both default analyzed should share the same stats
        final SeedStats expected = isAnalyzed ? SeedStats.DEFAULT_ANALYZED_READONLY
            : SeedStats.DEFAULT_NOT_ANALYZED_READONLY;
        assertEquals(expected, new SeedStats(isAnalyzed));
    }

    @Test
    public void noParamCtor_SameStatsAsDefaultNotAnalyzed() {
        assertEquals(SeedStats.DEFAULT_NOT_ANALYZED_READONLY, new SeedStats());
    }

    @Test
    public void getStatsFromStack_ReturnsNullForNullStack() {
        assertNull(SeedStats.getStatsFromStack(null));
        assertNull(SeedStats.getStatsFromStack(new ItemStack((Item) null, 1, 0)));
    }

    @Test
    public void getStatsFromStack_ReturnsDefaultUnanalyzedWhenNoNBT() {
        assertEquals(new SeedStats(), SeedStats.getStatsFromStack(new ItemStack(TestCrop.ITEM, 1, 0)));
    }

    @Test
    public void getStatsFromStack_ReturnsDefaultWhenNBTEmpty() {
        final ItemStack stack = new ItemStack(TestCrop.ITEM, 1, 0);
        stack.setTagCompound(new NBTTagCompound());
        assertEquals(new SeedStats(), SeedStats.getStatsFromStack(stack));
    }

    /**
     * getStatsFromStack isn't intended to care about the type of item, that's something left to the calling method.
     */
    @Test
    public void getStatsFromStack_DoesNotCareWhatTheItemIs() {
        final ItemStack stack = new ItemStack(new Item(), 1, 0);
        stack.setTagCompound(new NBTTagCompound());
        assertEquals(new SeedStats(), SeedStats.getStatsFromStack(stack));
    }

    /**
     * Also validates the value validity of the getters for all the constructors
     *
     * <dl>
     *
     * <dt>oob type: type % 2</dt>
     * <dd>0. lower bound</dd>
     * <dd>1. upper bound</dd>
     *
     * <dt>stat: type % 3</dt>
     * <dd>0. growth out of bounds</dd>
     * <dd>1. gain out of bounds</dd>
     * <dd>2. resistance out of bounds</dd>
     *
     * <dt>ctor: type / 6</dt>
     * <dd>0. no analyzed param ctor</dd>
     * <dd>1. full ctor, analyzed true</dd>
     * <dd>2. full ctor, analyzed false</dd>
     * <dd>3. nbt, analyzed</dd>
     * <dd>4. nbt, not analyzed</dd>
     * <dd>5. getStatsFromStack, analyzed</dd>
     * <dd>6. getStatsFromStack, not analyzed</dd>
     *
     * </dl>
     */
    @ParameterizedTest
    @ArgumentsSource(IntRangeValueSource.class)
    public void ctorWithStats_ValuesAreClamped(@IntRangeBounds(end = 2 * 3 * 7) int type) {
        final boolean isLowerBound = type % 2 == 0;
        final int stat = type % 3;
        final int ctor = type / 6;

        final byte bounded = isLowerBound ? Constants.MIN_SEED_STAT : Constants.MAX_SEED_STAT;
        final byte outOfBounds = (byte) (bounded + (isLowerBound ? -1 : 1));
        String statusString = "bounded: " + bounded + "| outOfBounds: " + outOfBounds;
        byte gr = EXPECTED_GROWTH, ga = EXPECTED_GAIN, re = EXPECTED_RESISTANCE;
        switch (stat) {
            case 0 -> {
                gr = outOfBounds;
                statusString += " | testedStat: growth";
            }
            case 1 -> {
                ga = outOfBounds;
                statusString += " | testedStat: growth";
            }
            case 2 -> {
                re = outOfBounds;
                statusString += " | testedStat: growth";
            }
        }

        // create stat with out-of-bound values
        SeedStats clamped;
        switch (ctor) {
            case 0 -> {
                statusString += " | ctor: noAnalyzed";
                clamped = new SeedStats(gr, ga, re);
            }
            case 1 -> {
                statusString += " | ctor: analyzedTrue";
                clamped = new SeedStats(gr, ga, re, true);
                // validate is analyzed just in case
                assertTrue(clamped.isAnalyzed());
            }
            case 2 -> {
                statusString += " | ctor: analyzedFalse";
                clamped = new SeedStats(gr, ga, re, false);
                // validate is analyzed just in case
                assertFalse(clamped.isAnalyzed());
            }
            case 3, 4, 5, 6 -> {
                final NBTTagCompound nbt = new NBTTagCompound();
                SeedStats.DEFAULT_ANALYZED_READONLY.writeToNBT(nbt);
                nbt.setByte(Names.NBT.growth, gr);
                nbt.setByte(Names.NBT.gain, ga);
                nbt.setByte(Names.NBT.resistance, re);
                statusString += switch (ctor) {
                    case 3 -> " | ctor: nbtAnalyzed";
                    case 4 -> " | ctor: nbtNotAnalyzed";
                    case 5 -> " | ctor: getStatsFromStackAnalyzed";
                    case 6 -> " | ctor: getStatsFromStackNotAnalyzed";
                    default -> throw new AssertionFailedError("ctor type is unknown! value is: " + ctor);
                };
                // set analyzed if needed
                nbt.setBoolean(Names.NBT.analyzed, ctor == 3 || ctor == 5);
                switch (ctor) {
                    case 3, 5 -> clamped = SeedStats.readFromNBT(nbt);
                    case 4, 6 -> {
                        final ItemStack stack = new ItemStack(TestCrop.ITEM, 1, 0);
                        stack.setTagCompound(nbt);
                        clamped = SeedStats.getStatsFromStack(stack);
                        assertNotNull(clamped, statusString);
                    }
                    default -> throw new AssertionFailedError("ctor type is unknown! value is: " + ctor);
                }
            }
            default -> throw new AssertionFailedError("ctor type is unknown! value is: " + ctor);
        }

        // validate other stats
        switch (stat) {
            case 0 -> {
                assertEquals(bounded, clamped.getGrowth(), statusString);
                assertEquals(ga, clamped.getGain(), statusString);
                assertEquals(re, clamped.getResistance(), statusString);
            }
            case 1 -> {
                assertEquals(gr, clamped.getGrowth(), statusString);
                assertEquals(bounded, clamped.getGain(), statusString);
                assertEquals(re, clamped.getResistance(), statusString);
            }
            default -> {
                assertEquals(gr, clamped.getGrowth(), statusString);
                assertEquals(ga, clamped.getGain(), statusString);
                assertEquals(bounded, clamped.getResistance(), statusString);
            }
        }
    }
}
