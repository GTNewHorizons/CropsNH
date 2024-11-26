package com.gtnewhorizon.cropsnh.loaders;

import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.crops.gregtech.CropTerraWart;
import com.gtnewhorizon.cropsnh.crops.thaumcraft.PrimordialPerlCrop;
import com.gtnewhorizon.cropsnh.crops.vanilla.CropNetherwart;
import com.gtnewhorizon.cropsnh.farming.registries.CropRegistry;

public class CropLoader {

    public static void init() {
        // weeds
        CropRegistry.instance.register(new CropWeed());

        // Vanilla
        CropRegistry.instance.register(new CropNetherwart());

        // CropNH
        CropRegistry.instance.register(new CropTerraWart());

        // Thaum
        CropRegistry.instance.register(new PrimordialPerlCrop());
    }
}
