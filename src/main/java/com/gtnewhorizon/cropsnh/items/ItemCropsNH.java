package com.gtnewhorizon.cropsnh.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * The root Item class for all CropsNH Items (excluding blockItems).
 */
public abstract class ItemCropsNH extends Item {

    public ItemCropsNH(String name) {
        super();
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.setMaxStackSize(64);
        RegisterHelper.registerItem(this, name);
    }

    public ItemCropsNH() {
        super();
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.setMaxStackSize(64);
        RegisterHelper.registerItem(this, getInternalName());
    }

    protected abstract String getInternalName();

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        LogHelper.debug("registering icon for: " + this.getUnlocalizedName());
        itemIcon = reg.registerIcon(
            this.getUnlocalizedName()
                .substring(
                    this.getUnlocalizedName()
                        .indexOf('.') + 1));
    }
}
