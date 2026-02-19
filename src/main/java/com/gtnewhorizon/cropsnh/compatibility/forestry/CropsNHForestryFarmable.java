package com.gtnewhorizon.cropsnh.compatibility.forestry;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.collect.ImmutableList;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCrop;
import com.gtnewhorizon.cropsnh.tileentity.singleblock.MTECropManager;

import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import forestry.core.utils.vect.IVect;
import forestry.core.utils.vect.Vect;
import forestry.farming.logic.Crop;

public class CropsNHForestryFarmable implements IFarmable {

    public static class CropBasicCropsNHCrop extends Crop {

        private final ICropStickTile cropTE;

        public CropBasicCropsNHCrop(World world, ICropStickTile tileEntity, Vect position) {
            super(world, position);
            this.cropTE = tileEntity;
        }

        @Override
        protected boolean isCrop(IVect pos) {
            return cropTE.canHarvest();
        }

        private static final Collection<ItemStack> NO_DROPS = new ImmutableList.Builder<ItemStack>().build();

        @Override
        protected Collection<ItemStack> harvestBlock(IVect pos) {
            Collection<ItemStack> ret = cropTE.harvest();
            return ret == null ? NO_DROPS : ret;
        }
    }

    @Override
    public boolean isSaplingAt(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof ICropStickTile crop) {
            crop.addWater(100, 100, MTECropManager.WATER_CAP, false);
            crop.addFertilizer(100, 100, MTECropManager.FERTILIZER_CAP, false);
            return true;
        }
        return false;
    }

    @Override
    public ICrop getCropAt(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (!(te instanceof TileEntityCrop crop)) return null;
        if (!crop.canHarvest()) return null;
        return new CropBasicCropsNHCrop(world, crop, new Vect(x, y, z));
    }

    @Override
    public boolean isGermling(ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean isWindfall(ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean plantSaplingAt(EntityPlayer player, ItemStack germling, World world, int x, int y, int z) {
        return false;
    }
}
