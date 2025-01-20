package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.reference.Reference;
import cpw.mods.fml.common.LoaderException;
import gregtech.api.enums.ItemList;
import gregtech.common.fluid.GTFluidBuilder;
import gtPlusPlus.core.util.minecraft.FluidUtils;
import gtPlusPlus.core.util.minecraft.ItemUtils;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class CropsNHFluids {
    public static Fluid enrichedFertilizer;

    public static void preInit() {
        // could probably use a propper GT Material but this is faster
        CropsNHFluids.enrichedFertilizer = FluidUtils.addGTFluidNonMolten(
            "EnrichedFertilizer",
            "Enriched Fertilizer",
            new short[] { 40,229,21,100 },
            4,
            295,
            null,
            ItemUtils.getEmptyCell(),
            1000,
            true);

        CropsNHItemList.enrichedFertilizerCell.set(FluidContainerRegistry.fillFluidContainer(new FluidStack(CropsNHFluids.enrichedFertilizer, 1000), ItemList.Cell_Empty.get(1)));
        try {
            CropsNHItemList.enrichedFertilizerCell.get(1);
        } catch (NullPointerException npe) {
            throw new LoaderException(npe);
        }
    }
}
