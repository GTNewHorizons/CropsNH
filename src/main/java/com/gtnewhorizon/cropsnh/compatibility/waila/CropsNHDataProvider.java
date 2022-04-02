package com.gtnewhorizon.cropsnh.compatibility.waila;

import com.gtnewhorizon.cropsnh.blocks.BlockCropsNH;
import com.gtnewhorizon.cropsnh.tileentity.TileEntityCropsNH;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class CropsNHDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
    	if(dataAccessor.getBlock() instanceof BlockCropsNH) {
    		BlockCropsNH block = (BlockCropsNH)dataAccessor.getBlock();
    		TileEntityCropsNH tea = dataAccessor.getTileEntity() instanceof TileEntityCropsNH ? (TileEntityCropsNH)dataAccessor.getTileEntity() : null;
    		return block.getWailaStack(block,tea);
    	} else {
    		return null;
    	}
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        TileEntity te = dataAccessor.getTileEntity();
        if(te!=null && te instanceof TileEntityCropsNH) {
            ((TileEntityCropsNH)te).addWailaInformation(list);
        }
        return list;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
    	if (te != null) {
            te.writeToNBT(tag);
    	}
        return tag;
    }
}
