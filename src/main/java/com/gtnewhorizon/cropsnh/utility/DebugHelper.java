package com.gtnewhorizon.cropsnh.utility;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * A class to aid in the management of debug data.
 */
public abstract class DebugHelper {

    /**
     * Retrieves the debug data for a location, and displays it in a chat message to the specified player in conjunction
     * with the log.
     *
     * @param player the player requesting the debug data.
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public static void debug(EntityPlayer player, World world, int x, int y, int z) {
        for (String dataLine : getDebugData(world, x, y, z)) {
            LogHelper.debug(dataLine);
            // player.addChatComponentMessage(new ChatComponentText(dataLine));
        }
    }

    /**
     * Constructs a list of strings representing the debug information for the provided location.
     *
     * @param world
     * @param x
     * @param y
     * @param z
     * @return a list of strings representing the requested debug data.
     */
    private static List<String> getDebugData(World world, int x, int y, int z) {

        List<String> debugData = new ArrayList<>();

        if (!world.isRemote) {
            debugData.add("Server debug info:");
            debugData.add("------------------");
        } else {
            debugData.add("Client debug info:");
            debugData.add("------------------");
        }

        TileEntity tile = world.getTileEntity(x, y, z);

        debugData.add("Block: " + Block.blockRegistry.getNameForObject(world.getBlock(x, y, z)));
        debugData.add("Meta: " + world.getBlockMetadata(x, y, z));

        debugData.add(" ");

        return debugData;
    }

    public static String dumpStack(ItemStack stack, boolean omitCount) {
        StringBuilder sb = new StringBuilder();
        // count
        if (!omitCount) {
            sb.append(stack.stackSize);
            sb.append(" x ");
        }
        // item
        sb.append(stack.getDisplayName());
        // nbt
        if (stack.hasTagCompound()) {
            sb.append(stack.stackTagCompound.toString());
        }
        return sb.toString();
    }

    public static String makeCSVLine(Object... cols) {
        StringBuilder sb = new StringBuilder();
        for (Object col : cols) {
            sb.append(sb.length() == 0 ? "" : ",");
            // skip contents if null
            if (col == null) continue;

            if (col instanceof Boolean) {
                sb.append((Boolean) col ? "TRUE" : "FALSE");
            } else {
                sb.append(sanitizeCSVString(col.toString()));
            }
        }
        return sb.toString();
    }

    public static String sanitizeCSVString(String value) {
        // yes, this is severely over-engineered.
        if (value.indexOf(',') != -1 || value.indexOf('"') != -1
            || value.indexOf('\r') != -1
            || value.indexOf('\n') != -1) {
            // " get escaped as ""
            // new lines needs to be escaped by being putting the entire field in quotes
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
