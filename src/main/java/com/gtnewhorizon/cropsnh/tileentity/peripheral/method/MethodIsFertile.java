package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

public class MethodIsFertile extends MethodBaseCrop {

    public MethodIsFertile() {
        super("isFertile");
    }

    @Override
    protected Object[] onMethodCalled(TileEntityCrop crop) {
        return new Object[] { crop.isFertile() };
    }

    @Override
    protected boolean requiresJournal() {
        return false;
    }
}
