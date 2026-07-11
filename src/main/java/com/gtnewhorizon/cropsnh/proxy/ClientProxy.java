package com.gtnewhorizon.cropsnh.proxy;

import java.lang.reflect.Field;

import net.minecraft.block.Block;

import com.gtnewhorizon.cropsnh.blocks.abstracts.BlockCropsNH;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

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
    public int getRenderId(Block block) {
        return RenderBlockBase.getRenderId(block);
    }

    @Override
    public void initConfiguration(FMLPreInitializationEvent event) {
        super.initConfiguration(event);
        ConfigurationHandler.initClientConfigs(event);
    }
}
