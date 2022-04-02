package com.gtnewhorizon.cropsnh.entity;

import com.gtnewhorizon.cropsnh.blocks.BlockFence;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityLeashKnotCropsNH extends EntityLeashKnot {
    @SuppressWarnings("unused")
    public EntityLeashKnotCropsNH(World world) {
        super(world);
    }

    public EntityLeashKnotCropsNH(World world, int x, int y, int z) {
        super(world, x, y, z);
    }

    public boolean onValidSurface() {
        return super.onValidSurface() || this.worldObj.getBlock(this.field_146063_b, this.field_146064_c, this.field_146062_d) instanceof BlockFence;
    }

    public static EntityLeashKnotCropsNH getKnotForBlock(World world, int x, int y, int z) {
        List list = world.getEntitiesWithinAABB(EntityLeashKnotCropsNH.class, AxisAlignedBB.getBoundingBox((double) x - 1.0D, (double) y - 1.0D, (double) z - 1.0D, (double) x + 1.0D, (double) y + 1.0D, (double) z + 1.0D));
        if (list != null) {
            for (Object obj : list) {
                EntityLeashKnotCropsNH entityleashknot = (EntityLeashKnotCropsNH) obj;
                if (entityleashknot.field_146063_b == x && entityleashknot.field_146064_c == y && entityleashknot.field_146062_d == z) {
                    return entityleashknot;
                }
            }
        }
        return null;
    }

    public static EntityLeashKnotCropsNH func_110129_a(World world, int x, int y, int z) {
        EntityLeashKnotCropsNH entityleashknot = new EntityLeashKnotCropsNH(world, x, y, z);
        entityleashknot.forceSpawn = true;
        world.spawnEntityInWorld(entityleashknot);
        return entityleashknot;
    }
}
