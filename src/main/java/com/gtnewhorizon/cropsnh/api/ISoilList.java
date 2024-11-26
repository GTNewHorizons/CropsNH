package com.gtnewhorizon.cropsnh.api;

import net.minecraft.block.Block;

public interface ISoilList {

    /**
     * Checks if the block has been registered in this soil list.
     *
     * @param block The block to check for.
     * @param meta  The metadata of the block.
     * @return True if the block has been registered.
     */
    boolean isRegistered(Block block, int meta);

    /**
     * Adds a list of soil to this soil list.
     * 
     * @param soils The list of soils to add to the list.
     */
    void registerSoil(BlockWithMeta... soils);
}
