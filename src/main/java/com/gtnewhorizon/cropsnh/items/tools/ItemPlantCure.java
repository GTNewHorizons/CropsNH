package com.gtnewhorizon.cropsnh.items.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.creativetab.CropsNHTab;
import com.gtnewhorizon.cropsnh.utility.LogHelper;
import com.gtnewhorizon.cropsnh.utility.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlantCure extends Item implements ICropRightClickHandler {

    private final static int MAX_USES = 100;

    public ItemPlantCure() {
        this.setCreativeTab(CropsNHTab.cropsNHTab);
        this.maxStackSize = 1;
        this.hasSubtypes = false;
        this.setMaxDamage(MAX_USES);
        RegisterHelper.registerItem(this, "plantCure");
    }

    @Override
    public boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (world.isRemote) return true;
        if (te.cureDisease()) {
            // add a bit of fertilizer so it doesn't fall sick again for a bit.
            te.addFertilizer(25, 25, 25, false);
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
