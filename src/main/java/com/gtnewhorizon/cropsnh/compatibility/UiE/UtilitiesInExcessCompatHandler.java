package com.gtnewhorizon.cropsnh.compatibility.UiE;

import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.Optional;

public class UtilitiesInExcessCompatHandler {

    @Optional.Method(modid = ModUtils.ModIDs.UtilitiesInExcess)
    public static void onRegeisteEventHandlers() {
        if (!ModUtils.UtilitiesInExcess.isModLoaded()) return;
        // MinecraftForge.EVENT_BUS.register(new WateringCanHandler());
    }

    // public static class WateringCanHandler {
    //
    // @SubscribeEvent
    // public void onWateringCanEvent(WateringCanEvent event) {
    // return;
    // }
    // }
}
