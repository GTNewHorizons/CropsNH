package com.gtnewhorizon.cropsnh.tileentity.decoration;

import com.gtnewhorizon.cropsnh.blocks.BlockFence;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCustomWood;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityFence extends TileEntityCustomWood {
    public TileEntityFence() {
        super();
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    public boolean canConnect(ForgeDirection dir) {
        if(this.worldObj == null) {
            return false;
        }
        Block block = this.getBlockType();
        return ((BlockFence) block).canConnect(worldObj, xCoord, yCoord, zCoord, dir);
    }
}
