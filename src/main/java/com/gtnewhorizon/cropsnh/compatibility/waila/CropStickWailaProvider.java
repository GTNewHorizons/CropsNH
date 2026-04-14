package com.gtnewhorizon.cropsnh.compatibility.waila;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;

public class CropStickWailaProvider implements IWailaDataProvider {

    private static final String WAILA_PROGRESS = "wp";
    private static final String WAILA_FAILED_REQ = "wf";
    private static final String WAILA_FAILED_REQ_KEY = "k";
    private static final String WAILA_FAILED_REQ_VALUES = "v";
    private static final ItemStack weedStack = new ItemStack(Blocks.tallgrass, 1, 1);

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
                    value = String.format("%3.2f", nbt.getFloat(WAILA_PROGRESS) * 100.0f);
                    information.add(header + ": " + value + "%");

                    if (nbt.getBoolean(Names.NBT.sick)) {
                        information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.isSick"));
                    }

                    // add failed reqs
                    if (nbt.hasKey(WAILA_FAILED_REQ, 9)) {
                        NBTTagList failedReqs = nbt.getTagList(WAILA_FAILED_REQ, 10);
                        for (int i = 0; i < failedReqs.tagCount(); i++) {
                            NBTTagCompound req = failedReqs.getCompoundTagAt(i);
                            if (!req.hasKey(WAILA_FAILED_REQ_KEY, 8)) continue;
                            String key = req.getString(WAILA_FAILED_REQ_KEY);
                            String translated;
                            if (req.hasKey(WAILA_FAILED_REQ_VALUES, 9)) {
                                NBTTagList values = req.getTagList(WAILA_FAILED_REQ_VALUES, 8);
                                Object[] formatValues = new Object[values.tagCount()];
                                for (int j = 0; j < values.tagCount(); j++) {
                                    formatValues[j] = values.getStringTagAt(j);
                                }
                                translated = StatCollector.translateToLocalFormatted(key, formatValues);
                            } else {
                                translated = StatCollector.translateToLocal(key);
                            }
                            information.add(
                                StatCollector
                                    .translateToLocalFormatted(Reference.MOD_ID + "_tooltip.failedReq", translated));
                        }
                    }

                    // write out the stats of the crop if the stats exist
                    ISeedStats stats = seedData.getStats();
                    if (stats.isAnalyzed()) {
                        information.add(
                            StatCollector.translateToLocalFormatted(
                                Reference.MOD_ID + "_tooltip.stats",
                                stats.getGrowth(),
                                stats.getGain(),
                                stats.getResistance()));
                    }
                }
            } else {
                information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.empty"));
            }

            information.add(
                StatCollector.translateToLocalFormatted(
                    Reference.MOD_ID + "_tooltip.soil",
                    formatNumber(nbt.getInteger(Names.NBT.fertilizer)),
                    formatNumber(nbt.getInteger(Names.NBT.water)),
                    formatNumber(nbt.getInteger(Names.NBT.weedEX))));
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
        if (te instanceof TileEntityCropSticks teCrop) {
            List<IGrowthRequirement> failedReqs = teCrop.getFailedChecks();
            if (failedReqs != null && !failedReqs.isEmpty()) {
                NBTTagList failedList = new NBTTagList();
                for (IGrowthRequirement req : failedReqs) {
                    if (req == null) {
                        if (ConfigurationHandler.panicIfNull) {
                            throw new NullPointerException("failed req list contained a null.");
                        }
                        continue;
                    }
                    Pair<String, String[]> unloc = req.getUnlocalizedDescription();
                    if (unloc.getLeft() == null) {
                        if (ConfigurationHandler.panicIfNull) {
                            throw new NullPointerException("failed req had a null lang key.");
                        }
                        continue;
                    }

                    // create the basic structure
                    NBTTagCompound entry = new NBTTagCompound();
                    entry.setString(WAILA_FAILED_REQ_KEY, unloc.getKey());
                    // add loc values if needed
                    if (unloc.getRight() != null && unloc.getRight().length > 0) {
                        NBTTagList locValues = new NBTTagList();
                        for (String value : unloc.getRight()) {
                            locValues.appendTag(new NBTTagString(value));
                        }
                        entry.setTag(WAILA_FAILED_REQ_VALUES, locValues);
                    }

                    // append to list
                    failedList.appendTag(entry);
                }
                tag.setTag(WAILA_FAILED_REQ, failedList);
            }
            tag.setFloat(WAILA_PROGRESS, teCrop.getGrowthPercent());
            te.writeToNBT(tag);
        }
        return tag;
    }
}
