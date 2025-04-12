package com.gtnewhorizon.cropsnh.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeBuilder;

public class CropOreDuplicationRecipe {

    /** The material of the ore to allow the duplication of. */
    public final ItemStack input;
    public final ItemStack output;
    /** Additional items used as inputs for the conversion. */
    public final FluidStack additionalFluid;

    public CropOreDuplicationRecipe(Materials ore) {
        this(ore, null, 0);
    }

    public CropOreDuplicationRecipe(Materials ore, Materials additionalFluid) {
        this(ore, additionalFluid, 144);
    }

    public CropOreDuplicationRecipe(Materials ore, Materials additionalFluid, int amount) {
        if (ore == null) {
            throw new IllegalArgumentException("mat is null");
        }
        this.input = GTOreDictUnificator.get(OrePrefixes.crushed, ore, 1);
        if (this.input == null) {
            throw new IllegalArgumentException("mat doesn't have a crushed ore form.");
        }
        this.output = GTOreDictUnificator.get(OrePrefixes.crushedPurified, ore, 4);
        if (this.output == null) {
            throw new IllegalArgumentException("mat doesn't have a purified crushed ore form.");
        }
        FluidStack _additionalFluid = null;
        if (additionalFluid != null && amount > 0) {
            _additionalFluid = additionalFluid.getMolten(amount);
            if (_additionalFluid == null) {
                _additionalFluid = additionalFluid.getFluid(amount);
                if (_additionalFluid == null) {
                    _additionalFluid = additionalFluid.getGas(amount);
                    if (_additionalFluid == null) {
                        throw new IllegalArgumentException("Additional fluid material doesn't have a fluid.");
                    }
                }
            }
        } else if (!ore.mOreByProducts.isEmpty()) {
            _additionalFluid = ore.mOreByProducts.get(0)
                .getMolten(amount);
        }
        this.additionalFluid = _additionalFluid;
    }

    public GTRecipeBuilder getBuilder(ItemStack leafStack) {
        GTRecipeBuilder builder = GTValues.RA.stdBuilder()
            .itemInputs(leafStack, this.input);
        if (this.output != null) builder.itemOutputs(this.output);
        if (this.additionalFluid != null) builder.fluidOutputs(this.additionalFluid);
        return builder;
    }
}
