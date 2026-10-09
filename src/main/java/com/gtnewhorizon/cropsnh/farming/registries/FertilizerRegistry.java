package com.gtnewhorizon.cropsnh.farming.registries;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.IPotencyData;
import com.gtnewhorizon.cropsnh.api.IPotencyRegistry;

public class FertilizerRegistry implements IPotencyRegistry {

    public static final FluidPotencyRegistry fertilizerUnitInstance = new FluidPotencyRegistry();

    public static final FertilizerRegistry instance = new FertilizerRegistry();

    private final ItemPotencyRegistry itemRegistry = new ItemPotencyRegistry();

    private final FluidPotencyRegistry fluidRegistry = new FluidPotencyRegistry();

    @Override
    public void register(Item item, int meta, IPotencyData potency) {
        this.itemRegistry.register(item, meta, potency);
    }

    @Override
    public boolean isRegistered(ItemStack stack) {
        return this.itemRegistry.isRegistered(stack);
    }

    @Override
    public boolean isRegistered(Item item, int meta) {
        return this.itemRegistry.isRegistered(item, meta);
    }

    @Override
    public @Nullable IPotencyData getPotency(ItemStack stack) {
        return this.itemRegistry.getPotency(stack);
    }

    @Override
    public @Nullable IPotencyData getPotency(Item item, int meta) {
        return this.itemRegistry.getPotency(item, meta);
    }

    @Override
    public void register(Fluid fluid, IPotencyData potency) {
        this.fluidRegistry.register(fluid, potency);
    }

    @Override
    public boolean isRegistered(FluidStack stack) {
        return this.fluidRegistry.isRegistered(stack);
    }

    @Override
    public boolean isRegistered(Fluid fluid) {
        return this.fluidRegistry.isRegistered(fluid);
    }

    @Override
    public @Nullable IPotencyData getPotency(FluidStack stack) {
        return this.fluidRegistry.getPotency(stack);
    }

    @Override
    public @Nullable IPotencyData getPotency(Fluid fluid) {
        return this.fluidRegistry.getPotency(fluid);
    }

    /**
     * @return a csv dump of all the registered fertilizers
     */
    public String dumpItemsCSV() {
        return this.itemRegistry.dumpCSV();
    }

    /**
     * @return a csv dump of all the registered fertilizers
     */
    public String dumpFluidsCSV() {
        return this.fluidRegistry.dumpCSV();
    }
}
