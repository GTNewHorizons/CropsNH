package com.gtnewhorizon.cropsnh.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class CropsNHUtilsItemStackValidityTest {

    private static final Item ITEM = new Item();
    private static final ItemStack ITEM_NULL = null;
    private static final ItemStack ITEM_ITEM_BAD_COUNT_GOOD = new ItemStack((Item) null, 1, 0);
    private static ItemStack ITEM_GOOD;

    @BeforeAll
    public static void beforeAll() {
        Reference.IS_GAME_LOADED = false;
        ITEM_GOOD = new ItemStack(ITEM, 1, 0);
    }

    // region isStackValid

    @Test
    public void isStackValid_nullItemStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(ITEM_NULL));
    }

    @Test
    public void isStackValid_ItemStackWithNullItemIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(ITEM_ITEM_BAD_COUNT_GOOD));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValid_ItemStackWithNullItemAndZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackValid(new ItemStack((Item) null, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValid_ItemStackWithZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackValid(new ItemStack(ITEM, count, 0)));
    }

    @Test
    public void isStackValid_ValidItemStackIsValid() {
        assertTrue(CropsNHUtils.isStackValid(ITEM_GOOD));
    }

    // endregion isStackValid

    // region isStackInvalid

    @Test
    public void isStackInvalid_nullItemStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalid(ITEM_NULL));
    }

    @Test
    public void isStackInvalid_ItemStackWithNullItemIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalid(ITEM_ITEM_BAD_COUNT_GOOD));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalid_ItemStackWithNullItemAndZeroOrLessIsInvalid(int count) {
        assertTrue(CropsNHUtils.isStackInvalid(new ItemStack((Item) null, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalid_ItemStackWithZeroOrLessIsInvalid(int count) {
        assertTrue(CropsNHUtils.isStackInvalid(new ItemStack(ITEM, count, 0)));
    }

    @Test
    public void isStackInvalid_ValidItemStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalid(ITEM_GOOD));
    }

    // endregion isStackInvalid

    // region isStackValidIgnoreStackSize

    @Test
    public void isStackValidIgnoreStackSize_nullItemStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValidIgnoreStackSize(ITEM_NULL));
    }

    @Test
    public void isStackValidIgnoreStackSize_ItemStackWithNullItemIsInvalid() {
        assertFalse(CropsNHUtils.isStackValidIgnoreStackSize(ITEM_ITEM_BAD_COUNT_GOOD));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValidIgnoreStackSize_ItemStackWithNullItemAndZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackValidIgnoreStackSize(new ItemStack((Item) null, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValidIgnoreStackSize_ItemStackWithZeroOrLessIsValid(int count) {
        assertTrue(CropsNHUtils.isStackValidIgnoreStackSize(new ItemStack(ITEM, count, 0)));
    }

    @Test
    public void isStackValidIgnoreStackSize_ValidItemStackIsValid() {
        assertTrue(CropsNHUtils.isStackValidIgnoreStackSize(ITEM_GOOD));
    }

    // endregion isStackValidIgnoreStackSize

    // region isStackInvalidIgnoreStackSize

    @Test
    public void isStackInvalidIgnoreStackSize_nullItemStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalidIgnoreStackSize(ITEM_NULL));
    }

    @Test
    public void isStackInvalidIgnoreStackSize_ItemStackWithNullItemIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalidIgnoreStackSize(ITEM_ITEM_BAD_COUNT_GOOD));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalidIgnoreStackSize_ItemStackWithNullItemAndZeroOrLessIsInvalid(int count) {
        assertTrue(CropsNHUtils.isStackInvalidIgnoreStackSize(new ItemStack((Item) null, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalidIgnoreStackSize_ItemStackWithZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(new ItemStack(ITEM, count, 0)));
    }

    @Test
    public void isStackInvalidIgnoreStackSize_ValidItemStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(ITEM_GOOD));
    }

    // endregion isStackInvalidIgnoreStackSize

}
