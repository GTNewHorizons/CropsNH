package com.gtnewhorizon.cropsnh.compatibility.StructureLib;

import com.gtnewhorizon.cropsnh.api.CropsNHStructureChannels;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.structurelib.StructureLibAPI;

public class StructureLibCompatHandler {

    public static void onInit() {
        if (!ModUtils.StructureLib.isModLoaded()) return;
        StructureLibAPI.registerChannelDescription(
            CropsNHStructureChannels.IFUpgrades.get(),
            Reference.MOD_ID,
            CropsNHStructureChannels.IFUpgrades.get());
    }

}
