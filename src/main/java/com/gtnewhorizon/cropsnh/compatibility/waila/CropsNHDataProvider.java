package com.gtnewhorizon.cropsnh.compatibility.waila;

import java.util.List;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.crops.CropWeed;
import com.gtnewhorizon.cropsnh.farming.SeedStats;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.blocks.BlockCropsNH;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropsNH;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;

public class CropsNHDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor dataAccessor,
        IWailaConfigHandler configHandler) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> information, IWailaDataAccessor dataAccessor,
        IWailaConfigHandler configHandler) {
        TileEntity te = dataAccessor.getTileEntity();
        NBTTagCompound nbt = dataAccessor.getNBTData();
        if (te instanceof TileEntityCrop) {
            TileEntityCrop teCrop = (TileEntityCrop) te;
            if (teCrop.hasCrop()) {
                if (teCrop.hasWeed()) {
                    information.add(StatCollector.translateToLocal("cropsnh_tooltip.weeds"));
                } else {
                    String header, value;

                    // Add the seed name
                    header = StatCollector.translateToLocal("cropsnh_tooltip.seed");
                    value = StatCollector.translateToLocal(teCrop.getCrop().getUnlocalizedName());
                    information.add(header + ": " + value);

                    // add growth progress
                    header = StatColl1ector.translateToLocal("cropsnh_tooltip.progress");
                    value = String.format("%3.2f", nbt.getFloat("waila_perc") * 100.0f);
                    information.add(header + ": " + value + "%");

                    if (nbt.getBoolean(Names.NBT.sick)) {
                        information.add(EnumChatFormatting.RED + StatCollector.translateToLocal("cropsnh_tooltip.isSick") + EnumChatFormatting.RESET);
                    }

                    List<IWorldGrowthRequirement> failedReqs = teCrop.getFailedChecks();
                    if (failedReqs != null) {
                        for (IWorldGrowthRequirement req : failedReqs) {
                            information.add(EnumChatFormatting.RED + StatCollector.translateToLocal(req.getDescription()) + EnumChatFormatting.RESET);
                        }
                    }

                    // write out the stats of the crop if the stats exist
                    ISeedStats stats = SeedStats.readFromNBT(nbt);
                    if (stats.isAnalyzed()) {
                        information.add(
                            String.format(
                                "%s -- %s: %d  %s: %d  %s: %d",
                                StatCollector.translateToLocal("cropsnh_tooltip.stats"),
                                StatCollector.translateToLocal("cropsnh_tooltip.growth"),
                                stats.getGrowth(),
                                StatCollector.translateToLocal("cropsnh_tooltip.gain"),
                                stats.getGain(),
                                StatCollector.translateToLocal("cropsnh_tooltip.resistance"),
                                stats.getResistance()));
                    }
                }
            } else {
                information.add(StatCollector.translateToLocal("cropsnh_tooltip.empty"));
            }

            information.add(
                String.format(
                    "%s -- %s: %d  %s: %d  %s: %d",
                    StatCollector.translateToLocal("cropsnh_tooltip.soil"),
                    StatCollector.translateToLocal("cropsnh_tooltip.fertilizer"),
                    nbt.getInteger(Names.NBT.fertilizer),
                    StatCollector.translateToLocal("cropsnh_tooltip.water"),
                    nbt.getInteger(Names.NBT.water),
                    StatCollector.translateToLocal("cropsnh_tooltip.weedEx"),
                    nbt.getInteger(Names.NBT.weedEx)));
        }
        return information;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor dataAccessor,
        IWailaConfigHandler configHandler) {
        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x,
        int y, int z) {
        if (te instanceof TileEntityCrop) {
            TileEntityCrop teCrop = (TileEntityCrop) te;
            tag.setFloat("waila_perc", ((TileEntityCrop) te).getGrowthPercent());
            te.writeToNBT(tag);
        }
        return tag;
    }
}
