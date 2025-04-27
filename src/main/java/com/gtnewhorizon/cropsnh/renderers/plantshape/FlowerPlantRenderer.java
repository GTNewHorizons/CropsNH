package com.gtnewhorizon.cropsnh.renderers.plantshape;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.api.BasePlantRenderShape;

public class FlowerPlantRenderer extends BasePlantRenderShape {

    @Override
    public String getName() {
        return "Flower";
    }

    public void render(Tessellator tessellator, IIcon icon) {
        int minY = 0;
        int maxY = 12;

        float minU = icon.getMinU();
        float minV = icon.getMinV();
        float maxU = icon.getMaxU();
        float maxV = icon.getMaxV();

        // spotless:off
        // plane 1 front right
        addScaledVertexWithUV(tessellator,       6, minY,  4.001F, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,       6, maxY,  4.001F, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,      18, maxY,  4.001F, minU, minV, icon);
        addScaledVertexWithUV(tessellator,      18, minY,  4.001F, minU, maxV, icon);
        // plane 1 front left
        addScaledVertexWithUV(tessellator,      -2, minY,  3.999F, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,      -2, maxY,  3.999F, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,      10, maxY,  3.999F, minU, minV, icon);
        addScaledVertexWithUV(tessellator,      10, minY,  3.999F, minU, maxV, icon);
        // plane 1 back right
        addScaledVertexWithUV(tessellator,       6, minY,  4.001F, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,      18, minY,  4.001F, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,      18, maxY,  4.001F, minU, minV, icon);
        addScaledVertexWithUV(tessellator,       6, maxY,  4.001F, maxU, minV, icon);
        // plane 1 back left
        addScaledVertexWithUV(tessellator,      -2, minY,  3.999F, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,      10, minY,  3.999F, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,      10, maxY,  3.999F, minU, minV, icon);
        addScaledVertexWithUV(tessellator,      -2, maxY,  3.999F, maxU, minV, icon);

        // plane 2 front right
        addScaledVertexWithUV(tessellator,  3.999F, minY,       6, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,  3.999F, minY,      18, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,  3.999F, maxY,      18, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,  3.999F, maxY,       6, minU, minV, icon);
        // plane 2 front left
        addScaledVertexWithUV(tessellator,  4.001F, minY,      -2, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,  4.001F, minY,      10, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,  4.001F, maxY,      10, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,  4.001F, maxY,      -2, minU, minV, icon);
        // plane 2 back right
        addScaledVertexWithUV(tessellator,  3.999F, minY,       6, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,  3.999F, maxY,       6, minU, minV, icon);
        addScaledVertexWithUV(tessellator,  3.999F, maxY,      18, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,  3.999F, minY,      18, maxU, maxV, icon);
        // plane 2 back right
        addScaledVertexWithUV(tessellator,  4.001F, minY,      -2, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,  4.001F, maxY,      -2, minU, minV, icon);
        addScaledVertexWithUV(tessellator,  4.001F, maxY,      10, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,  4.001F, minY,      10, maxU, maxV, icon);

        // plane 3 front right
        addScaledVertexWithUV(tessellator,       6, minY, 11.999F, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,      18, minY, 11.999F, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,      18, maxY, 11.999F, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,       6, maxY, 11.999F, minU, minV, icon);
        // plane 3 front left
        addScaledVertexWithUV(tessellator,      -2, minY, 12.001F, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,      10, minY, 12.001F, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator,      10, maxY, 12.001F, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,      -2, maxY, 12.001F, minU, minV, icon);
        // plane 3 back right
        addScaledVertexWithUV(tessellator,       6, minY, 11.999F, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,       6, maxY, 11.999F, minU, minV, icon);
        addScaledVertexWithUV(tessellator,      18, maxY, 11.999F, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,      18, minY, 11.999F, maxU, maxV, icon);
        // plane 3 back left
        addScaledVertexWithUV(tessellator,      -2, minY, 12.001F, minU, maxV, icon);
        addScaledVertexWithUV(tessellator,      -2, maxY, 12.001F, minU, minV, icon);
        addScaledVertexWithUV(tessellator,      10, maxY, 12.001F, maxU, minV, icon);
        addScaledVertexWithUV(tessellator,      10, minY, 12.001F, maxU, maxV, icon);

        // plane 4 front right
        addScaledVertexWithUV(tessellator, 11.999F, minY,      18, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 11.999F, minY,       6, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY,       6, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY,      18, minU, minV, icon);
        // plane 4 front left
        addScaledVertexWithUV(tessellator, 12.001F, minY,      10, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 12.001F, minY,      -2, maxU, maxV, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY,      -2, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY,      10, minU, minV, icon);
        // plane 4 back right
        addScaledVertexWithUV(tessellator, 11.999F, minY,      18, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY,      18, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY,       6, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 11.999F, minY,       6, maxU, maxV, icon);
        // plane 4 back left
        addScaledVertexWithUV(tessellator, 12.001F, minY,      10, minU, maxV, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY,      10, minU, minV, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY,      -2, maxU, minV, icon);
        addScaledVertexWithUV(tessellator, 12.001F, minY,      -2, maxU, maxV, icon);
        // spotless:on
    }
}
