package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.api.CropsNHCrops;
import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.crops.definitions.BoPCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.BonsaiCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.FoodCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.MaterialCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.MobCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.NaturaCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.OreBerryCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.StoneLilyCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.ThaumcraftCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.TwilightForestCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.VanillaCrops;
import com.gtnewhorizon.cropsnh.crops.definitions.WitcheryCrops;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;

public class CropLoader {

    public static void postInit() {
        // weeds
        CropRegistry.instance.register(CropsNHCrops.Weed = new CropWeed());
        CropRegistry.instance.register(CropsNHCrops.Migrator = new CropMigrator());

        // the crops defined with CropBuilder, one group per file
        BonsaiCrops.register();
        BoPCrops.register();
        NaturaCrops.register();
        FoodCrops.register();
        MaterialCrops.register();
        OreBerryCrops.register();
        ThaumcraftCrops.register();
        TwilightForestCrops.register();
        MobCrops.register();
        StoneLilyCrops.register();
        VanillaCrops.register();
        WitcheryCrops.register();
    }

    public static void loadComplete() {
        for (ICropCard cc : CropRegistry.instance.getAllInRegistrationOrder()) {
            cc.onLoadComplete();
        }
    }
}
