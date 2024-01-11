package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;

public class MethodGetPlant extends MethodBaseCrop {

    public MethodGetPlant() {
        super("getPlant");
    }

    @Override
    protected Object[] onMethodCalled(TileEntityCrop crop) {
        return new Object[] { crop.getSeedStack().getDisplayName() };
    }
}
