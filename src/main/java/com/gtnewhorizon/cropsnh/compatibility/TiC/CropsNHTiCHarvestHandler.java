package com.gtnewhorizon.cropsnh.compatibility.TiC;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;

import tconstruct.api.harvesting.CropHarvestHandler;

public class CropsNHTiCHarvestHandler implements CropHarvestHandler {

    @Override
    public boolean couldHarvest(World world, int x, int y, int z) {
        return world.getTileEntity(x, y, z) instanceof ICropStickTile;
    }

    @Override
    public boolean tryHarvest(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
        if (!(world.getTileEntity(x, y, z) instanceof ICropStickTile crop)) return false;
        // allow weed removal just like a spade would.
        if (crop.hasWeed()) {
            if (crop.isMature()) {
                crop.dropItem(CropsNHUtils.getWeedDrop(1));
            }
            crop.clear();
            return true;
        }
        return crop.doPlayerHarvest();
    }
}
