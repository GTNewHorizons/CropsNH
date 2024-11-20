package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.reference.Names;

import cpw.mods.fml.common.event.FMLInterModComms;

public class WailaHelper extends ModHelper {

    @Override
    protected void onInit() {
        FMLInterModComms.sendMessage(
            Names.Mods.waila,
            "register",
            "com.gtnewhorizon.cropsnh.compatibility.waila.WailaRegistry.initWaila");
    }

    @Override
    protected String modId() {
        return Names.Mods.waila;
    }
}
