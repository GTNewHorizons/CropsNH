package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import java.util.ArrayList;

import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

public abstract class MethodBaseGrowthReq extends MethodBase {

    public MethodBaseGrowthReq(String name) {
        super(name);
    }

    @Override
    protected boolean appliesToCrop() {
        return true;
    }

    @Override
    protected Object[] onMethodCalled(TileEntityCrop crop) throws MethodException {
        return onMethodCalled(getCropPlant(crop));
    }

    @Override
    protected Object[] onMethodCalled(TileEntityPeripheral peripheral) throws MethodException {
        return onMethodCalled(getCropPlant(peripheral.getSpecimen()));
    }

    protected abstract Object[] onMethodCalled(CropPlant plant) throws MethodException;

    @Override
    protected boolean appliesToPeripheral() {
        return true;
    }

    @Override
    protected boolean requiresJournal() {
        return true;
    }

    @Override
    protected ArrayList<MethodParameter> getParameters() {
        ArrayList<MethodParameter> pars = new ArrayList<>();
        pars.add(MethodParameter.DIRECTION_OPTIONAL);
        return pars;
    }
}
