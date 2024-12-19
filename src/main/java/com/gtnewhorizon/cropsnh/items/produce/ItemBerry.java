package com.gtnewhorizon.cropsnh.items.produce;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBerry extends ItemFood {

    public IIcon[] icons;
    public String[] textureNames = new String[] { "huckle", "sugarbeet" };

    public ItemBerry() {
        super(1, 0.4F, false);
        this.setCreativeTab(CreativeTabs.tabFood);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setMaxStackSize(64);
        RegisterHelper.registerItem(this, "berry");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if (player.canEat(true) && player.getFoodStats()
            .getSaturationLevel() < 18F) {
            player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 16;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        return icons[meta];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon("cropsnh:berry_" + textureNames[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "item.cropsnh:berry."
            + textureNames[Math.min(textureNames.length - 1, Math.max(0, itemstack.getItemDamage()))];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < textureNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String name = textureNames[Math.min(textureNames.length - 1, Math.max(0, stack.getItemDamage()))];
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.berry." + name + ".1"));
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.berry." + name + ".2"));
    }

}
