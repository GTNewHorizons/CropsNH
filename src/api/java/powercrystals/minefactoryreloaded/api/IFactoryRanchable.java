package powercrystals.minefactoryreloaded.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author PowerCrystals
 *
 * Defines a ranchable entity for use in the Rancher.
 */
public interface IFactoryRanchable
{
	/**
	 * @return The entity being ranched. Must be a subtype of EntityLivingBase.
	 */
    Class<? extends EntityLivingBase> getRanchableEntity();

	/**
	 * @param world The world this entity is in.
	 * @param entity The entity instance being ranched.
	 * @param rancher The rancher instance doing the ranching. Used to access the Rancher's inventory when milking cows, for example.
	 * @return A list of drops.
	 */
    List<RanchedItem> ranch(World world, EntityLivingBase entity, IInventory rancher);
}
