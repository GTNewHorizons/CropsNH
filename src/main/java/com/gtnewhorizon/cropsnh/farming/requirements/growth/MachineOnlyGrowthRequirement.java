package com.gtnewhorizon.cropsnh.farming.requirements.growth;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.cropsnh.api.ICropStickTile;
import com.gtnewhorizon.cropsnh.api.IWorldGrowthRequirement;
import com.gtnewhorizon.cropsnh.utility.Tuple2;

public class MachineOnlyGrowthRequirement implements IWorldGrowthRequirement {

    private final Tuple2<String, Object[]> unlocalizedDesc;

    public MachineOnlyGrowthRequirement() {
        this.unlocalizedDesc = new Tuple2<>("cropsnh_growthReq.lockout.machineOnly", new Object[] {});
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocalFormatted(this.unlocalizedDesc.item1, this.unlocalizedDesc.item2);
    }

    @Override
    public Tuple2<String, Object[]> getUnlocalisedDescription() {
        return this.unlocalizedDesc;
    }

    @Override
    public boolean canGrow(World world, ICropStickTile tile, int x, int y, int z) {
        return false;
    }
}
