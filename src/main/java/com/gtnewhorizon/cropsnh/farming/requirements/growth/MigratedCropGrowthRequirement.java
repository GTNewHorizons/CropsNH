package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class MigratedCropGrowthRequirement implements IWorldGrowthRequirement {

    private final Pair<String, Object[]> unlocalizedDesc;

    public MigratedCropGrowthRequirement() {
        this.unlocalizedDesc = Pair.of(Reference.MOD_ID + "_growthReq.lockout.migrated", new Object[] {});
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted(this.unlocalizedDesc.getLeft(), this.unlocalizedDesc.getRight());
    }

    @Override
    public Pair<String, Object[]> getUnlocalizedDescription() {
        return this.unlocalizedDesc;
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
