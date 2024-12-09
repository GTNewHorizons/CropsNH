package com.gtnewhorizon.cropsnh.items.tools;

import java.util.List;

import com.gtnewhorizon.cropsnh.items.ItemCropsNH;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropLeftClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.objects.XSTR;

/**
 * Tool to uproot weeds.
 * Comes in a wooden and iron variant.
 */
public class ItemSpade extends ItemCropsNH implements ICropLeftClickHandler, ICropRightClickHandler {

    public ItemSpade() {
        super();
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }


    @Override
    protected String getInternalName() {
        return Names.Objects.spade;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal("cropsnh_tooltip.spade"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        LogHelper.debug("registering icon for: " + this.getUnlocalizedName());
        this.itemIcon = reg.registerIcon(
            this.getUnlocalizedName()
                .substring(
                    this.getUnlocalizedName()
                        .indexOf('.') + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return null;
    }

    @Override
    public boolean onLeftClick(ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        return this.doWork(te, true);

    }

    @Override
    public boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (world.isRemote) return true;
        return this.doWork(te, false);
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    private boolean doWork(ICropStickTile te, boolean clearAfter) {
        // if it's weeds clear them
        if (te.hasWeed()) {
            // drop tall grass if it's mature
            if (te.isMature()) {
                te.dropItem(new ItemStack(Blocks.tallgrass, 1));
            }
            te.clear();
            return true;
        }
        // else only drop seeds if we can harvest the crops.
        if (te.doPlayerHarvest()) {
            // get the seeds
            ItemStack seedDrop = te.getSeedStack();
            if (seedDrop != null) {
                seedDrop.stackSize = getSeedCount(
                    te.getStats()
                        .getResistance());
                if (seedDrop.stackSize > 0) {
                    te.dropItem(seedDrop);
                }
            }
            if (clearAfter) te.clear();
            return true;
        }
        return true;
    }

    private static int getSeedCount(int resist) {
        // every 10 resil you get 1 more seed
        int seedCount = resist / 10;
        // and then you get a percent chance based on the resil eg 11 = 1 seed + 10% for a 2nd
        seedCount += XSTR.XSTR_INSTANCE.nextInt(10) < (resist % 10) ? 1 : 0;
        return seedCount;
    }
}
