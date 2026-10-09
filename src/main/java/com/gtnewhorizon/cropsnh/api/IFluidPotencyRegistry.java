package com.gtnewhorizon.cropsnh.api;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

public interface IFluidPotencyRegistry {

    void register(Fluid fluid, IPotencyData potency);

    boolean isRegistered(FluidStack stack);

    boolean isRegistered(Fluid fluid);

    @Nullable
    IPotencyData getPotency(FluidStack stack);

    @Nullable
    IPotencyData getPotency(Fluid fluid);
}
