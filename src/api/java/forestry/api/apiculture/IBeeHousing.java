/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import com.mojang.authlib.GameProfile;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IErrorLogicSource;
import forestry.api.genetics.IHousing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBeeHousing extends IHousing, IErrorLogicSource {

	Iterable<IBeeModifier> getBeeModifiers();
	Iterable<IBeeListener> getBeeListeners();

	IBeeHousingInventory getBeeInventory();
	IBeekeepingLogic getBeekeepingLogic();

	EnumTemperature getTemperature();
	EnumHumidity getHumidity();
	int getBlockLightValue();
	boolean canBlockSeeTheSky();

	World getWorld();
	BiomeGenBase getBiome();
	GameProfile getOwner();
}
