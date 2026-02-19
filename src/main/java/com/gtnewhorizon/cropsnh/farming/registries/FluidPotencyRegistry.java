package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.IFluidPotencyRegistry;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;

public class FluidPotencyRegistry implements IFluidPotencyRegistry {

    /**
     * A list of fertilizers along with their potency.
     */
    public Map<Fluid, Integer> registry = new IdentityHashMap<>();

    @Override
    public void register(Fluid fluid, int potency) {
        if (potency <= 0) throw new IllegalArgumentException("potency must be greater then 0");
        this.registry.putIfAbsent(fluid, potency);
    }

    @Override
    public boolean isRegistered(FluidStack stack) {
        if (stack == null) return false;
        Fluid fluid = stack.getFluid();
        if (fluid == null) return false;
        return this.registry.containsKey(fluid);
    }

    @Override
    public boolean isRegistered(Fluid item) {
        if (item == null) return false;
        return this.registry.containsKey(item);
    }

    @Override
    public int getPotency(FluidStack stack) {
        if (stack == null) return 0;
        Fluid fluid = stack.getFluid();
        if (fluid == null) return 0;
        return this.registry.getOrDefault(fluid, 0);
    }

    @Override
    public int getPotency(Fluid fluid) {
        return this.registry.getOrDefault(fluid, 0);
    }

    public String dumpCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugHelper.makeCSVLine("Potency", "Fluid"));
        sb.append(System.lineSeparator());
        sb.append(
            this.registry.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(
                    e -> DebugHelper.makeCSVLine(
                        e.getValue(),
                        e.getKey()
                            .getName()))
                .collect(Collectors.joining(System.lineSeparator())));
        return sb.toString();
    }
}
