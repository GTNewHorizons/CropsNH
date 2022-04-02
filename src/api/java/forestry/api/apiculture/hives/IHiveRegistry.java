/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture.hives;

import forestry.api.apiculture.IHiveDrop;

import java.util.List;

public interface IHiveRegistry {

	/* Forestry Hive Names */
    String forest = "Forestry:forest";
	String meadows = "Forestry:meadows";
	String desert = "Forestry:desert";
	String jungle = "Forestry:jungle";
	String end = "Forestry:end";
	String snow = "Forestry:snow";
	String swamp = "Forestry:swamp";

	/**
	 * Adds a new hive to be generated in the world.
	 */
	void registerHive(String hiveName, IHiveDescription hiveDescription);

	/**
	 * Add drops to a registered hive.
	 */
	void addDrops(String hiveName, IHiveDrop... drops);
	void addDrops(String hiveName, List<IHiveDrop> drop);
}
