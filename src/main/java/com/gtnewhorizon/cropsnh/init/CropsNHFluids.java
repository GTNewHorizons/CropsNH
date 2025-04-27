package com.gtnewhorizon.cropsnh.init;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.fluids.AlcoholImpure;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.enums.ItemList;
import gtPlusPlus.core.util.minecraft.FluidUtils;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class CropsNHFluids {

    public static Fluid enrichedFertilizer;

    // alchool
    public final static AlcoholImpure FWheat = new AlcoholImpure("cropsnh:FWheat", 40, "potion.alcopops");
    public final static AlcoholImpure Korn = new AlcoholImpure("cropsnh:Korn", 320, "potion.vodka");
    public final static AlcoholImpure DKorn = new AlcoholImpure("cropsnh:DKorn", 380, "potion.vodka");
    public final static AlcoholImpure FReed = new AlcoholImpure("cropsnh:FReed", 50, "potion.alcopops");
    public final static AlcoholImpure SWhine = new AlcoholImpure("cropsnh:SWhine", 700, "potion.reedwater");
    public final static AlcoholImpure Mash = new AlcoholImpure("cropsnh:Mash", 25, "potion.reedwater");
    public final static AlcoholImpure Wash = new AlcoholImpure("cropsnh:Wash", 50, "potion.alcopops");
    public final static AlcoholImpure GHP = new AlcoholImpure("cropsnh:GHP", 700, "potion.vodka");
    public final static AlcoholImpure jagi = new AlcoholImpure("cropsnh:jagi", 100000, "potion.alcopops");
    public final static AlcoholImpure njagi = new AlcoholImpure("cropsnh:njagi", 350, "potion.alcopops");

    public static void preInit() {
        // could probably use a propper GT Material but this is faster
        CropsNHFluids.enrichedFertilizer = FluidUtils.addGTFluidNonMolten(
            "cropsnh:EnrichedFertilizer",
            "Enriched Fertilizer",
            new short[] { 40, 229, 21, 100 },
            4,
            295,
            null,
            ItemUtils.getEmptyCell(),
            1000,
            true);

        CropsNHItemList.enrichedFertilizerCell.set(
            FluidContainerRegistry.fillFluidContainer(
                new FluidStack(CropsNHFluids.enrichedFertilizer, 1000),
                ItemList.Cell_Empty.get(1)));
        try {
            CropsNHItemList.enrichedFertilizerCell.get(1);
        } catch (NullPointerException npe) {
            throw new LoaderException(npe);
        }
    }
}
