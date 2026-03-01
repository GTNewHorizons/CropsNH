package com.gtnewhorizon.cropsnh.api;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidPotencyRegistry {

    void register(Fluid fluid, int potency);

    boolean isRegistered(FluidStack stack);

    boolean isRegistered(Fluid fluid);

    int getPotency(FluidStack stack);

    int getPotency(Fluid fluid);
}
