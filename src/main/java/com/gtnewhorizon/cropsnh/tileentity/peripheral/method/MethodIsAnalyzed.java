package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import java.util.ArrayList;

import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.tileentity.peripheral.TileEntityPeripheral;

public class MethodIsAnalyzed extends MethodBase {

    public MethodIsAnalyzed() {
        super("isAnalyzed");
    }

    @Override
    protected boolean appliesToCrop() {
        return true;
    }

    @Override
    protected Object[] onMethodCalled(TileEntityCrop crop) throws MethodException {
        return new Object[] { crop.isAnalyzed() };
    }

    @Override
    protected boolean appliesToPeripheral() {
        return true;
    }

    @Override
    protected Object[] onMethodCalled(TileEntityPeripheral peripheral) throws MethodException {
        return new Object[] { peripheral.isSpecimenAnalyzed() };
    }

    @Override
    protected boolean requiresJournal() {
        return false;
    }

    @Override
    protected ArrayList<MethodParameter> getParameters() {
        return new ArrayList<>();
    }
}
