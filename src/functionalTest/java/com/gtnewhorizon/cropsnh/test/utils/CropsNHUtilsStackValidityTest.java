package com.gtnewhorizon.cropsnh.test.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

public class CropsNHUtilsStackValidityTest {

    private static final ItemStack ITEM_NULL = null;
    private static final FluidStack FLUID_NULL = null;
    private static final ItemStack ITEM_ITEM_BAD_COUNT_GOOD = new ItemStack((Item) null, 1, 0);
    private static final FluidStack FLUID_FLUID_BAD_COUNT_GOOD = new FluidStack((Fluid) null, 1);
    private static ItemStack ITEM_GOOD;
    private static FluidStack FLUID_GOOD;

    @BeforeAll
    public static void beforeAll() {
        ITEM_GOOD = new ItemStack(Items.feather, 1, 0);
        FLUID_GOOD = new FluidStack(FluidRegistry.WATER, 1);
    }

    // region isStackValid

    @Test
    public void isStackValid_nullItemStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(ITEM_NULL));
    }

    @Test
    public void isStackValid_nullFluidStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(FLUID_NULL));
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
        assertFalse(CropsNHUtils.isStackValid(new ItemStack(Items.feather, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValid_FluidStackWithZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackValid(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackValid_ValidItemStackIsValid() {
        assertTrue(CropsNHUtils.isStackValid(ITEM_GOOD));
    }

    @Test
    public void isStackValid_ValidFluidStackIsValid() {
        assertTrue(CropsNHUtils.isStackValid(FLUID_GOOD));
    }

    // endregion isStackValid

    // region isStackInvalid

    @Test
    public void isStackInvalid_nullItemStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalid(ITEM_NULL));
    }

    @Test
    public void isStackInvalid_nullFluidStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalid(FLUID_NULL));
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
        assertTrue(CropsNHUtils.isStackInvalid(new ItemStack(Items.feather, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalid_FluidStackWithZeroOrLessIsInvalid(int count) {
        assertTrue(CropsNHUtils.isStackInvalid(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackInvalid_ValidItemStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalid(ITEM_GOOD));
    }

    @Test
    public void isStackInvalid_ValidFluidStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalid(FLUID_GOOD));
    }

    // endregion isStackInvalid

    // region isStackValidIgnoreStackSize

    @Test
    public void isStackValidIgnoreStackSize_nullItemStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(ITEM_NULL));
    }

    @Test
    public void isStackValidIgnoreStackSize_nullFluidStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(FLUID_NULL));
    }

    @Test
    public void isStackValidIgnoreStackSize_ItemStackWithNullItemIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(ITEM_ITEM_BAD_COUNT_GOOD));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValidIgnoreStackSize_ItemStackWithNullItemAndZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackValid(new ItemStack((Item) null, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValidIgnoreStackSize_ItemStackWithZeroOrLessIsValid(int count) {
        assertTrue(CropsNHUtils.isStackValid(new ItemStack(Items.feather, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValidIgnoreStackSize_FluidStackWithZeroOrLessIsValid(int count) {
        assertTrue(CropsNHUtils.isStackValid(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackValidIgnoreStackSize_ValidItemStackIsValid() {
        assertTrue(CropsNHUtils.isStackValid(ITEM_GOOD));
    }

    @Test
    public void isStackValidIgnoreStackSize_ValidFluidStackIsValid() {
        assertTrue(CropsNHUtils.isStackValid(FLUID_GOOD));
    }

    // endregion isStackValidIgnoreStackSize

    // region isStackInvalidIgnoreStackSize

    @Test
    public void isStackInvalidIgnoreStackSize_nullItemStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalidIgnoreStackSize(ITEM_NULL));
    }

    @Test
    public void isStackInvalidIgnoreStackSize_nullFluidStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalidIgnoreStackSize(FLUID_NULL));
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
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(new ItemStack(Items.feather, count, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalidIgnoreStackSize_FluidStackWithZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackInvalidIgnoreStackSize_ValidItemStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(ITEM_GOOD));
    }

    @Test
    public void isStackInvalidIgnoreStackSize_ValidFluidStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(FLUID_GOOD));
    }

    // endregion isStackInvalidIgnoreStackSize

}
