package com.gtnewhorizon.cropsnh.items.produce;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBerry extends ItemFood {

    public static final int META_HUCKLE = 0;
    public static final int META_SUGARBEET = 1;
    public static final int META_MAX_TOMATO = 2;

    private static final String[] BERRY_NAMES = new String[] { "huckle", "sugarbeet", "maxTomato" };

    public IIcon[] icons;

    public ItemBerry() {
        super(1, 0.4F, false);
        this.setCreativeTab(CreativeTabs.tabFood);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setMaxStackSize(64);
        RegisterHelper.registerItem(this, "berry");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        boolean canEat = switch (CropsNHUtils.getItemMeta(stack)) {
            case META_SUGARBEET -> player.canEat(true) && player.getFoodStats()
                .getSaturationLevel() < 18F;
            default -> player.canEat(false);
        };

        if (canEat) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }

    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (CropsNHUtils.getItemMeta(stack) == META_MAX_TOMATO) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 100, true));
            }
        }
    }

    @Override
    public int func_150905_g(ItemStack itemStackIn) {
        return switch (CropsNHUtils.getItemMeta(itemStackIn)) {
            case 2 -> 9;
            default -> super.func_150905_g(itemStackIn);
        };
    }

    @Override
    public float func_150906_h(ItemStack itemStackIn) {
        return switch (CropsNHUtils.getItemMeta(itemStackIn)) {
            case 2 -> 1.0F;
            default -> super.func_150906_h(itemStackIn);
        };
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
        this.icons = new IIcon[BERRY_NAMES.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":berry_" + BERRY_NAMES[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "item." + Reference.MOD_ID
            + ":berry."
            + BERRY_NAMES[Math.min(BERRY_NAMES.length - 1, Math.max(0, CropsNHUtils.getItemMeta(itemstack)))];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < BERRY_NAMES.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        int meta = CropsNHUtils.getItemMeta(stack);
        switch (meta) {
            case META_HUCKLE:
            case META_SUGARBEET:
                list.add(
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.berry." + BERRY_NAMES[meta] + ".1"));
                list.add(
                    StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.berry." + BERRY_NAMES[meta] + ".2"));
                break;
            case META_MAX_TOMATO:
                list.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.berry." + BERRY_NAMES[meta]));
                break;
        }
    }

}
