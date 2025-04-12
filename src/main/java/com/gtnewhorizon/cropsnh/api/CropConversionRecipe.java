package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.Materials;

public class CropConversionRecipe {

    /** An override for the amount of catalyst to use, set to 0 or -1 to remove all catalyst from the recipe. */
    public Integer catalystAmountOverride = null;
    /** An override for the eu recipe tier to use. */
    public Integer tierOverride = null;
    /** An override for which catalyst to use. */
    public FluidStack catalystFluidOverride = null;
    /** Additional items used as inputs for the conversion */
    public final ArrayList<ItemStack> itemInputs = new ArrayList<>();
    /** Additional items used as inputs for the conversion */
    public final ArrayList<FluidStack> fluidInputs = new ArrayList<>();
    /** Items to output for the catalyst */
    public final ArrayList<ItemStack> itemOutputs = new ArrayList<>();
    /** Fluids to output for the catalyst */
    public final ArrayList<FluidStack> fluidOutputs = new ArrayList<>();

    public CropConversionRecipe withCatalystUsage(int amount) {
        this.catalystAmountOverride = amount;
        return this;
    }

    public CropConversionRecipe withFluidInput(Materials mat, long amount) {
        fluidOutputs.add(mat.getFluid(amount));
        return this;
    }

    public CropConversionRecipe withFluidOutput(Materials mat, long amount) {
        fluidOutputs.add(mat.getFluid(amount));
        return this;
    }
}
