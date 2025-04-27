package com.gtnewhorizon.cropsnh.items;

import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;

public class ItemFertilizer extends ItemCropsNH {

    @Override
    protected String getInternalName() {
        return "fertilizer";
    }

    @Override
    public RenderItemBase getItemRenderer() {
        return null;
    }
}
