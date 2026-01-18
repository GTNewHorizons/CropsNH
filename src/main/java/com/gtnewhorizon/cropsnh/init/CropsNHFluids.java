package com.gtnewhorizon.cropsnh.init;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.fluids.AlcoholImpure;
import com.gtnewhorizon.cropsnh.reference.Reference;

import cpw.mods.fml.common.LoaderException;
import gregtech.api.enums.ItemList;
import gtPlusPlus.core.util.minecraft.FluidUtils;

public class CropsNHFluids {

    public static Fluid fertilizer;
    public static Fluid enrichedFertilizer;

    // alchool
    public final static AlcoholImpure FWheat = new AlcoholImpure(Reference.MOD_ID + ":FWheat", 40, "potion.alcopops");
    public final static AlcoholImpure Korn = new AlcoholImpure(Reference.MOD_ID + ":Korn", 320, "potion.vodka");
    public final static AlcoholImpure DKorn = new AlcoholImpure(Reference.MOD_ID + ":DKorn", 380, "potion.vodka");
    public final static AlcoholImpure FReed = new AlcoholImpure(Reference.MOD_ID + ":FReed", 50, "potion.alcopops");
    public final static AlcoholImpure SWhine = new AlcoholImpure(Reference.MOD_ID + ":SWhine", 700, "potion.reedwater");
    public final static AlcoholImpure Mash = new AlcoholImpure(Reference.MOD_ID + ":Mash", 25, "potion.reedwater");
    public final static AlcoholImpure Wash = new AlcoholImpure(Reference.MOD_ID + ":Wash", 50, "potion.alcopops");
    public final static AlcoholImpure GHP = new AlcoholImpure(Reference.MOD_ID + ":GHP", 700, "potion.vodka");
    public final static AlcoholImpure jagi = new AlcoholImpure(Reference.MOD_ID + ":jagi", 100000, "potion.alcopops");
    public final static AlcoholImpure njagi = new AlcoholImpure(Reference.MOD_ID + ":njagi", 350, "potion.alcopops");

    public static void preInit() {
        // could probably use a propper GT Material but this is faster
        CropsNHFluids.enrichedFertilizer = FluidUtils.addGTFluidNonMolten(
            Reference.MOD_ID + ":EnrichedFertilizer",
            "Enriched Fertilizer",
            new short[] { 40, 229, 21, 100 },
            4,
            295,
            null,
            ItemList.Cell_Empty.get(1),
            1000,
            true);

        CropsNHFluids.fertilizer = FluidUtils.addGTFluidNonMolten(
            Reference.MOD_ID + ":fertilizer",
            "Fertilizer",
            new short[] { 45, 170, 45, 100 },
            4,
            32,
            null,
            ItemList.Cell_Empty.get(1),
            1000,
            true);

        CropsNHItemList.enrichedFertilizerCell.set(
            FluidContainerRegistry.fillFluidContainer(
                new FluidStack(CropsNHFluids.enrichedFertilizer, 1000),
                ItemList.Cell_Empty.get(1)));
        CropsNHItemList.fertilizerCell.set(
            FluidContainerRegistry
                .fillFluidContainer(new FluidStack(CropsNHFluids.fertilizer, 1000), ItemList.Cell_Empty.get(1)));
        try {
            CropsNHItemList.fertilizer.get(1);
        } catch (NullPointerException npe) {
            throw new LoaderException(npe);
        }
        try {
            CropsNHItemList.enrichedFertilizerCell.get(1);
        } catch (NullPointerException npe) {
            throw new LoaderException(npe);
        }
    }
}
