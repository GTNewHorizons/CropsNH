package com.gtnewhorizon.cropsnh.items;

import net.minecraft.creativetab.CreativeTabs;

import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNugget extends ItemCropsNH {

    private final String name;

    public ItemNugget(String name) {
        super("nugget" + name);
        this.name = "nugget" + name;
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    protected String getInternalName() {
        return name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return null;
    }
}
