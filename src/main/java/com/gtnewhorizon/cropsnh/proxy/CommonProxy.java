package com.gtnewhorizon.cropsnh.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.gtnewhorizon.cropsnh.compatibility.UiE.UtilitiesInExcessCompatHandler;
import com.gtnewhorizon.cropsnh.compatibility.extrautils.ExUWateringCanHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;
import com.gtnewhorizon.cropsnh.items.tools.ItemSpadeNH;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.CropsNHChatComponentNutrientScore;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregtech.api.events.BlockScanningEvent;
import gregtech.api.util.scanner.ScannerHelper;

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
        // TODO: Enable when UIE gets added to GTNH
        if (ModUtils.UtilitiesInExcess.isModLoaded()) UtilitiesInExcessCompatHandler.onRegisterEventHandlers();
        MinecraftForge.EVENT_BUS.register(new BlockScanningEventHandler());
        MinecraftForge.EVENT_BUS.register(new PreventCropSticksFromBreakingWhenSneaking());
        if (ModUtils.ExtraUtilities.isModLoaded()) MinecraftForge.EVENT_BUS.register(new ExUWateringCanHandler());
    }

    @Override
    public void initConfiguration(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event);
    }

    public static class PreventCropSticksFromBreakingWhenSneaking {

        @SubscribeEvent
        public void canHarvest(PlayerEvent.HarvestCheck harvestCheck) {
            if (shouldStopBreakingCropStick(harvestCheck.block, harvestCheck.entityPlayer)) {
                harvestCheck.success = false;
            }
        }

        @SubscribeEvent
        public void getBreakSpeed(PlayerEvent.BreakSpeed breakSpeedCheck) {
            if (shouldStopBreakingCropStick(breakSpeedCheck.block, breakSpeedCheck.entityPlayer)) {
                breakSpeedCheck.newSpeed = 0.0f;
            }
        }

        private static boolean shouldStopBreakingCropStick(Block block, EntityPlayer player) {
            if (block != CropsNHBlocks.blockCropSticks || !player.isSneaking()) return false;
            ItemStack heldItem = player.getHeldItem();
            return heldItem != null && heldItem.getItem() instanceof ItemSpadeNH;
        }

    }

    public static class BlockScanningEventHandler {

        @SubscribeEvent
        public void onBlockScanned(BlockScanningEvent event) {
            // check if we're scanning a crop stick
            if (!(event.mTileEntity instanceof TileEntityCropSticks teCrop)) return;
            event.mEUCost += 1000;

            // analyze the seed if it's not already analyzed
            if (teCrop.hasCrop() && !teCrop.getSeed()
                .getStats()
                .isAnalyzed()) {
                teCrop.getSeed()
                    .setAnalyzed(true);
            }

            event.mComponents.add(ScannerHelper.addTitleComp("title_crops"));

            // add chat components to scanner
            teCrop.getPlantLensStatus(event.mComponents);

            // add nutrient score line
            event.mComponents.add(new CropsNHChatComponentNutrientScore(teCrop.getNutrientScore()));
        }
    }
}
