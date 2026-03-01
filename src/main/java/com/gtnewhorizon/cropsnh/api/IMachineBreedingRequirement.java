package com.gtnewhorizon.cropsnh.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public interface IMachineBreedingRequirement extends IBreedingRequirement {

    public boolean canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack[] catalysts,
        int[] consumptionTracker);

    public @Nullable List<ItemStack> getMachineOnlyCatalystsForNEI();
}
