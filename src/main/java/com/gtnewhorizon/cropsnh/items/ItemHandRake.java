package com.gtnewhorizon.cropsnh.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Tool to uproot weeds.
 * Comes in a wooden and iron variant.
 */
public class ItemHandRake extends ItemCropsNH implements ICropRightClickHandler {

    private static final int WOOD_VARIANT_META = 0;
    private static final int IRON_VARIANT_META = 1;
    private static final int[] dropChance = new int[] { 10, 25 };

    private final IIcon[] icons = new IIcon[2];

    public ItemHandRake() {
        super();
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.handRake;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, WOOD_VARIANT_META));
        list.add(new ItemStack(item, 1, IRON_VARIANT_META));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String base = super.getUnlocalizedName(itemStack);
        if (itemStack.getItemDamage() == WOOD_VARIANT_META) {
            return base + ".wood";
        } else if (itemStack.getItemDamage() == IRON_VARIANT_META) {
            return base + ".iron";
        } else {
            throw new IllegalArgumentException(
                "Unsupported meta value of " + itemStack.getItemDamage() + " for ItemHandRake.");
        }
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.handRake"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        LogHelper.debug("registering icon for: " + this.getUnlocalizedName());
        icons[0] = reg.registerIcon(
            this.getUnlocalizedName()
                .substring(
                    this.getUnlocalizedName()
                        .indexOf('.') + 1)
                + "_wood");
        icons[1] = reg.registerIcon(
            this.getUnlocalizedName()
                .substring(
                    this.getUnlocalizedName()
                        .indexOf('.') + 1)
                + "_iron");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta <= 1) {
            return this.icons[meta];
        }
        return null;
    }

    @Override
    public boolean onRightClick(ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (!te.hasWeed()) return false;
        te.clear();
        // TODO: RE-ADD WEED DROP ON WEED REMOVAL
        return true;
    }

}
