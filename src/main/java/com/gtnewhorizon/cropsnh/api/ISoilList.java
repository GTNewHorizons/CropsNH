package com.gtnewhorizon.cropsnh.api;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface ISoilList {

    String getId();

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

    /**
     * Dumps the contents of the string builder at runtime.
     *
     * @param sb The string builder to append to.
     */
    void dump(StringBuilder sb);

    /**
     * @return A stream of item stacks for this soil list.
     */
    Stream<ItemStack> getNEIItemList();
}
