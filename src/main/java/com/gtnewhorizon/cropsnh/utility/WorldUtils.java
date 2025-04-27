package com.gtnewhorizon.cropsnh.utility;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WorldUtils {

    public static void dropItem(World world, int x, int y, int z, ItemStack drop) {
        double f = 0.7;
        double dx = (double) world.rand.nextFloat() * f + (1.0 - f) * 0.5;
        double dy = (double) world.rand.nextFloat() * f + (1.0 - f) * 0.5;
        double dz = (double) world.rand.nextFloat() * f + (1.0 - f) * 0.5;
        EntityItem entityItem = new EntityItem(world, x + dx, y + dy, z + dz, drop);
        entityItem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityItem);
    }
}
