package com.gtnewhorizon.cropsnh.utility;

import static kubatech.api.utils.ItemUtils.writeItemStackToNBT;

import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.gtnhlib.util.map.ItemStackMap;

public abstract class NBTHelper {

    public static NBTTagCompound getMaterialTag(ItemStack stack) {
        NBTTagCompound tag = null;
        if (stack != null && stack.getItem() != null) {
            String name = Block.blockRegistry.getNameForObject(((ItemBlock) stack.getItem()).field_150939_a);
            if (name != null && !name.equals("")) {
                tag = new NBTTagCompound();
                tag.setString(Names.NBT.material, name);
                tag.setInteger(Names.NBT.materialMeta, stack.getItemDamage());
            }
        }
        return tag;
    }

    public static boolean listContainsStack(NBTTagList list, ItemStack stack) {
        if (stack == null || stack.getItem() == null) {
            return true;
        }
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tagAtIndex = list.getCompoundTagAt(i);
            ItemStack stackAtIndex = ItemStack.loadItemStackFromNBT(tagAtIndex);
            if (stackAtIndex == null || stackAtIndex.getItem() == null) {
                continue;
            }
            if (stack.getItem() == stackAtIndex.getItem() && stack.getItemDamage() == stackAtIndex.getItemDamage()) {
                return true;
            }
        }
        return false;
    }

    public static void addCoordsToNBT(int[] coords, NBTTagCompound tag) {
        if (coords != null && coords.length == 3) {
            addCoordsToNBT(coords[0], coords[1], coords[2], tag);
        }
    }

    public static void addCoordsToNBT(int x, int y, int z, NBTTagCompound tag) {
        tag.setInteger(Names.NBT.x, x);
        tag.setInteger(Names.NBT.y, y);
        tag.setInteger(Names.NBT.z, z);
    }

    public static int[] getCoordsFromNBT(NBTTagCompound tag) {
        int[] coords = null;
        if (tag.hasKey(Names.NBT.x) && tag.hasKey(Names.NBT.y) && tag.hasKey(Names.NBT.z)) {
            coords = new int[] { tag.getInteger(Names.NBT.x), tag.getInteger(Names.NBT.y),
                tag.getInteger(Names.NBT.z) };
        }
        return coords;
    }

    public static void sortStacks(NBTTagList list) {
        // clear empty tags from the list
        clearEmptyStacksFromNBT(list);
        // if the list has no or one stack, nothing has to be sorted
        if (list.tagCount() < 2) {
            return;
        }
        // sort the stacks in a new array
        ItemStack[] array = new ItemStack[list.tagCount()];
        ItemStack first;
        int index;
        for (int i = 0; i < array.length; i++) {
            index = 0;
            first = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(index));
            // find the next stack in the list
            for (int j = 0; j < list.tagCount(); j++) {
                ItemStack stackAtIndex = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(j));
                String firstName = first.getDisplayName()
                    .equalsIgnoreCase("Seeds") ? "Wheat Seeds" : first.getDisplayName();
                String stackAtIndexName = stackAtIndex.getDisplayName()
                    .equalsIgnoreCase("Seeds") ? "Wheat Seeds" : stackAtIndex.getDisplayName();
                if (firstName.compareToIgnoreCase(stackAtIndexName) > 0) {
                    first = stackAtIndex;
                    index = j;
                }
            }
            // set the stack at this index to the next stack in order
            array[i] = first;
            // remove the stack at this index from the list
            list.removeTag(index);
        }
        // add all the stacks in order to the list
        for (ItemStack stack : array) {
            NBTTagCompound tag = new NBTTagCompound();
            stack.writeToNBT(tag);
            list.appendTag(tag);
        }
    }

    public static void clearEmptyStacksFromNBT(NBTTagList list) {
        for (int i = list.tagCount() - 1; i >= 0; i--) {
            ItemStack stack = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(i));
            if (stack == null || stack.getItem() == null) {
                list.removeTag(i);
            }
        }
    }

    public static boolean getBoolean(NBTTagCompound tag, String key, boolean def) {
        if (tag == null || !tag.hasKey(key, Data.NBTType._boolean)) return def;
        return tag.getBoolean(key);
    }

    public static int getInteger(NBTTagCompound tag, String key, int def) {
        if (tag == null || !tag.hasKey(key, Data.NBTType._int)) return def;
        return tag.getInteger(key);
    }

    /**
     * Gets a number as number as long as it stored as number
     * 
     * @param tag The tag that should contain the key.
     * @param key The key to look for.
     * @param def The default value.
     * @return the value or default if it wasn't found.
     */
    public static long getIntgegerNumber(NBTTagCompound tag, String key, long def) {
        if (tag == null) return def;
        switch (tag.func_150299_b(key)) {
            case Data.NBTType._byte:
                return tag.getByte(key);
            case Data.NBTType._short:
                return tag.getShort(key);
            case Data.NBTType._int:
                return tag.getInteger(key);
            case Data.NBTType._long:
                return tag.getLong(key);
            default:
                return def;
        }
    }

    /**
     * Gets a number as long as that number can be converted to a double (this includes integers)
     * 
     * @param tag The tag that should contain the key.
     * @param key The key to look for.
     * @param def The default value.
     * @return the value or default if it wasn't found.
     */
    public static double getFloatingNumber(NBTTagCompound tag, String key, double def) {
        if (tag == null) return def;
        switch (tag.func_150299_b(key)) {
            case Data.NBTType._byte:
                return tag.getByte(key);
            case Data.NBTType._short:
                return tag.getShort(key);
            case Data.NBTType._int:
                return tag.getInteger(key);
            case Data.NBTType._long:
                return tag.getLong(key);
            case Data.NBTType._float:
                return tag.getFloat(key);
            case Data.NBTType._double:
                return tag.getDouble(key);
            default:
                return def;
        }
    }

    private static final String NBT_ITEM_STACK_MAP_KEY = "key";
    private static final String NBT_ITEM_STACK_MAP_VALUE = "value";

    public static NBTTagList saveItemStackMap(ItemStackMap<Integer> map) {
        NBTTagList nbt = new NBTTagList();
        for (Map.Entry<ItemStack, Integer> entry : map.entrySet()) {
            NBTTagCompound entryNBT = new NBTTagCompound();
            entryNBT.setTag(NBT_ITEM_STACK_MAP_KEY, writeItemStackToNBT(entry.getKey()));
            entryNBT.setDouble(NBT_ITEM_STACK_MAP_VALUE, entry.getValue());
            nbt.appendTag(entryNBT);
        }
        return nbt;
    }

    public static void loadItemStackMap(ItemStackMap<Integer> map, NBTTagList nbt,
        BiFunction<Integer, Integer, Integer> merger) {
        for (int i = 0; i < nbt.tagCount(); i++) {
            NBTTagCompound entry = nbt.getCompoundTagAt(i);
            if (entry.hasNoTags()) continue;
            ItemStack stack = ItemStack.loadItemStackFromNBT(entry.getCompoundTag(NBT_ITEM_STACK_MAP_KEY));
            if (stack == null) continue;
            int value = getInteger(entry, NBT_ITEM_STACK_MAP_VALUE, 1);
            map.merge(stack, value, merger);
        }
    }

}
