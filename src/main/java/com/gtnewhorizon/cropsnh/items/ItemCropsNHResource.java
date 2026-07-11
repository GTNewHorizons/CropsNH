package com.gtnewhorizon.cropsnh.items;

public class ItemCropsNHResource extends ItemCropsNH {

    private final String internalName;

    public ItemCropsNHResource(String name) {
        super(name);
        this.internalName = name;
    }

    @Override
    protected String getInternalName() {
        return this.internalName;
    }

}
