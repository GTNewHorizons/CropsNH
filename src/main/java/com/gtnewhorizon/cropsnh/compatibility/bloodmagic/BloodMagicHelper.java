package com.gtnewhorizon.cropsnh.compatibility.bloodmagic;

import java.lang.reflect.Method;

import com.gtnewhorizon.cropsnh.compatibility.ModHelper;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Names;

public class BloodMagicHelper extends ModHelper {

    @Override
    @SuppressWarnings("unchecked")
    protected void onInit() {
        try {
            Class<?> harvestRegistry = Class.forName("WayofTime.alchemicalWizardry.api.harvest.HarvestRegistry");
            Class<?> harvestHandler = Class.forName("WayofTime.alchemicalWizardry.api.harvest.IHarvestHandler");
            Method registerHarvestHandler = harvestRegistry.getMethod("registerHarvestHandler", harvestHandler);
            registerHarvestHandler.invoke(null, new HarvestHandler());
        } catch (Exception e) {
            if (ConfigurationHandler.debug) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected String modId() {
        return Names.Mods.bloodMagic;
    }
}
