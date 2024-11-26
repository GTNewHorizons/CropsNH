package com.gtnewhorizon.cropsnh.api;

import net.minecraft.block.Block;

public interface IBlockUnderComparator {

    boolean isValid(Block block, int metadata);
}
