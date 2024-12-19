package com.gtnewhorizon.cropsnh.items.produce;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModifier extends Item {

    public IIcon[] icons;
    public String[] textureNames = new String[] { "space", "magic", "NaNCertificate" };

    public ItemModifier() {
        super();
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.bFull3D = false;
        this.hasSubtypes = true;
        GameRegistry.registerItem(this, "modifier");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icons[Math.min(this.icons.length - 1, Math.max(0, meta))];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String name = this.textureNames[Math.min(this.textureNames.length - 1, Math.max(0, stack.getItemDamage()))];
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.modifier." + name));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[this.textureNames.length];

        for (int i = 0; i < this.textureNames.length; ++i) {
            this.icons[i] = iconRegister.registerIcon("cropsnh:modifier_" + textureNames[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "item.cropsnh:modifier."
            + this.textureNames[Math.min(this.textureNames.length - 1, Math.max(0, itemstack.getItemDamage()))];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < this.textureNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
