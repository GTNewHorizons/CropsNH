package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.container.ContainerSeedAnalyzer;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

public class MethodGetSpecimen extends MethodBasePeripheral {

    public MethodGetSpecimen() {
        super("getSpecimen");
    }

    @Override
    protected Object[] onMethodCalled(TileEntityPeripheral peripheral) throws MethodException {
        ItemStack stack = peripheral.getStackInSlot(ContainerSeedAnalyzer.seedSlotId);
        return new Object[] { stack == null ? null : stack.getDisplayName() };
    }
}
