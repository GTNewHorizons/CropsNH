package com.gtnewhorizon.cropsnh.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.blocks.abstracts.BlockCropsNH;
import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * The base class for all CropsNH container blocks.
 */
public abstract class BlockContainerCropsNH extends BlockCropsNH implements ITileEntityProvider {

    /**
     * The default constructor.
     *
     * @param material the material the block is composed of.
     */
    protected BlockContainerCropsNH(Material material) {
        super(material);
        registerTileEntity();
    }

    /**
     * Attempts to register the TileEntity for this block. If the process fails, an error is printed to the log via
     * {@link LogHelper#printStackTrace(Exception)}.
     */
    private void registerTileEntity() {
        try {
            TileEntity tile = this.createNewTileEntity(null, 0);
            if (tile != null) {
                Class<? extends TileEntity> tileClass = tile.getClass();
                GameRegistry.registerTileEntity(tileClass, wrapName(getTileEntityName()));
            }
        } catch (Exception e) {
            LogHelper.printStackTrace(e);
        }
    }

    /**
     * Retrieves the name of the TileEntity to this container block.
     *
     * @return the name of the block's TileEntity.
     */
    protected abstract String getTileEntityName();

    public static String wrapName(String name) {
        return Reference.MOD_ID + ':' + name;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void breakBlock(World world, int x, int y, int z, Block b, int meta) {
        super.breakBlock(world, x, y, z, b, meta);
        world.removeTileEntity(x, y, z);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int data) {
        super.onBlockEventReceived(world, x, y, z, id, data);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(id, data);
    }

    public abstract boolean isMultiBlock();
}
