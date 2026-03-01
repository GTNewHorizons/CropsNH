package com.gtnewhorizon.cropsnh.items.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.loaders.FertilizerLoader;
import com.gtnewhorizon.cropsnh.reference.Constants;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWeedEX extends Item implements ICropRightClickHandler {

    private final static int POTENCY_PER_USE = 10;
    private final static int THRESHOLD = 90;
    private final static int MAX_STORAGE = 100;
    public final static int MAX_USES = Constants.WEEDEX_CAPACITY / FertilizerLoader.WEEDEX_POTENCY / POTENCY_PER_USE;

    public ItemWeedEX() {
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.maxStackSize = 1;
        this.hasSubtypes = false;
        this.setMaxDamage(MAX_USES);
        RegisterHelper.registerItem(this, "weedEX");
    }

    @Override
    public boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (world.isRemote) return true;
        if (te.addWeedEx(POTENCY_PER_USE, THRESHOLD, MAX_STORAGE, false)) {
            if (!player.capabilities.isCreativeMode) {
                heldItem.damageItem(1, player);
            }
        }
        return true;
    }

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
