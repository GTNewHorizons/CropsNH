package powercrystals.minefactoryreloaded.api;

import net.minecraft.world.World;

import java.util.List;


public interface IRandomMobProvider
{
	List<RandomMob> getRandomMobs(World world);
}
