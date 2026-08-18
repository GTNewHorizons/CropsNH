package com.gtnewhorizon.cropsnh.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * The root Item class for all CropsNH Items (excluding blockItems).
 */
public abstract class ItemCropsNH extends Item {

    public ItemCropsNH(String name) {
        this(name, true);
    }

    public ItemCropsNH() {
        this(true);
    }

    public ItemCropsNH(String name, boolean doInit) {
        super();
        this.init(name, doInit);
    }

    public ItemCropsNH(boolean doInit) {
        super();
        this.init(this.getInternalName(), doInit);
    }

    private void init(String name, boolean doInit) {
        if (!doInit) return;
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.setMaxStackSize(64);
        if (Reference.IS_GAME_LOADED) {
            RegisterHelper.registerItem(this, name);
        }
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
