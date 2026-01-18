package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

/**
 * Used to prevent a crop from growing when the light level is too low.
 */
public class MinLightLevelGrowthRequirement implements IWorldGrowthRequirement {

    private final static String unlocalizedDesc = Reference.MOD_ID + "_growthReq.minLight.format";
    private final int minLightLevel;

    public MinLightLevelGrowthRequirement(int minLightLevel) {
        this.minLightLevel = minLightLevel;
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted(unlocalizedDesc, this.minLightLevel);
    }

    @Override
    public Pair<String, Object[]> getUnlocalizedDescription() {
        return Pair.of(unlocalizedDesc, new Object[] { this.minLightLevel });
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) >= this.minLightLevel;
    }

    @Override
    public boolean onlyPreventsHarvest() {
        return false;
    }
}
