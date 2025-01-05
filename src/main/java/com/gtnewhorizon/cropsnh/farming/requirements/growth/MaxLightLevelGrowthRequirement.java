package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;

/**
 * Used to prevent a crop from growing if the light level is too high.
 */
public class MaxLightLevelGrowthRequirement implements IWorldGrowthRequirement {

    private final int maxLightLevel;

    public MaxLightLevelGrowthRequirement(int maxLightLevel) {
        this.maxLightLevel = maxLightLevel;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted("cropsnh_growthReq.maxLight.format", maxLightLevel);
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) <= maxLightLevel;
    }
}
