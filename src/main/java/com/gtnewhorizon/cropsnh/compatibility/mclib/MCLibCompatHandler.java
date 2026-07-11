package com.gtnewhorizon.cropsnh.compatibility.mclib;

import makamys.mclib.core.MCLib;
import makamys.mclib.ext.assetdirector.ADConfig;
import makamys.mclib.ext.assetdirector.AssetDirectorAPI;

public class MCLibCompatHandler {

    public static void onConstruct() {
        MCLib.init();
        ADConfig config = new ADConfig();
        config.addSoundEvent("1.21", "item.crop.plant", "block");
        config.addSoundEvent("1.21", "block.crop.break", "block");
        config.addSoundEvent("1.21", "item.bone_meal.use", "block");
        AssetDirectorAPI.register(config);
    }
}
