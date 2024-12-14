package com.gtnewhorizon.cropsnh.farming.growthrequirements;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;

/**
 * Used to prevent a crop from growing when the light level is too low.
 */
public class MinLightLevelRequirement implements IWorldGrowthRequirement {

    private final int minLightLevel;

    public MinLightLevelRequirement(int minLightLevel) {
        this.minLightLevel = minLightLevel;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted("cropsnh_growthReq.minLight.format", minLightLevel);
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) >= minLightLevel;
    }
}
