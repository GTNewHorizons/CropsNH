package com.gtnewhorizon.cropsnh.api;

import net.minecraft.world.World;

public interface IWorldGrowthRequirement {

    /**
     * A short description shown in the seed's tooltip.
     */
    String getDescription();

    /**
     *
     * @param world The world the crop is growing in
     * @param tile  The tile holding the seed
     * @param x     The X coordinate of the crop
     * @param y     The y coordinate of the crop
     * @param z     The z coordiante of the crop
     * @return true if the crop can grow
     */
    boolean canGrow(World world, ICropStickTile tile, int x, int y, int z);
}
