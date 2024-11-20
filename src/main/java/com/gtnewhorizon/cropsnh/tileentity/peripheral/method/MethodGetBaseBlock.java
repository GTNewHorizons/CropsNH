package com.gtnewhorizon.cropsnh.tileentity.peripheral.method;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.api.v1.BlockWithMeta;
import com.gtnewhorizon.cropsnh.farming.cropplant.CropPlant;

public class MethodGetBaseBlock extends MethodBaseGrowthReq {

    public MethodGetBaseBlock() {
        super("getBaseBlock");
    }

    @Override
    protected Object[] onMethodCalled(CropPlant plant) {
        if (plant == null) {
            return null;
        }
        BlockWithMeta block = plant.getGrowthRequirement()
            .getRequiredBlock();
        String msg = block == null ? "null" : (new ItemStack(block.getBlock(), 1, block.getMeta())).getDisplayName();
        return new Object[] { msg };
    }
}
