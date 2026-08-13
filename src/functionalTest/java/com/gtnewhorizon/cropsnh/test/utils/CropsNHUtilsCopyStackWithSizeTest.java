package com.gtnewhorizon.cropsnh.test.utils;

import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CropsNHUtilsCopyStackWithSizeTest {

    private static int TEST_COUNT = 23;
    private static int TEST_META = 5;
    private static int TEST_INVALID_COUNT = -1;
    private static ItemStack VALID_STACK = null;
    private static ItemStack VALID_STACK_IGNORE_STACKSIZE = null;
    private static ItemStack VALID_STACK_TEST_META = null;
    private static ItemStack VALID_STACK_TEST_META_IGNORE_STACKSIZE = null;

    @BeforeAll
    public void beforeAll() {
        VALID_STACK = new ItemStack(Items.feather, 1, 0);
        VALID_STACK_IGNORE_STACKSIZE = new ItemStack(Items.feather, 1, 0);
        VALID_STACK_TEST_META = new ItemStack(Items.feather, TEST_INVALID_COUNT, TEST_META);
        VALID_STACK_TEST_META_IGNORE_STACKSIZE = new ItemStack(Items.feather, TEST_INVALID_COUNT, TEST_META);
    }


    // region copyStackWithSize

    @Test
    public void copyStackWithSize_NullNotCopied() {
        assertNull(CropsNHUtils.copyStackWithSize(null, 1));
    }

    @Test
    public void copyStackWithSize_StackWithNullItemNotCopied() {
        assertNull(CropsNHUtils.copyStackWithSize(new ItemStack((Item) null, 0, 0), 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void copyStackWithSize_StackWithNullItemAndZeroOrLessNotCopied(int count) {
        assertNull(CropsNHUtils.copyStackWithSize(new ItemStack((Item) null, count, 0), 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void copyStackWithSize_StackWithZeroOrLessNotCopied(int count) {
        assertNull(CropsNHUtils.copyStackWithSize(new ItemStack(Items.feather, count, 0), 1));
    }

    @Test
    public void copyStackWithSize_ValidStackCopied() {
        assertNotNull(CropsNHUtils.copyStackWithSize(new ItemStack(Items.feather, 1, 0), 1));
    }

    @Test
    public void copyStackWithSize_copyHasExpectedItem() {
        final Item expected = Items.feather;
        final ItemStack copied = CropsNHUtils.copyStackWithSize(VALID_STACK, 1);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(TEST_COUNT, copied.stackSize);
    }

    @Test
    public void copyStackWithSize_copyHasExpectedCount() {
        final ItemStack copied = CropsNHUtils.copyStackWithSize(VALID_STACK, TEST_COUNT);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(TEST_COUNT, copied.stackSize);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void copyStackWithSize_copyCanHaveZeroOrLess(int count) {
        final ItemStack copied = CropsNHUtils.copyStackWithSize(VALID_STACK, count);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(count, copied.stackSize);
    }

    @Test
    public void copyStackWithSize_copyHasExpectedMeta() {
        final ItemStack copied = CropsNHUtils.copyStackWithSize(VALID_STACK_TEST_META, 1);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(TEST_META, copied.stackSize);
    }

    @Test
    public void copyStackWithSize_copyHasExpectedNBT() {
        final ItemStack original = VALID_STACK.copy();
        final NBTTagCompound originalTag = new NBTTagCompound();
        originalTag.setBoolean("test_key", true);
        final NBTTagCompound originalNestedTag = new NBTTagCompound();
        originalNestedTag.setString("test_key_nested", "this_is_a_string");
        originalTag.setTag("nested", originalNestedTag);
        original.setTagCompound(originalTag);

        final ItemStack copied = CropsNHUtils.copyStackWithSize(original, 1);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertTrue(copied.hasTagCompound(), "copied should have a compound tag!");
        assertEquals(originalTag, copied.getTagCompound());
    }

    // endregion copyStackWithSize

    // region copyStackWithSizeIgnoreInvalidStackSize

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_NullNotCopied() {
        assertNull(CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(null, 1));
    }

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_StackWithNullItemNotCopied() {
        assertNull(CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(new ItemStack((Item) null, 0, 0), 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void copyStackWithSizeIgnoreInvalidStackSize_StackWithNullItemAndZeroOrLessNotCopied(int count) {
        assertNull(CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(new ItemStack((Item) null, count, 0), 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void copyStackWithSizeIgnoreInvalidStackSize_StackWithZeroOrLessCopied(int count) {
        assertNotNull(CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(VALID_STACK_IGNORE_STACKSIZE, 1));
    }

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_ValidStackCopied() {
        assertNotNull(CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(new ItemStack(Items.feather, 1, 0), 1));
    }

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_copyHasExpectedItem() {
        final Item expected = Items.feather;
        final ItemStack copied = CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(VALID_STACK_IGNORE_STACKSIZE, 1);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(TEST_COUNT, copied.stackSize);
    }

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_copyHasExpectedCount() {
        final ItemStack copied = CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(VALID_STACK_IGNORE_STACKSIZE, TEST_COUNT);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(TEST_COUNT, copied.stackSize);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void copyStackWithSizeIgnoreInvalidStackSize_copyCanHaveZeroOrLess(int count) {
        final ItemStack copied = CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(VALID_STACK_IGNORE_STACKSIZE, count);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(count, copied.stackSize);
    }

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_copyHasExpectedMeta() {
        final ItemStack copied = CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(VALID_STACK_TEST_META_IGNORE_STACKSIZE, 1);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertEquals(TEST_META, copied.stackSize);
    }

    @Test
    public void copyStackWithSizeIgnoreInvalidStackSize_copyHasExpectedNBT() {
        final ItemStack original = VALID_STACK_TEST_META_IGNORE_STACKSIZE.copy();
        final NBTTagCompound originalTag = new NBTTagCompound();
        originalTag.setBoolean("test_key", true);
        final NBTTagCompound originalNestedTag = new NBTTagCompound();
        originalNestedTag.setString("test_key_nested", "this_is_a_string");
        originalTag.setTag("nested", originalNestedTag);
        original.setTagCompound(originalTag);

        final ItemStack copied = CropsNHUtils.copyStackWithSizeIgnoreInvalidStackSize(original, 1);
        Assumptions.assumeTrue(copied != null, "copied item was null, test cannot continue.");
        assertTrue(copied.hasTagCompound(), "copied should have a compound tag!");
        assertEquals(originalTag, copied.getTagCompound());
    }

    // endregion copyStackWithSizeIgnoreInvalidStackSize
}
