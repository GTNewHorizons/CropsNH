package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class MachineOnlyGrowthRequirement implements IWorldGrowthRequirement {

    private final Pair<String, Object[]> unlocalizedDesc;
    private final Pair<String, Object[]> unlocalizedDescForNEI;

    public MachineOnlyGrowthRequirement() {
        this.unlocalizedDesc = Pair.of(Reference.MOD_ID + "_growthReq.lockout.machineOnly", new Object[] {});
        this.unlocalizedDescForNEI = Pair.of(Reference.MOD_ID + "_growthReq.lockout.machineOnly.nei", new Object[] {});
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted(this.unlocalizedDesc.getLeft(), this.unlocalizedDesc.getRight());
    }

    @Override
    public String getDescriptionForNEI() {
        return StatCollector
            .translateToLocalFormatted(this.unlocalizedDescForNEI.getLeft(), this.unlocalizedDescForNEI.getRight());
    }

    @Override
    public Pair<String, Object[]> getUnlocalizedDescription() {
        return this.unlocalizedDesc;
    }

    @Override
    public Pair<String, Object[]> getUnlocalizedDescriptionForNEI() {
        return this.unlocalizedDescForNEI;
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean onlyPreventsHarvest() {
        return true;
    }
}
