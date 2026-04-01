package com.gtnewhorizon.cropsnh.compatibility.forestry;

import java.util.ArrayList;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.Optional;
import forestry.api.farming.Farmables;
import forestry.api.multiblock.MultiblockManager;
import forestry.core.circuits.Circuit;
import forestry.farming.circuits.CircuitFarmLogic;
import forestry.farming.logic.FarmableReference;
import forestry.plugins.PluginManager;

public abstract class ForestryCompatHandler {

    public static void onPostInit() {
        if (!ModUtils.Forestry.isModLoaded()) return;
        addMultifarmCompat();
    }

    @Optional.Method(modid = ModUtils.ModIDs.Forestry)
    private static void addMultifarmCompat() {
        Circuit.farmIC2CropsManual = new CircuitFarmLogic("manualIC2Crop", CropsNHFarmLogic.class).setManual();
        Farmables.farmables.putIfAbsent(FarmableReference.IC2Crops.get(), new ArrayList<>());
        Farmables.farmables.get(FarmableReference.IC2Crops.get())
            .add(new CropsNHForestryFarmable());

        if (PluginManager.Module.FARMING.isEnabled()) {
            MultiblockManager.farmFertilizerRegistry.addFertilizer(CropsNHItemList.fertilizer.get(1));
        }
    }
}
