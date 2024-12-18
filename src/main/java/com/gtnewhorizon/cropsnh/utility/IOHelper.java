package com.gtnewhorizon.cropsnh.utility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.BlockWithMeta;

import cpw.mods.fml.common.registry.GameRegistry;

// helper class to read, write and parse data to and from the config files
public abstract class IOHelper {

    public static String getModId(ItemStack stack) {
        if (stack == null || stack.getItem() == null) {
            return null;
        }
        String name = Item.itemRegistry.getNameForObject(stack.getItem());
        int split = name.indexOf(':');
        if (split >= 0) {
            name = name.substring(0, split);
        }
        return name;
    }

    /**
     * Retrieves a a block from a string representation.
     * The string must be formatted as "modid:name:meta".
     * The meta is not required in all cases.
     *
     * @param input the string representation of the block to get.
     * @return the block as a blockWithMeta, or null.
     */
    public static BlockWithMeta getBlock(String input) {
        String[] data = input.split(":");
        if (data.length <= 1) {
            return null;
        }
        int meta = data.length == 3 ? Integer.parseInt(data[2]) : -1;
        Block block = GameRegistry.findBlock(data[0], data[1]);
        if (block == null) {
            return null;
        }
        return meta < 0 ? new BlockWithMeta(block) : new BlockWithMeta(block, meta);
    }

}
