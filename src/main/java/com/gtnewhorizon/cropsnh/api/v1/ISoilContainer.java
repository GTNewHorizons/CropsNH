package com.gtnewhorizon.cropsnh.api.v1;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 *  implement in block class, can be used for flower pots for example
 */
public interface ISoilContainer {
    /** returns the block contained within this container */
    Block getSoil(World world, int x, int y, int z);

    int getSoilMeta(World world, int x, int y, int z);
}
