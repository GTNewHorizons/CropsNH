package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;

public class MethodGetBrightnessRange extends MethodBaseGrowthReq {

    public MethodGetBrightnessRange() {
        super("getBrightnessRange");
    }

    @Override
    protected Object[] onMethodCalled(CropPlant plant) {
        int[] brightnessRange = plant.getGrowthRequirement().getBrightnessRange();
        return new Object[] { brightnessRange[0], brightnessRange[1] };
    }
}
