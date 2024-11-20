package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

public class MethodIsMature extends MethodBaseCrop {

    public MethodIsMature() {
        super("isMature");
    }

    @Override
    protected Object[] onMethodCalled(TileEntityCrop crop) {
        return new Object[] { crop.isMature() };
    }

    @Override
    protected boolean requiresJournal() {
        return false;
    }
}
