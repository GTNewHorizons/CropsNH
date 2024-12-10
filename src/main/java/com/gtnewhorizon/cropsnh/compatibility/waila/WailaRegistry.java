package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.blocks.BlockCropsNH;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaRegistry {

    public static void initWaila(IWailaRegistrar registry) {
        // All blocks.
        IWailaDataProvider agriProvider = new CropsNHDataProvider();
        registry.registerBodyProvider(agriProvider, TileEntityCrop.class);
        registry.registerNBTProvider(agriProvider, TileEntityCrop.class);
    }
}
