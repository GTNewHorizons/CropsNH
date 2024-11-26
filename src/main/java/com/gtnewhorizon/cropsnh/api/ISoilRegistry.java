package com.gtnewhorizon.cropsnh.api;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface ISoilRegistry {

    /**
     * Checks if the block is a valid soil.
     *
     * @param world The world in which the block is found.
     * @param x     The x coordinate of the block.
     * @param y     The y coordinate of the block.
     * @param z     The z coordinate of the block.
     * @return True if the block is a registered soil.
     */
    boolean isRegistered(IBlockAccess world, int x, int y, int z);

    /**
     * Checks if the block has been registered with any soil list under this registry.
     *
     * @param block The block to check for.
     * @param meta  The metadata of the block.
     * @return True if the block has been registered.
     */
    boolean isRegistered(Block block, int meta);

    /**
     * Gets a soil list from the registry.
     * 
     * @implNote safe even if the list hasn't been registered yet.
     * @param type The name of the registry type.
     * @return The soil list for the given type.
     */
    ISoilList get(String type);

    /**
     * Registers a list of soils to a soil list.
     * 
     * @param type  The name of the soil list.
     * @param soils The list of soils to add to the list.
     */
    void register(String type, BlockWithMeta... soils);
}
