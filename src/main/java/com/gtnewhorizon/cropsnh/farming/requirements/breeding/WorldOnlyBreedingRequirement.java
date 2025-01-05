package com.gtnewhorizon.cropsnh.farming.requirements.breeding;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.IMachineBreedingRequirement;

public class WorldOnlyBreedingRequirement implements IMachineBreedingRequirement {

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, TileEntity te, ItemStack[] catalysts) {
        return false;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("cropsnh_breedingReq.lockout.worldOnly");
    }
}
