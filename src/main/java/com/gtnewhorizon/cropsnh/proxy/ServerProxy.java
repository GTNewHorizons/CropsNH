package com.gtnewhorizon.cropsnh.proxy;

import net.minecraft.block.Block;

@SuppressWarnings("unused")
public class ServerProxy extends CommonProxy {

    @Override
    public void registerRenderers() {}

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
    }

    @Override
    public int getRenderId(Block block) {
        return -1;
    }

}
