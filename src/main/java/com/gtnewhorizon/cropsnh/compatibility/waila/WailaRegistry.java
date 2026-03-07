package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaRegistry {

    public static void onInit() {
        if (!ModUtils.Waila.isModLoaded()) return;
        FMLInterModComms
            .sendMessage(ModUtils.Waila.ID, "register", WailaRegistry.class.getCanonicalName() + ".initWaila");
    }

    public static void initWaila(IWailaRegistrar registry) {
        // All blocks.
        IWailaDataProvider cropStickProvider = new CropStickWailaProvider();
        registry.registerBodyProvider(cropStickProvider, TileEntityCropSticks.class);
        registry.registerStackProvider(cropStickProvider, TileEntityCropSticks.class);
        registry.registerNBTProvider(cropStickProvider, TileEntityCropSticks.class);
    }

}
