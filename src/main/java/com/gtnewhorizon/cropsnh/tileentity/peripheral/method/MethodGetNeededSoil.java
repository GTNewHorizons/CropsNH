package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;

public class MethodGetNeededSoil extends MethodBaseGrowthReq {

    public MethodGetNeededSoil() {
        super("getNeededSoil");
    }

    @Override
    protected Object[] onMethodCalled(CropPlant plant) {
        BlockWithMeta block = plant.getGrowthRequirement().getSoil();
        String msg = block == null ? "null" : (new ItemStack(block.getBlock(), 1, block.getMeta())).getDisplayName();
        return new Object[] { msg };
    }
}
