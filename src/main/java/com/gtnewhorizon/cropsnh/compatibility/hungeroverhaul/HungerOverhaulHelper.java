package com.gtnewhorizon.cropsnh.compatibility.hungeroverhaul;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.reference.Names;

import cpw.mods.fml.common.event.FMLInterModComms;

public final class HungerOverhaulHelper extends ModHelper {

    @Override
    protected void onInit() {
        FMLInterModComms
            .sendMessage("HungerOverhaul", "BlacklistRightClick", "com.gtnewhorizon.cropsnh.blocks.BlockCrop");
    }

    @Override
    protected String modId() {
        return Names.Mods.hungerOverhaul;
    }
}
