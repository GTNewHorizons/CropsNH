package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.blocks.BlockCropsNH;

import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaRegistry {

    public static void initWaila(IWailaRegistrar registry) {
        // All blocks.
        IWailaDataProvider agriProvider = new CropsNHDataProvider();
        registry.registerStackProvider(agriProvider, BlockCropsNH.class);
        registry.registerBodyProvider(agriProvider, BlockCropsNH.class);
    }
}
