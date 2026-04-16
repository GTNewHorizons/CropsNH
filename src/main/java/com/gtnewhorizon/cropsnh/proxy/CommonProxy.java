package com.gtnewhorizon.cropsnh.proxy;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.compatibility.UiE.UtilitiesInExcessCompatHandler;
import com.gtnewhorizon.cropsnh.compatibility.extrautils.ExUWateringCanHandler;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.ModUtils;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregtech.api.events.BlockScanningEvent;

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
        if (ModUtils.ExtraUtilities.isModLoaded()) MinecraftForge.EVENT_BUS.register(new ExUWateringCanHandler());
    }

    @Override
    public void initConfiguration(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event);
    }

    public static class BlockScanningEventHandler {

        @SubscribeEvent
        public void onBlockScanned(BlockScanningEvent event) {
            if (!(event.mTileEntity instanceof TileEntityCropSticks teCrop)) return;
            event.mEUCost += 1000;
            if (teCrop.hasCrop() && !teCrop.getSeed()
                .getStats()
                .isAnalyzed()) {
                teCrop.getSeed()
                    .setAnalyzed(true);
            }
            teCrop.getPlantLensStatus(event.mList);
            event.mList.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.industrialFarm.scanner.6",
                    formatNumber(teCrop.getNutrientScore()),
                    formatNumber(TileEntityCropSticks.MAX_NUTRIENT_SCORE)));
            List<IGrowthRequirement> failedReqs = teCrop.getFailedChecks();
            if (failedReqs != null) {
                for (IGrowthRequirement req : failedReqs) {
                    event.mList.add(req.getDescription());
                }
            }
        }
    }
}
