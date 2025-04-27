package com.gtnewhorizon.cropsnh.utility;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public abstract class CropsNHUtils {

    public static void deduplicateItemList(List<ItemStack> stacks) {
        if (stacks.isEmpty()) return;
        // yank out null items for good measure
        stacks.removeIf(Objects::isNull);
        final HashSet<String> deduplicator = new HashSet<>();
        stacks.removeIf(x -> {
            // set the stack size to 1 since this is for soils and block under and they shouldn't show a count.
            x.stackSize = 1;
            return !deduplicator.add(x.toString());
        });
    }

    /**
     * Does its best to turn an item into a block.
     * 
     * @param block the block to convert
     * @return The item or null if none is found.
     */
    public static Item getItemFromBlock(Block block) {
        Item item = Item.getItemFromBlock(block);
        if (item == null) {
            try {
                // should catch things like the skull block
                item = block.getItem(null, 0, 0, 0);
            } catch (Exception ignored) {
                return null;
            }
        }
        return item;
    }

    /**
     * Does its best to turn a block into an item.
     * 
     * @param stack The stack containing the item to convert.
     * @return The block or null if none is found.
     */
    public static Block getBlockFromItem(ItemStack stack) {
        if (stack == null) return null;
        return getBlockFromItem(stack.getItem());
    }

    /**
     * Does its best to turn a block into an item.
     * 
     * @param item the block to convert
     * @return The block or null if none is found.
     */
    public static Block getBlockFromItem(Item item) {
        if (item == null) {
            return null;
        } else if (item instanceof ItemBlock) {
            return ((ItemBlock) item).field_150939_a;
        } else {
            return Block.getBlockFromItem(item);
        }
    }
}
