package com.gtnewhorizon.cropsnh.utility;

import static kubatech.api.utils.ItemUtils.writeItemStackToNBT;

import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.gtnewhorizon.cropsnh.reference.Data;
import com.gtnewhorizon.gtnhlib.util.map.ItemStackMap;

public abstract class NBTHelper {

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
