package com.gtnewhorizon.cropsnh.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.CropsNHItemList;
import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMug extends Item {

    public static final String[] textureNames = { "stoneMug", "coffeeCold", "coffeeDark", "coffee" };
    public IIcon[] icons;

    private static final DamageSource HEART_ATTACK = new DamageSource(Reference.MOD_ID + ":heartAttack")
        .setDamageBypassesArmor()
        .setDamageIsAbsolute();

    public static final int META_STONE_MUG = 0;
    public static final int META_COLD_COFFEE = 1;
    public static final int META_DARK_COFFEE = 2;
    public static final int META_COFFEE = 3;

    public ItemMug() {
        super();
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.setMaxStackSize(1);
        RegisterHelper.registerItem(this, "mug");
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        int meta = CropsNHUtils.getItemMeta(stack);
        // abort early if bad
        if (meta <= 0 || meta >= textureNames.length) return stack;
        int maxLevel = switch (meta) {
            case META_COLD_COFFEE -> 1;
            case META_DARK_COFFEE -> 5;
            default -> 6;
        };
        int upgradeTime = meta == META_COLD_COFFEE ? 600 : 1200;
        int buffMax = Math.max(
            increaseCaffeineLevels(player, Potion.digSpeed, maxLevel, upgradeTime),
            increaseCaffeineLevels(player, Potion.moveSpeed, maxLevel, upgradeTime));

        // do heart attack if nessesary
        if (meta == META_COFFEE) buffMax -= 2;
        if (buffMax > 2) player.addPotionEffect(new PotionEffect(Potion.confusion.id, (buffMax - 2) * 200, 0));
        if (buffMax > 3) player.attackEntityFrom(HEART_ATTACK, 6 << (buffMax - 3));

        return CropsNHItemList.emptyMug.get(1);
    }

    /**
     * May be unhealthy for you, but early mining with haste 6 is nice, given you can find brewing stand.
     *
     * @param player      The player to caffeinate
     * @param potion      The effect to increase
     * @param maxLevel    The max level to increase the effect to
     * @param upgradeTime The amount of time to add to the effect if it wasn't already there.
     * @return The new level of the effect.
     */
    private static int increaseCaffeineLevels(EntityPlayer player, Potion potion, int maxLevel, int upgradeTime) {
        PotionEffect effect = player.getActivePotionEffect(potion);
        if (effect == null) {
            player.addPotionEffect(new PotionEffect(potion.id, 300, 0));
            return 0;
        }
        int level = Math.min(effect.getAmplifier() + 1, maxLevel);
        player.addPotionEffect(new PotionEffect(potion.id, effect.getDuration() + upgradeTime, level));
        return Math.max(level, effect.getAmplifier());
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = CropsNHUtils.getItemMeta(itemstack);
        if (meta >= textureNames.length) {
            meta = 0;
        }
        return "item." + Reference.MOD_ID + ":" + textureNames[meta];
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 30;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return CropsNHUtils.getItemMeta(stack) == META_STONE_MUG ? EnumAction.none : EnumAction.drink;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (CropsNHUtils.getItemMeta(itemStackIn) != META_STONE_MUG)
            player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < 4; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta < textureNames.length) return icons[meta];
        else return icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[textureNames.length];
        for (int i = 0; i < textureNames.length; i++) {
            this.icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + textureNames[i]);
        }
    }
}
