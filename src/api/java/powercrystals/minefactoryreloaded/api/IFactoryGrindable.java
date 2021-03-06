package powercrystals.minefactoryreloaded.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author PowerCrystals
 *
 * Defines a grindable entity for the Grinder.
 */
public interface IFactoryGrindable
{
	/**
	 * @return The class that this grindable instance is handling. This must be a subtype of EntityLivingBase or the entity will never
	 * be noticed by the Grinder.
	 */
    Class<? extends EntityLivingBase> getGrindableEntity();

	/**
	 * @param world The world this entity is in.
	 * @param entity The entity instance being ground.
	 * @param random A Random instance.
	 * @return The drops generated when this entity is killed.
	 */
    List<MobDrop> grind(World world, EntityLivingBase entity, Random random);

	/**
	 * @param entity The entity instance being ground.
	 * @return Whether this entity has been fully processed or not.
	 */
    boolean processEntity(EntityLivingBase entity);
}
