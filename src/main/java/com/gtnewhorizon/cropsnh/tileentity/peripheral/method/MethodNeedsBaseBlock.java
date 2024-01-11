package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import com.gtnewhorizon.cropsnh.api.v1.RequirementType;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;

public class MethodNeedsBaseBlock extends MethodBaseGrowthReq {

    public MethodNeedsBaseBlock() {
        super("needsBaseBlock");
    }

    @Override
    protected Object[] onMethodCalled(CropPlant plant) throws MethodException {
        return new Object[] { plant.getGrowthRequirement().getRequiredType() != RequirementType.NONE };
    }

    @Override
    protected boolean requiresJournal() {
        return true;
    }
}
