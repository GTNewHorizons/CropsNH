package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IMachineBreedingRequirement extends IBreedingRequirement {

    boolean canBreed(ArrayList<ICropCard> parents, TileEntity te, ItemStack[] catalysts);
}
