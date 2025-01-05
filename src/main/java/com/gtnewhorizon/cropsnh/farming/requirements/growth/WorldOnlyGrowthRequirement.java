package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;

public class WorldOnlyGrowthRequirement implements IWorldGrowthRequirement {

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("cropsnh_growthReq.lockout.machineOnly");
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return false;
    }
}
