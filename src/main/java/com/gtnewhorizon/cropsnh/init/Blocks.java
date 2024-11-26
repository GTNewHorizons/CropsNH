package com.gtnewhorizon.cropsnh.init;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.blocks.BlockCropSticks;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

public class Blocks {

    public static Block blockCrop;

    public static void init() {
        blockCrop = new BlockCropSticks();
        LogHelper.debug("Blocks registered");
    }
}
