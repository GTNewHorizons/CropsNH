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
import net.minecraftforge.common.util.Constants;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.api.IGrowthRequirement;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.api.ISeedStats;
import com.gtnewhorizon.cropsnh.crops.CropMigrator;
import com.gtnewhorizon.cropsnh.farming.SeedData;
import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.SpecialChars;

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
            ISeedData seedData = nbt.hasKey(Names.NBT.crop, Constants.NBT.TAG_COMPOUND)
                ? new SeedData(nbt.getCompoundTag(Names.NBT.crop))
                : teCrop.getSeed();
            if (seedData != null) {
                if (seedData.getCrop() instanceof CropMigrator
                    && nbt.hasKey(Names.NBT.extra, Constants.NBT.TAG_COMPOUND)) {
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
        // order should always be:
        // 1. what's planted
        // 2. crop stats
        // 3. soil data
        // 4. what's wrong
        // 5. progress
        TileEntity te = dataAccessor.getTileEntity();
        NBTTagCompound nbt = dataAccessor.getNBTData();
        if (te instanceof TileEntityCropSticks teCrop) {
            String soilLine = StatCollector.translateToLocalFormatted(
                Reference.MOD_ID + "_tooltip.plantLens.soil",
                formatNumber(nbt.getInteger(Names.NBT.fertilizer)),
                formatNumber(nbt.getInteger(Names.NBT.water)),
                formatNumber(nbt.getInteger(Names.NBT.weedEX)));
            if (teCrop.hasCrop()) {
                // parse seed data
                ISeedData seedData = nbt.hasKey(Names.NBT.crop, Constants.NBT.TAG_COMPOUND)
                    ? new SeedData(nbt.getCompoundTag(Names.NBT.crop))
                    : teCrop.getSeed();
                boolean showFullProgress = true;
                if (teCrop.hasWeed()) {
                    showFullProgress = false;
                    information.add(soilLine);
                    information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.plantLens.weeds"));
                } else {
                    // no need to add name, the waila header will take care of that.

                    // add soil info
                    information.add(soilLine);

                    String sickLine = nbt.getBoolean(Names.NBT.sick)
                        ? StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.plantLens.isSick")
                        : null;
                    // write out the identifying information if analyzed
                    ISeedStats stats = seedData.getStats();
                    if (stats.isAnalyzed()) {
                        // add stats
                        information.add(
                            StatCollector.translateToLocalFormatted(
                                Reference.MOD_ID + "_tooltip.plantLens.stats",
                                stats.getGrowth(),
                                stats.getGain(),
                                stats.getResistance()));

                        // warn if sick
                        if (sickLine != null) {
                            information.add(sickLine);
                        }

                        // add failed growth requirements
                        if (nbt.hasKey(WAILA_FAILED_REQ, Constants.NBT.TAG_LIST)) {
                            NBTTagList failedReqs = nbt.getTagList(WAILA_FAILED_REQ, Constants.NBT.TAG_COMPOUND);
                            for (int i = 0; i < failedReqs.tagCount(); i++) {
                                NBTTagCompound req = failedReqs.getCompoundTagAt(i);
                                if (!req.hasKey(WAILA_FAILED_REQ_KEY, Constants.NBT.TAG_STRING)) continue;
                                String key = req.getString(WAILA_FAILED_REQ_KEY);
                                String translated;
                                if (req.hasKey(WAILA_FAILED_REQ_VALUES, Constants.NBT.TAG_LIST)) {
                                    NBTTagList values = req
                                        .getTagList(WAILA_FAILED_REQ_VALUES, Constants.NBT.TAG_STRING);
                                    Object[] formatValues = new Object[values.tagCount()];
                                    for (int j = 0; j < values.tagCount(); j++) {
                                        formatValues[j] = values.getStringTagAt(j);
                                    }
                                    translated = StatCollector.translateToLocalFormatted(key, formatValues);
                                } else {
                                    translated = StatCollector.translateToLocal(key);
                                }
                                information.add(
                                    StatCollector.translateToLocalFormatted(
                                        Reference.MOD_ID + "_tooltip.waila.cropStick.failedReq",
                                        translated));
                            }
                        }
                    } else {
                        showFullProgress = false;
                        // warn if sick
                        if (sickLine != null) {
                            information.add(sickLine);
                        }
                        information.add(
                            StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.genericSeed.scanToViewStats"));
                    }
                }
                // draw progress bar
                information.add(
                    SpecialChars.getRenderString(
                        "waila.cropsnh.cropStick.progress",
                        String.valueOf(nbt.getInteger(Names.NBT.progress)),
                        String.valueOf(
                            seedData.getCrop()
                                .getGrowthDuration()),
                        showFullProgress ? "1" : "0"));
            } else {
                // notify player of empty crop stick
                information.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.plantLens.empty"));
                // add soil info
                information.add(soilLine);
            }

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
