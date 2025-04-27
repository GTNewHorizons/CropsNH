package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaRegistry {

    public static void initWaila(IWailaRegistrar registry) {
        // All blocks.
        IWailaDataProvider cropStickProvider = new CropStickWailaProvider();
        registry.registerBodyProvider(cropStickProvider, TileEntityCrop.class);
        registry.registerStackProvider(cropStickProvider, TileEntityCrop.class);
        registry.registerNBTProvider(cropStickProvider, TileEntityCrop.class);
    }
}
