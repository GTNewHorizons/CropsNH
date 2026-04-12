package com.gtnewhorizon.cropsnh.init;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.fluids.AlcoholImpure;
import com.gtnewhorizon.cropsnh.fluids.CropsNHCellFluid;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class CropsNHFluids {

    // fertilizers
    public static CropsNHCellFluid fertilizer;
    public static CropsNHCellFluid enrichedFertilizer;

    // alchool
    public static AlcoholImpure FWheat;
    public static AlcoholImpure Korn;
    public static AlcoholImpure DKorn;
    public static AlcoholImpure FReed;
    public static AlcoholImpure SWhine;
    public static AlcoholImpure Mash;
    public static AlcoholImpure Wash;
    public static AlcoholImpure GHP;
    public static AlcoholImpure jagi;
    public static AlcoholImpure njagi;

    public static void preInit() {

        // using . instead of : because backwards compatibility
        fertilizer = new CropsNHCellFluid(
            Reference.MOD_ID + ".fertilizer",
            new Color(45, 170, 45, 100),
            "fertilizerCell",
            CropsNHItemList.fertilizerCell);

        enrichedFertilizer = new CropsNHCellFluid(
            Reference.MOD_ID + ".enrichedFertilizer",
            new Color(40, 229, 21, 100),
            "enrichedFertilizerCell",
            CropsNHItemList.enrichedFertilizerCell);

        FWheat = new AlcoholImpure(Reference.MOD_ID + ":FWheat", 40, "potion.alcopops");
        Korn = new AlcoholImpure(Reference.MOD_ID + ":Korn", 320, "potion.vodka");
        DKorn = new AlcoholImpure(Reference.MOD_ID + ":DKorn", 380, "potion.vodka");
        FReed = new AlcoholImpure(Reference.MOD_ID + ":FReed", 50, "potion.alcopops");
        SWhine = new AlcoholImpure(Reference.MOD_ID + ":SWhine", 700, "potion.reedwater");
        Mash = new AlcoholImpure(Reference.MOD_ID + ":Mash", 25, "potion.reedwater");
        Wash = new AlcoholImpure(Reference.MOD_ID + ":Wash", 50, "potion.alcopops");
        GHP = new AlcoholImpure(Reference.MOD_ID + ":GHP", 700, "potion.vodka");
        jagi = new AlcoholImpure(Reference.MOD_ID + ":jagi", 100000, "potion.alcopops");
        njagi = new AlcoholImpure(Reference.MOD_ID + ":njagi", 350, "potion.alcopops");
    }
}
