package com.gtnewhorizon.cropsnh.api.v1;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Register custom stat display methods via APIv1.setStatStringDisplayer
 */
@SideOnly(Side.CLIENT)
public interface IStatStringDisplayer {
    /**
     * Used to display plant stats in tooltips and WAILA
     * @param stat the level of the stat to be displayed
     * @param max the maximum level of the stat
     * @return the String which will be displayed
     */
    String getStatDisplayString(int stat, int max);
}
