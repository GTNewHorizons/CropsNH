package com.gtnewhorizon.cropsnh.api;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IMaterialLeafVariant {

    int getId();

    String getUnlocalizedName();

    @SideOnly(Side.CLIENT)
    IIcon getIcon();

    @SideOnly(Side.CLIENT)
    void getTooltip(List list);

    @SideOnly(Side.CLIENT)
    void RegisterIcon(IIconRegister register);

    /**
     * @return a stack representing this material leaf;
     */
    ItemStack get();

    /**
     * @param amount The amount to set the stack to.
     * @return a stack representing this material leaf;
     */
    ItemStack get(int amount);

}
