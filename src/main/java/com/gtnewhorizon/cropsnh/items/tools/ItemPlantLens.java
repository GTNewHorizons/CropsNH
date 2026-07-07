package com.gtnewhorizon.cropsnh.items.tools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.items.ItemCropsNH;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropSticks;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlantLens extends ItemCropsNH implements ICropRightClickHandler {

    public ItemPlantLens() {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    protected String getInternalName() {
        return Names.Objects.plantLens;
    }

    // I'm overriding this just to be sure
    @Override
    public boolean canItemEditBlocks() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip.item." + Names.Objects.plantLens));
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
    public boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        // analyze the crop if there is a crop
        if (te.hasCrop()) {
            ISeedData seedData = te.getSeed();
            seedData.setAnalyzed(true);
        }

        // client shouldn't be reading out the data since the client data for crops isn't always up to date.
        if (world.isRemote) {
            return true;
        }

        // just in case some weird thing happened on the server, mark it as dirty for good measure.
        te.updateState();

        // display some info text to the player about the crop if it's on the client side.
        if (te instanceof TileEntityCropSticks cropTE) {
            List<IChatComponent> info = new ArrayList<>();
            cropTE.getPlantLensStatus(info);
            for (IChatComponent line : info) {
                player.addChatComponentMessage(line);
            }
        }
        return true;
    }
}
