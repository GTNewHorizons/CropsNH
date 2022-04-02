package com.gtnewhorizon.cropsnh.init;

import com.gtnewhorizon.cropsnh.CropsNH;
import com.gtnewhorizon.cropsnh.entity.EntityLeashKnotCropsNH;
import com.gtnewhorizon.cropsnh.entity.EntityVillagerFarmer;
import com.gtnewhorizon.cropsnh.reference.Names;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {
    public static void init() {
        EntityRegistry.registerModEntity(EntityVillagerFarmer.class, Names.Objects.villager, 0, CropsNH.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLeashKnotCropsNH.class, Names.Objects.leash, 1, CropsNH.instance, 16, 100, false);
    }
}
