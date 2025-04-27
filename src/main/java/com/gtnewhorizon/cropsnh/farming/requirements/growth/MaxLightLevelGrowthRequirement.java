package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.Tuple2;

/**
 * Used to prevent a crop from growing if the light level is too high.
 */
public class MaxLightLevelGrowthRequirement implements IWorldGrowthRequirement {

    private final static String unlocalizedDesc = "cropsnh_growthReq.maxLight.format";
    private final int maxLightLevel;

    public MaxLightLevelGrowthRequirement(int maxLightLevel) {
        this.maxLightLevel = maxLightLevel;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted(unlocalizedDesc, this.maxLightLevel);
    }

    @Override
    public Tuple2<String, Object[]> getUnlocalisedDescription() {
        return new Tuple2<>(unlocalizedDesc, new Object[] { this.maxLightLevel });
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) <= this.maxLightLevel;
    }
}
