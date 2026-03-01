package com.gtnewhorizon.cropsnh.handler;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.gtnhlib.util.map.ItemStackMap;

import cpw.mods.fml.common.IFuelHandler;

public class CropsNHFurnaceFuelHandler implements IFuelHandler {

    public static ItemStackMap<Integer> FUEL_VALUE_MAP = new ItemStackMap<>(true);

    @Override
    public int getBurnTime(ItemStack fuel) {
        return FUEL_VALUE_MAP.getOrDefault(fuel, 0);
    }
}
