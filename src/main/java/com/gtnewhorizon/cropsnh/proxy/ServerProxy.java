package com.gtnewhorizon.cropsnh.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.server.FMLServerHandler;

@SuppressWarnings("unused")
public class ServerProxy extends CommonProxy {

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }

    @Override
    public World getClientWorld() {
        return null;
    }

    @Override
    public World getWorldByDimensionId(int dimension) {
        return FMLServerHandler.instance()
            .getServer()
            .worldServerForDimension(dimension);
    }

    @Override
    public void registerRenderers() {}

    @Override
    public void initNEI() {}

    @Override
    public void hideItemInNEI(ItemStack stack) {}

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
    }

    @Override
    public int getRenderId(Block block) {
        return -1;
    }

}
