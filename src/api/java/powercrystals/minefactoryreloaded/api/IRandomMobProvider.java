package powercrystals.minefactoryreloaded.api;

import java.util.List;

import net.minecraft.world.World;

public interface IRandomMobProvider {

    List<RandomMob> getRandomMobs(World world);
}
