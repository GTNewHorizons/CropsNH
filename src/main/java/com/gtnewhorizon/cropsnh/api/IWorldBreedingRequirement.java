package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;

import net.minecraft.world.World;

public interface IWorldBreedingRequirement extends IBreedingRequirement {

    /**
     * Checks if a crop at a given position can breed
     * 
     * @param parents The parent crops for the breeding operation
     * @param world   The world the crops tick is in
     * @param tile    The tile of the crop stick
     * @param x       The x coordinate of the crops stick
     * @param y       The y coordinate of the crops stick
     * @param z       The z coordinate of the crops stick
     * @return True if the crop can breed
     */
    boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z);
}
