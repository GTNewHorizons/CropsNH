package com.gtnewhorizon.cropsnh.api;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.reference.Constants;

public abstract class BasePlantRenderShape implements IPlantRenderShape {

    /**
     * Adds a vertex to the tessellator scaled to the unit size of a block.
     *
     * @param tessellator the Tessellator instance used for rendering
     * @param x           the x position, from 0 to 1
     * @param y           the y position, from 0 to 1
     * @param z           the z position, from 0 to 1
     * @param u           u offset for the bound texture
     * @param v           v offset for the bound texture
     * @param icon        the icon to render
     */
    protected static void addScaledVertexWithUV(Tessellator tessellator, float x, float y, float z, float u, float v,
        IIcon icon) {
        tessellator.addVertexWithUV(x * Constants.UNIT, y * Constants.UNIT, z * Constants.UNIT, u, v);
    }
}
