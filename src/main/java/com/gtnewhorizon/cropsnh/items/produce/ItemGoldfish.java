package com.gtnewhorizon.cropsnh.items.produce;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.handler.ConfigurationHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGoldfish extends ItemFood {

    public ItemGoldfish() {
        super(1, 2, false);
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setMaxStackSize(64);
        RegisterHelper.registerItem(this, Names.Objects.goldfish);
    }

    @SideOnly(value = Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("cropsnh:goldfish");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.goldfish"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (!ConfigurationHandler.putAnEndToExistentialDread) {
            player.playSound(ConfigurationHandler.goldfishScream, 5, (float) 0.5);
        }
        return true;
    }

    @Override
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
        if (!ConfigurationHandler.putAnEndToExistentialDread) {
            p_77654_3_.playSound(ConfigurationHandler.goldfishScream, 5, (float) 0.5);
        }
        return super.onEaten(p_77654_1_, p_77654_2_, p_77654_3_);
    }
}
