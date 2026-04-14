package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

public class MigratedCropGrowthRequirement implements IWorldGrowthRequirement {

    private final Pair<String, String[]> unlocalizedDesc;

    public MigratedCropGrowthRequirement() {
        this.unlocalizedDesc = Pair.of(Reference.MOD_ID + "_growthReq.lockout.migrated", null);
    }

    @Override
    public @NotNull Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescription() {
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
