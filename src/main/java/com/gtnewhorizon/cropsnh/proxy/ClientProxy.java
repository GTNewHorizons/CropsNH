package com.gtnewhorizon.cropsnh.proxy;

import java.lang.reflect.Field;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.blocks.abstracts.BlockCropsNH;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import codechicken.nei.api.API;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public World getWorldByDimensionId(int dimension) {
        return FMLClientHandler.instance()
            .getServer()
            .worldServerForDimension(dimension);
    }

    @Override
    public Entity getEntityById(World world, int id) {
        return world.getEntityByID(id);
    }

    @Override
    public void registerRenderers() {
        // BLOCKS
        // ------
        for (Field field : CropsNHBlocks.class.getDeclaredFields()) {
            if (field.getType()
                .isAssignableFrom(BlockCropsNH.class)) {
                try {
                    Object obj = field.get(null);
                    if (obj instanceof BlockCropsNH cropBlockNH) {
                        cropBlockNH.getRenderer();
                    }
                } catch (IllegalAccessException e) {
                    LogHelper.printStackTrace(e);
                }
            }
        }

        LogHelper.debug("Renderers registered");
    }

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
    }

    @Override
    public void hideItemInNEI(ItemStack stack) {
        Iterator<ModContainer> mods = Loader.instance()
            .getActiveModList()
            .iterator();
        ModContainer modContainer;
        while (mods.hasNext()) {
            modContainer = (ModContainer) mods.next();
            if (modContainer.getModId()
                .equalsIgnoreCase("NotEnoughItems")) {
                API.hideItem(stack);
            }
        }
    }

    @Override
    public void addItemInNEI(ItemStack stack) {
        Iterator<ModContainer> mods = Loader.instance()
            .getActiveModList()
            .iterator();
        ModContainer modContainer;
        while (mods.hasNext()) {
            modContainer = (ModContainer) mods.next();
            if (modContainer.getModId()
                .equalsIgnoreCase("NotEnoughItems")) {
                API.addItemVariant(stack.getItem(), stack);
            }
        }
    }

    @Override
    public int getRenderId(Block block) {
        return RenderBlockBase.getRenderId(block);
    }

    @Override
    public void initConfiguration(FMLPreInitializationEvent event) {
        super.initConfiguration(event);
        ConfigurationHandler.initClientConfigs(event);
    }
}
