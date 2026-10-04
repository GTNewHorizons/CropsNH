package com.gtnewhorizon.cropsnh.farming.registries;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.IFluidPotencyRegistry;
import com.gtnewhorizon.cropsnh.api.IPotencyData;
import com.gtnewhorizon.cropsnh.utility.DebugHelper;

public class FluidPotencyRegistry implements IFluidPotencyRegistry {

    /**
     * A list of fertilizers along with their potency.
     */
    public Map<Fluid, IPotencyData> registry = new IdentityHashMap<>();

    @Override
    public void register(Fluid fluid, IPotencyData data) {
        if (data.getPotency() <= 0) throw new IllegalArgumentException("potency must be greater then 0");
        if (data.getUnitsConsumedPerApplication() <= 0)
            throw new IllegalArgumentException("Units per application must be greater than 0");
        this.registry.putIfAbsent(fluid, data);
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
    public @Nullable IPotencyData getPotency(FluidStack stack) {
        if (stack == null) return null;
        Fluid fluid = stack.getFluid();
        if (fluid == null) return null;
        return this.registry.getOrDefault(fluid, null);
    }

    @Override
    public @Nullable IPotencyData getPotency(Fluid fluid) {
        return this.registry.getOrDefault(fluid, null);
    }

    public String dumpCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugHelper.makeCSVLine("Fluid", "Potency", "Max Storage", "Consumed per Application"));
        sb.append(System.lineSeparator());
        // Custom comparison logic
        sb.append(
            this.registry.entrySet()
                .stream()
                .sorted(
                    Map.Entry.<Fluid, IPotencyData>comparingByValue()
                        .thenComparing(
                            entry -> entry.getKey()
                                .getName()))
                .sorted(
                    Map.Entry.comparingByValue(
                        Comparator.comparingInt(IPotencyData::getUnitsConsumedPerApplication)
                            .thenComparingInt(IPotencyData::getMaxStorage)
                            .thenComparingInt(IPotencyData::getUnitsConsumedPerApplication)))
                .map(
                    e -> DebugHelper.makeCSVLine(
                        e.getValue(),
                        e.getKey()
                            .getName()))
                .collect(Collectors.joining(System.lineSeparator())));
        return sb.toString();
    }
}
