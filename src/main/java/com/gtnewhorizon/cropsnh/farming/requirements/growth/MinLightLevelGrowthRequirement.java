package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.Tuple2;

/**
 * Used to prevent a crop from growing when the light level is too low.
 */
public class MinLightLevelGrowthRequirement implements IWorldGrowthRequirement {

    private final static String unlocalizedDesc = "cropsnh_growthReq.minLight.format";
    private final int minLightLevel;

    public MinLightLevelGrowthRequirement(int minLightLevel) {
        this.minLightLevel = minLightLevel;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted(unlocalizedDesc, this.minLightLevel);
    }

    @Override
    public Tuple2<String, Object[]> getUnlocalisedDescription() {
        return new Tuple2<>(unlocalizedDesc, new Object[] { this.minLightLevel });
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) >= this.minLightLevel;
    }
}
