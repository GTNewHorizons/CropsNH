package com.gtnewhorizon.cropsnh.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.compatibility.UiE.UtilitiesInExcessCompatHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

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
        if (ModUtils.UtilitiesInExcess.isModLoaded()) UtilitiesInExcessCompatHandler.onRegeisteEventHandlers();
    }

    @Override
    public void initConfiguration(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event);
    }

}
