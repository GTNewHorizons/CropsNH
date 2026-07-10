package com.gtnewhorizon.cropsnh.items.produce;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.compatibility.ic2.IC2CompatHandler;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Mods;

public class ItemGaiaWart extends Item {

    /** How much to reduce radiation effects by when eaten. */
    private static final int RADIATION_REDUCTION = 30 * 20;

    public ItemGaiaWart() {
        this.setCreativeTab(CreativeTabs.tabFood);
        RegisterHelper.registerItem(this, Names.Objects.gaiaWart);
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.item." + Names.Objects.gaiaWart));
    }

    @SideOnly(value = Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + Names.Objects.gaiaWart);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.eat;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        --stack.stackSize;
        world.playSoundAtEntity(player, "random.burp", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
        player.removePotionEffect(Potion.confusion.id);
        player.removePotionEffect(Potion.digSlowdown.id);
        player.removePotionEffect(Potion.hunger.id);
        player.removePotionEffect(Potion.moveSlowdown.id);
        player.removePotionEffect(Potion.weakness.id);
        player.removePotionEffect(Potion.blindness.id);
        player.removePotionEffect(Potion.poison.id);
        player.removePotionEffect(Potion.wither.id);
        // reduces radiation effects by 30 seconds (600 ticks)
        if (Mods.IndustrialCraft2.isModLoaded()) {
            IC2CompatHandler.reduceRadiationTimer(player, RADIATION_REDUCTION);
        }
        // TODO: ADD NUCLEAR HORIZONS GAIA WART COMPAT WHEN IT'S READY
        return stack;
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }
}
