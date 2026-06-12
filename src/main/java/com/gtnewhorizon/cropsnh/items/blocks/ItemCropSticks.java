package com.gtnewhorizon.cropsnh.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropRightClickHandler;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHBlocks;

public class ItemCropSticks extends ItemBlockCropsNH implements ICropRightClickHandler {

    /**
     * The default constructor.
     * A super call to this is generally all that is needed in subclasses.
     *
     * @param block the block associated with this item.
     */
    public ItemCropSticks(Block block) {
        super(block);
    }

    // I'm overriding this just to be sure
    @Override
    public boolean canItemEditBlocks() {
        return true;
    }

    // this is called when you right-click with this item in hand
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        // check if we are targeting the top side of a valid soil block
        if (side != 1 || !SoilRegistry.instance.isRegistered(world, x, y, z)
            || !world.canPlaceEntityOnSide(CropsNHBlocks.blockCropSticks, x, y + 1, z, false, 0, player, stack)) {
            return false;
        }

        // you can shift-right-click to place a cross.
        boolean isPlacingCross = player.isSneaking() && (player.capabilities.isCreativeMode || stack.stackSize >= 2);

        world.setBlock(x, y + 1, z, CropsNHBlocks.blockCropSticks);

        if (!world.isRemote) {
            // play the placement sound effect.
            world.playSoundEffect(
                ((float) x + 0.5F),
                ((float) y + 0.5F),
                ((float) z + 0.5F),
                Blocks.planks.stepSound.func_150496_b(),
                (Blocks.planks.stepSound.getVolume() + 1.0F) / 2.0F,
                Blocks.planks.stepSound.getPitch() * 0.8F);
            // consume items if not in creative mode
            if (!player.capabilities.isCreativeMode) {
                stack.stackSize -= isPlacingCross ? 2 : 1;
            }
        }

        // upgrade it if necessary
        if (isPlacingCross && world.getTileEntity(x, y + 1, z) instanceof ICropStickTile crop) {
            crop.setCrossCrop(true);
            world.markBlockForUpdate(x, y + 1, z);
        }
        return true;
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
