package com.gtnewhorizon.cropsnh.farming.requirements.breeding;

import java.util.ArrayList;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropCard;
import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldBreedingRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class MachineOnlyBreedingRequirement implements IWorldBreedingRequirement {

    @Override
    public boolean canBreed(ArrayList<ICropCard> parents, World world, ICropStickTile tile, int x, int y, int z) {
        return false;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal(Reference.MOD_ID + "_breedingReq.lockout.machineOnly");
    }

}
