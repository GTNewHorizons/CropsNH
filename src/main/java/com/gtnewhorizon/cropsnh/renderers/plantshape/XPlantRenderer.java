package com.gtnewhorizon.cropsnh.renderers.plantshape;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.BasePlantRenderShape;

public class XPlantRenderer extends BasePlantRenderShape {

    @Override
    public String getName() {
        return "X";
    }

    public void render(Tessellator tessellator, IIcon icon) {
        final int minY = 0;
        final int maxY = 12;
        final int minX = 2;
        final int maxX = 14;
        final int minZ = 2;
        final int maxZ = 14;

        float minU = icon.getMinU();
        float minV = icon.getMinV();
        float maxU = icon.getMaxU();
        float maxV = icon.getMaxV();

        // spotless:off
        // plane 1 front
        addScaledVertexWithUV(tessellator, minX, minY, minZ, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, minZ, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, maxZ, minU, minV, icon);
        addScaledVertexWithUV(tessellator, maxX, minY, maxZ, minU, maxV, icon);
        // plane 1 back
        addScaledVertexWithUV(tessellator, maxX, minY, maxZ, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, maxZ, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, minZ, minU, minV, icon);
        addScaledVertexWithUV(tessellator, minX, minY, minZ, minU, maxV, icon);
        // plane 2 front
        addScaledVertexWithUV(tessellator, minX, minY, maxZ, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, maxX, minY, minZ, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, minZ, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, maxZ, minU, minV, icon);
        // plane 2 front
        addScaledVertexWithUV(tessellator, maxX, minY, minZ, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, minX, minY, maxZ, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, maxZ, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, minZ, minU, minV, icon);
        // spotless:on
    }
}
