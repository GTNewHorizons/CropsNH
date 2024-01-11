package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

public interface IMethod {

    String getName();

    Object[] call(TileEntityPeripheral peripheral, World world, int x, int y, int z, ItemStack journal, Object... args)
            throws MethodException;

    String getDescription();

    String signature();
}
