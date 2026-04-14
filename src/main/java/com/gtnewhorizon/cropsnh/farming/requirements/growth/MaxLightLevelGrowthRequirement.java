package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import static com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil.formatNumber;

import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.reference.Reference;

/**
 * Used to prevent a crop from growing if the light level is too high.
 */
public class MaxLightLevelGrowthRequirement implements IWorldGrowthRequirement {

    private final Pair<String, String[]> unlocalizedDesc;
    private final int maxLightLevel;

    public MaxLightLevelGrowthRequirement(int maxLightLevel) {
        this.maxLightLevel = maxLightLevel;
        this.unlocalizedDesc = Pair
            .of(Reference.MOD_ID + "_growthReq.maxLight.format", new String[] { formatNumber(this.maxLightLevel) });
    }

    @Override
    public @NotNull Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescription() {
        return unlocalizedDesc;
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) <= this.maxLightLevel;
    }

    @Override
    public boolean onlyPreventsHarvest() {
        return false;
    }
}
