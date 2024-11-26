package com.gtnewhorizon.cropsnh.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTerraWart extends ItemFood {

    public ItemTerraWart() {
        super(0, 1.0f, false);
        this.setAlwaysEdible();
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setMaxStackSize(64);
        RegisterHelper.registerItem(this, Names.Objects.terraWart);
    }

    @SideOnly(value = Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("cropsnh:terraWart");
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
        --itemstack.stackSize;
        world.playSoundAtEntity(player, "random.burp", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
        player.removePotionEffect(Potion.confusion.id);
        player.removePotionEffect(Potion.digSlowdown.id);
        player.removePotionEffect(Potion.hunger.id);
        player.removePotionEffect(Potion.moveSlowdown.id);
        player.removePotionEffect(Potion.weakness.id);
        player.removePotionEffect(Potion.blindness.id);
        player.removePotionEffect(Potion.poison.id);
        player.removePotionEffect(Potion.wither.id);
        // TODO: REIMPLEMENT RADIATION REDUCTION ONCE NUCLEAR HORIZONS IS IMPLEMENTED
        // should reduce effect duration by 600
        return itemstack;
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }
}
