package com.gtnewhorizon.cropsnh.test.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

// needs the fluid registry to be online in order to function correctly
public class CropsNHUtilsFluidStackValidityTest {

    private static final FluidStack FLUID_NULL = null;
    private static FluidStack FLUID_GOOD;

    @BeforeAll
    public static void beforeAll() {
        FLUID_GOOD = new FluidStack(FluidRegistry.WATER, 1);
    }

    // region isStackValid

    @Test
    public void isStackValid_nullFluidStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValid(FLUID_NULL));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValid_FluidStackWithZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackValid(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackValid_ValidFluidStackIsValid() {
        assertTrue(CropsNHUtils.isStackValid(FLUID_GOOD));
    }

    // endregion isStackValid

    // region isStackInvalid

    @Test
    public void isStackInvalid_nullFluidStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalid(FLUID_NULL));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalid_FluidStackWithZeroOrLessIsInvalid(int count) {
        assertTrue(CropsNHUtils.isStackInvalid(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackInvalid_ValidFluidStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalid(FLUID_GOOD));
    }

    // endregion isStackInvalid

    // region isStackValidIgnoreStackSize

    @Test
    public void isStackValidIgnoreStackSize_nullFluidStackIsInvalid() {
        assertFalse(CropsNHUtils.isStackValidIgnoreStackSize(FLUID_NULL));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackValidIgnoreStackSize_FluidStackWithZeroOrLessIsValid(int count) {
        assertTrue(CropsNHUtils.isStackValidIgnoreStackSize(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackValidIgnoreStackSize_ValidFluidStackIsValid() {
        assertTrue(CropsNHUtils.isStackValidIgnoreStackSize(FLUID_GOOD));
    }

    // endregion isStackValidIgnoreStackSize

    // region isStackInvalidIgnoreStackSize

    @Test
    public void isStackInvalidIgnoreStackSize_nullFluidStackIsInvalid() {
        assertTrue(CropsNHUtils.isStackInvalidIgnoreStackSize(FLUID_NULL));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void isStackInvalidIgnoreStackSize_FluidStackWithZeroOrLessIsInvalid(int count) {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(new FluidStack(FluidRegistry.WATER, count)));
    }

    @Test
    public void isStackInvalidIgnoreStackSize_ValidFluidStackIsValid() {
        assertFalse(CropsNHUtils.isStackInvalidIgnoreStackSize(FLUID_GOOD));
    }

    // endregion isStackInvalidIgnoreStackSize

}
