/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.genetics;

import net.minecraftforge.common.EnumPlantType;

import java.util.EnumSet;

public interface IAllelePlantType extends IAllele {

	EnumSet<EnumPlantType> getPlantTypes();

}
