package com.gtnewhorizon.cropsnh.renderers.plantshape;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.BasePlantRenderShape;

public class HashPlantRenderer extends BasePlantRenderShape {

    @Override
    public String getName() {
        return "Hash";
    }

    public void render(Tessellator tessellator, IIcon icon) {
        int minY = 0;
        int maxY = 16;

        float minU = icon.getMinU();
        float minV = icon.getMinV();
        float maxU = icon.getMaxU();
        float maxV = icon.getMaxV();

        // spotless:on
        // plane 1 front
        addScaledVertexWithUV(tessellator, 0, minY, 4, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 4, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 4, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 4, minU, maxV, icon);
        // plane 1 back
        addScaledVertexWithUV(tessellator, 0, minY, 4, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 4, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 4, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 4, maxU, minV, icon);
        // plane 2 front
        addScaledVertexWithUV(tessellator, 4, minY, 0, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 4, minY, 16, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 16, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 0, minU, minV, icon);
        // plane 2 back
        addScaledVertexWithUV(tessellator, 4, minY, 0, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 0, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 16, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 4, minY, 16, maxU, maxV, icon);
        // plane 3 front
        addScaledVertexWithUV(tessellator, 0, minY, 12, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 12, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 12, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 12, minU, minV, icon);
        // plane 3 back
        addScaledVertexWithUV(tessellator, 0, minY, 12, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 12, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 12, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 12, maxU, maxV, icon);
        // plane 4 front
        addScaledVertexWithUV(tessellator, 12, minY, 16, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 12, minY, 0, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 0, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 16, minU, minV, icon);
        // plane 4 back
        addScaledVertexWithUV(tessellator, 12, minY, 16, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 16, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 0, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 12, minY, 0, maxU, maxV, icon);
        // spotless:off
    }
}
