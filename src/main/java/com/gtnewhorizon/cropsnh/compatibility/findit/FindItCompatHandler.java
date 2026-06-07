package com.gtnewhorizon.cropsnh.compatibility.findit;

import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnh.findit.FindIt;

public abstract class FindItCompatHandler {

    public static void postInit() {
        if (!ModUtils.FindIt.isModLoaded()) return;
        FindIt.INSTANCE.pluginsList.add(new CropsNHFIndItFilterProvider());
    }
}
