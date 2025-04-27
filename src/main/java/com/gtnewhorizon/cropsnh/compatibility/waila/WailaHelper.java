package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;

import cpw.mods.fml.common.event.FMLInterModComms;
import gregtech.api.enums.Mods;

public class WailaHelper extends ModHelper {

    @Override
    protected void onInit() {
        FMLInterModComms.sendMessage(
            Mods.Waila.ID,
            "register",
            "com.gtnewhorizon.cropsnh.compatibility.waila.WailaRegistry.initWaila");
    }

    @Override
    protected String modId() {
        return Mods.Waila.ID;
    }
}
