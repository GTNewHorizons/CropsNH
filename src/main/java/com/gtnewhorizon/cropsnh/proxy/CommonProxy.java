package com.gtnewhorizon.cropsnh.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.handler.PlayerConnectToServerHandler;
import com.gtnewhorizon.cropsnh.handler.PlayerInteractEventHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public abstract class CommonProxy implements IProxy {

    @Override
    public Entity getEntityById(int dimension, int id) {
        return getEntityById(getWorldByDimensionId(dimension), id);
    }

    @Override
    public Entity getEntityById(World world, int id) {
        return world.getEntityByID(id);
    }

    @Override
    public void registerEventHandlers() {
        PlayerInteractEventHandler playerInteractEventHandler = new PlayerInteractEventHandler();
        MinecraftForge.EVENT_BUS.register(playerInteractEventHandler);

        PlayerConnectToServerHandler playerConnectToServerHandler = new PlayerConnectToServerHandler();
        FMLCommonHandler.instance().bus().register(playerConnectToServerHandler);
        MinecraftForge.EVENT_BUS.register(playerConnectToServerHandler);

    }

    @Override
    public void initConfiguration(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event);
    }
}
