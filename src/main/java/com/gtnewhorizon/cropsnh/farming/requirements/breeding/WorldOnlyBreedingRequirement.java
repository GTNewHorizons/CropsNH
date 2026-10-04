package com.gtnewhorizon.cropsnh.farming.requirements.breeding;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public class WorldOnlyBreedingRequirement implements IMachineBreedingRequirement {

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, IGregTechTileEntity te, ItemStack[] catalysts,
        int[] consumationTracker) {
        return true;
    }

    @Override
    public @Nullable List<ItemStack> getMachineOnlyCatalystsForNEI() {
        return null;
    }

    @Override
    public String getDescription() {
        return this.getDescriptionForMutationDump();
    }

    @Override
    public @NotNull String getDescriptionForMutationDump() {
        return StatCollector.translateToLocal(Reference.MOD_ID + "_breedingReq.lockout.worldOnly");
    }
}
