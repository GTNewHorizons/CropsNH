package com.gtnewhorizon.cropsnh.compatibility.waila;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;

public class CropStickWailaProvider implements IWailaDataProvider {

    private final static ItemStack weedStack = new ItemStack(Blocks.tallgrass, 1, 1);

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        TileEntity te = dataAccessor.getTileEntity();
        if (te instanceof TileEntityCropSticks teCrop) {
            if (teCrop.hasWeed()) {
                return weedStack;
            }
            NBTTagCompound nbt = dataAccessor.getNBTData();
            ISeedData seedData = nbt.hasKey(Names.NBT.crop, Data.NBTType._object)
                ? new SeedData(nbt.getCompoundTag(Names.NBT.crop))
                : teCrop.getSeed();
            if (seedData != null) {
                if (seedData.getCrop() instanceof CropMigrator && nbt.hasKey(Names.NBT.extra, Data.NBTType._object)) {
                    return new CropMigrator.AdditionalData(nbt.getCompoundTag(Names.NBT.extra)).seed.getStack();
                }
                return seedData.getStack();
            }
        }
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor dataAccessor,
        IWailaConfigHandler configHandler) {
        return currentTip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> information, IWailaDataAccessor dataAccessor,
        IWailaConfigHandler configHandler) {
        TileEntity te = dataAccessor.getTileEntity();
        NBTTagCompound nbt = dataAccessor.getNBTData();
        if (te instanceof TileEntityCropSticks teCrop) {
            if (teCrop.hasCrop()) {
                if (teCrop.hasWeed()) {
                    information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.weeds"));
                } else {
                    String header, value;
                    ISeedData seedData = nbt.hasKey(Names.NBT.crop, Data.NBTType._object)
                        ? new SeedData(nbt.getCompoundTag(Names.NBT.crop))
                        : teCrop.getSeed();
                    if (seedData.getCrop() instanceof CropMigrator
                        && nbt.hasKey(Names.NBT.extra, Data.NBTType._object)) {
                        seedData = new CropMigrator.AdditionalData(nbt.getCompoundTag(Names.NBT.extra)).seed;
                    }
                    if (seedData.getStats()
                        .isAnalyzed()) {
                        // Add the seed name
                        header = StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.seed");
                        value = StatCollector.translateToLocal(
                            seedData.getCrop()
                                .getUnlocalizedName());
                        information.add(header + ": " + value);
                    }

                    // add growth progress
                    header = StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.progress");
                    value = String.format("%3.2f", nbt.getFloat("waila_perc") * 100.0f);
                    information.add(header + ": " + value + "%");

                    if (nbt.getBoolean(Names.NBT.sick)) {
                        information.add(
                            EnumChatFormatting.RED
                                + StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.isSick")
                                + EnumChatFormatting.RESET);
                    }

                    List<IGrowthRequirement> failedReqs = teCrop.getFailedChecks();
                    if (failedReqs != null) {
                        for (IGrowthRequirement req : failedReqs) {
                            information.add(EnumChatFormatting.RED + req.getDescription() + EnumChatFormatting.RESET);
                        }
                    }

                    // write out the stats of the crop if the stats exist
                    ISeedStats stats = seedData.getStats();
                    if (stats.isAnalyzed()) {
                        information.add(
                            String.format(
                                "%s -- %s: %d  %s: %d  %s: %d",
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.stats"),
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.growth"),
                                stats.getGrowth(),
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.gain"),
                                stats.getGain(),
                                StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.resistance"),
                                stats.getResistance()));
                    }
                }
            } else {
                information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.empty"));
            }

            information.add(
                String.format(
                    "%s -- %s: %d  %s: %d  %s: %d",
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.soil"),
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.fertilizer"),
                    nbt.getInteger(Names.NBT.fertilizer),
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.water"),
                    nbt.getInteger(Names.NBT.water),
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.weedEx"),
                    nbt.getInteger(Names.NBT.weedEX)));
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
        if (te instanceof TileEntityCropSticks) {
            tag.setFloat("waila_perc", ((TileEntityCropSticks) te).getGrowthPercent());
            te.writeToNBT(tag);
        }
        return tag;
    }
}
