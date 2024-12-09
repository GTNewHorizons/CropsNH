package com.gtnewhorizon.cropsnh.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.Blocks;
import com.gtnewhorizon.cropsnh.renderers.items.RenderItemBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCropSticks extends ItemBlockCropsNH implements ICropRightClickHandler {

    private IIcon icon;

    /**
     * The default constructor.
     * A super call to this is generally all that is needed in subclasses.
     *
     * @param block the block associated with this item.
     */
    public ItemCropSticks(Block block) {
        super(block);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public RenderItemBase getItemRenderer() {
        return null;
    }

    // I'm overriding this just to be sure
    @Override
    public boolean canItemEditBlocks() {
        return true;
    }

    // this is called when you right click with this item in hand
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            // check if we are targetting the top side of the block and wether
            if (side != 1 || !SoilRegistry.instance.isRegistered(world, x, y, z)) return false;

            // you can shift-right-click to place a cross.
            boolean isPlacingCross = player.isSneaking()
                && (player.capabilities.isCreativeMode || stack.stackSize >= 2);

            // place the crop stick block
            world.setBlock(x, y + 1, z, Blocks.blockCrop);
            // upgrade it if necessary
            if (isPlacingCross) {
                TileEntity te = world.getTileEntity(x, y, z);
                if (te instanceof ICropStickTile) {
                    ((ICropStickTile) te).setCrossCrop(true);
                }
            }

            // play the placement sound effect.
            world.playSoundEffect(
                ((float) x + 0.5F),
                ((float) y + 0.5F),
                ((float) z + 0.5F),
                net.minecraft.init.Blocks.leaves.stepSound.func_150496_b(),
                (net.minecraft.init.Blocks.leaves.stepSound.getVolume() + 1.0F) / 2.0F,
                net.minecraft.init.Blocks.leaves.stepSound.getPitch() * 0.8F);

            // consume items if not in creative mode
            if (!player.capabilities.isCreativeMode) {
                stack.stackSize -= isPlacingCross ? 2 : 1;
            }
        }
        // default to false or else the player won't be able to place crop sticks
        return false;
    }

    @Override
    public boolean onRightClick(World world, ICropStickTile te, EntityPlayer player, ItemStack heldItem) {
        if (world.isRemote) return true;
        // ignore if the held item is bad or if the crop sticks can't be upgraded.
        if (heldItem == null || heldItem.stackSize < 1 || !te.canUpgrade()) return false;
        // upgrade the crop stick to a cross
        te.setCrossCrop(true);
        // consume items if necessary
        heldItem.stackSize -= player.capabilities.isCreativeMode ? 0 : 1;
        return true;
    }
}
