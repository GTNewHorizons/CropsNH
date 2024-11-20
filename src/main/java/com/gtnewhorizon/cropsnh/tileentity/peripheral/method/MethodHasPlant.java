package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

public class MethodHasPlant extends MethodBaseCrop {

    public MethodHasPlant() {
        super("hasPlant");
    }

    @Override
    protected Object[] onMethodCalled(TileEntityCrop crop) {
        return new Object[] { crop.hasPlant() };
    }
}
