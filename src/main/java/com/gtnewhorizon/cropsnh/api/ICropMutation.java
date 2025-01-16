package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface ICropMutation {

    ICropCard getOutput();

    Collection<ICropCard> getParents();

    boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z);

    boolean canBreed(ArrayList<ICropCard> parents, TileEntity te, ItemStack[] catalysts);

    List<IBreedingRequirement> getRequirements();

    List<ItemStack> getBlocksUnderForNEI(boolean useCache);
}
