package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import java.util.ArrayList;

import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

public class MethodHasJournal extends MethodBasePeripheral {

    public MethodHasJournal() {
        super("hasJournal");
    }

    @Override
    protected Object[] onMethodCalled(TileEntityPeripheral peripheral) throws MethodException {
        return new Object[] { peripheral.getJournal() != null };
    }

    @Override
    protected ArrayList<MethodParameter> getParameters() {
        return new ArrayList<>();
    }
}
