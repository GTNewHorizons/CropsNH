package com.gtnewhorizon.cropsnh.farming;

import static com.gtnewhorizon.cropsnh.BeforeAllHook.CROPID_INVALID;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.CROP_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.CROP_NOT_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STACK_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STACK_NOT_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STATS_ANALYZED;
import static com.gtnewhorizon.cropsnh.BeforeAllHook.STATS_NOT_ANALYZED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class SeedDataTest {

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void NBT_SerializationAndDeserialisationPreservesStats(final boolean analyzed) {
        final ICropCard expectedCrop = analyzed ? CROP_ANALYZED : CROP_NOT_ANALYZED;
        final ISeedStats expectedStats = analyzed ? STATS_ANALYZED : STATS_NOT_ANALYZED;
        final SeedData data = new SeedData(new SeedData(expectedCrop, expectedStats).writeToNBT());
        assertEquals(expectedStats, data.getStats());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void NBT_SerializationAndDeserialisationPreservesCrop(final boolean analyzed) {
        final ICropCard expectedCrop = analyzed ? CROP_ANALYZED : CROP_NOT_ANALYZED;
        final ISeedStats expectedStats = analyzed ? STATS_ANALYZED : STATS_NOT_ANALYZED;
        final SeedData data = new SeedData(new SeedData(expectedCrop, expectedStats).writeToNBT());
        assertSame(expectedCrop, data.getCrop());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void NBT_SerializationAndDeserialisationStackSize(final int type) {
        // initialize test parameters
        final int expectedAmount = type % 2 == 0 ? 1 : 64;
        final boolean analyzed = type < 2;
        // create expectations
        final ICropCard expectedCrop = analyzed ? CROP_ANALYZED : CROP_NOT_ANALYZED;
        final ISeedStats expectedStats = analyzed ? STATS_ANALYZED : STATS_NOT_ANALYZED;
        final ItemStack expectedStack = CropsNHUtils
            .copyStackWithSize(analyzed ? STACK_ANALYZED : STACK_NOT_ANALYZED, expectedAmount);
        final SeedData data = new SeedData(new SeedData(expectedCrop, expectedStats, expectedStack).writeToNBT());
        assertEquals(expectedAmount, data.getStack().stackSize, String.format("analyzed: %b", analyzed));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void setAnalyzed_CanAnalyze(final boolean analyzed) {
        final ICropCard expectedCrop = analyzed ? CROP_ANALYZED : CROP_NOT_ANALYZED;
        final ISeedStats expectedStats = analyzed ? STATS_ANALYZED : STATS_NOT_ANALYZED;
        final SeedData data = new SeedData(new SeedData(expectedCrop, expectedStats).writeToNBT());
        data.setAnalyzed(true);
        assertTrue(data.stats.isAnalyzed());
        assertNotNull(CropsNHUtils.getSeedData(data.getStack(), false, true));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void setAnalyzed_CanUnAnalyze(final boolean analyzed) {
        final ICropCard expectedCrop = analyzed ? CROP_ANALYZED : CROP_NOT_ANALYZED;
        final ISeedStats expectedStats = analyzed ? STATS_ANALYZED : STATS_NOT_ANALYZED;
        final SeedData data = new SeedData(new SeedData(expectedCrop, expectedStats).writeToNBT());
        // remove analyzed status
        data.setAnalyzed(false);
        // should not be analyzed past this point
        assertFalse(data.stats.isAnalyzed());
        // the contained stack should resolve appropriately via the getSeedData function
        assertNull(CropsNHUtils.getSeedData(data.getStack(), false, true));
        assertNotNull(CropsNHUtils.getSeedData(data.getStack(), false, false));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7 })
    public void NBT_DefaultsToFallbackCrop(final int type) {
        // initialize test parameters
        final int expectedAmount = type % 2 == 0 ? 1 : 64;
        final boolean analyzed = (type / 2) % 2 == 0;
        final boolean deleteCropTag = type < 4;
        final String statusString = String.format("analyzed: %b | deleteCropTag: %b", analyzed, deleteCropTag);
        // create expectations
        final ICropCard originalCrop = analyzed ? CROP_ANALYZED : CROP_NOT_ANALYZED;
        final ISeedStats expectedStats = analyzed ? STATS_ANALYZED : STATS_NOT_ANALYZED;
        final ItemStack expectedStack = CropsNHUtils
            .copyStackWithSize(analyzed ? STACK_ANALYZED : STACK_NOT_ANALYZED, expectedAmount);
        // serialize and set the id to something invalid
        final NBTTagCompound nbt = new SeedData(originalCrop, expectedStats, expectedStack).writeToNBT();
        if (deleteCropTag) {
            nbt.setString(Names.NBT.crop, CROPID_INVALID);
        } else {
            nbt.removeTag(Names.NBT.crop);
        }
        // check if the deserialized seed data has the same stats and size while using the fallback crop type.
        final SeedData data = new SeedData(nbt);
        // has same stats
        assertEquals(expectedStats, data.getStats(), statusString);
        assertSame(CropsNHUtils.getFallbackCrop(), data.getCrop(), statusString);
        assertEquals(expectedAmount, data.getStack().stackSize, statusString);
    }

}
