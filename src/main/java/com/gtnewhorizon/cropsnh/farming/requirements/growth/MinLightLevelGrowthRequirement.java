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
 * Used to prevent a crop from growing when the light level is too low.
 */
public class MinLightLevelGrowthRequirement implements IWorldGrowthRequirement {

    private final Pair<String, String[]> unlocalizedDesc;
    private final int minLightLevel;

    public MinLightLevelGrowthRequirement(int minLightLevel) {
        this.minLightLevel = minLightLevel;
        this.unlocalizedDesc = Pair
            .of(Reference.MOD_ID + "_growthReq.minLight.format", new String[] { formatNumber(this.minLightLevel) });
    }

    @Override
    public @NotNull Pair<@NotNull String, @Nullable String[]> getUnlocalizedDescription() {
        return this.unlocalizedDesc;
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
