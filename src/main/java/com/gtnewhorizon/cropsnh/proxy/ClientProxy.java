package com.gtnewhorizon.cropsnh.proxy;

import codechicken.nei.api.API;
import com.gtnewhorizon.cropsnh.blocks.BlockCropsNH;
import com.gtnewhorizon.cropsnh.compatibility.NEI.NEIConfig;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.handler.ItemToolTipHandler;
import com.gtnewhorizon.cropsnh.handler.SoundHandler;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.init.Items;
import com.gtnewhorizon.cropsnh.items.ItemCropsNH;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.renderers.blocks.RenderBlockBase;
import com.gtnewhorizon.cropsnh.renderers.player.renderhooks.RenderPlayerHooks;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.util.Iterator;

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
        return FMLClientHandler.instance().getServer().worldServerForDimension(dimension);
    }

    @Override
    public Entity getEntityById(World world, int id) {
        return world.getEntityByID(id);
    }

    @Override
    public void registerRenderers() {
        //BLOCKS
        //------
        for(Field field: Blocks.class.getDeclaredFields()) {
            if(field.getType().isAssignableFrom(BlockCropsNH.class)) {
                try {
                    Object obj = field.get(null);
                    if(obj!=null) {
                        ((BlockCropsNH) obj).getRenderer();
                    }
                } catch (IllegalAccessException e) {
                    LogHelper.printStackTrace(e);
                }
            }
        }

        //ITEMS
        //-----
        for(Field field: Items.class.getDeclaredFields()) {
            if(field.getType().isAssignableFrom(ItemCropsNH.class)) {
                try {
                    Object obj = field.get(null);
                    if(obj!=null) {
                        ((ItemCropsNH) obj).getItemRenderer();
                    }
                }catch (IllegalAccessException e) {
                    LogHelper.printStackTrace(e);
                }
            }
        }

        LogHelper.debug("Renderers registered");
    }

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();

        ItemToolTipHandler itemToolTipHandler = new ItemToolTipHandler();
        MinecraftForge.EVENT_BUS.register(itemToolTipHandler);

        RenderPlayerHooks renderPlayerHooks = new RenderPlayerHooks();
        MinecraftForge.EVENT_BUS.register(renderPlayerHooks);

        SoundHandler soundHandler = new SoundHandler();
        MinecraftForge.EVENT_BUS.register(soundHandler);
    }

    @Override
    public void initNEI() {
        NEIConfig configNEI = new NEIConfig();
        configNEI.loadConfig();
    }

    @Override
    public void hideItemInNEI(ItemStack stack) {
        Iterator<ModContainer> mods = Loader.instance().getActiveModList().iterator();
        ModContainer modContainer;
        while(mods.hasNext()) {
            modContainer = (ModContainer) mods.next();
            if(modContainer.getModId().equalsIgnoreCase("NotEnoughItems")) {
                API.hideItem(stack);
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
