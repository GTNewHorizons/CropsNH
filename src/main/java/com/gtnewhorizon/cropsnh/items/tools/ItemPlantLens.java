package com.gtnewhorizon.cropsnh.items.tools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.ISeedData;
import com.gtnewhorizon.cropsnh.items.ItemCropsNH;
import com.gtnewhorizon.cropsnh.reference.Names;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
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
        list.add(StatCollector.translateToLocal(Reference.MOD_ID + "_tooltip." + Names.Objects.plantLens));
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

        if (!world.isRemote) {
            te.updateState();
            return true;
        }

        // display some info text to the player about the crop if it's on the client side.
        if (te instanceof TileEntityCrop) {
            List<String> info = new ArrayList<>();
            ((TileEntityCrop) te).getPlantLensStatus(info);
            for (String line : info) {
                player.addChatComponentMessage(new ChatComponentText(line));
            }
        }
        return true;
    }
}
