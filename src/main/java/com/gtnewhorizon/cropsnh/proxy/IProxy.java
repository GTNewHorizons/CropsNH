package com.gtnewhorizon.cropsnh.proxy;

import net.minecraft.block.Block;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

    /** Registers the renderers on the client, does nothing on the server */
    void registerRenderers();

    /** Registers all the needed event handlers to the correct event bus */
    void registerEventHandlers();

    /** Returns the correct render id, might return a wrong id on the server */
    int getRenderId(Block block);

    /** Initializes the configuration file */
    void initConfiguration(FMLPreInitializationEvent event);
}
