package com.gtnewhorizon.cropsnh.items;

public class ItemCropsNHResource extends ItemCropsNH {

    private final String mInternalName;

    public ItemCropsNHResource(String name) {
        super(name);
        this.mInternalName = name;
    }

    @Override
    protected String getInternalName() {
        return this.mInternalName;
    }

}
