package com.gtnewhorizon.cropsnh.renderers;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.gtnewhorizon.cropsnh.reference.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PlantRenderer {

    public static final int RENDER_CROSS = 2;
    public static final int RENDER_X = 1;
    public static final int RENDER_HASHTAG = 0;

    public static void renderPlantLayer(IBlockAccess world, int x, int y, int z, int renderType, IIcon icon, int layer,
        boolean isSick) {
        renderPlantLayer2(world, x, y, z, renderType, icon, layer, isSick);
    }

    public static void renderPlantLayer2(IBlockAccess world, int x, int y, int z, int renderType, IIcon icon, int layer,
        boolean isSick) {
        if (icon != null) {
            Tessellator tessellator = Tessellator.instance;
            tessellator.addTranslation(x, y, z);
            tessellator.setBrightness(Blocks.wheat.getMixedBrightnessForBlock(world, x, y, z));
            if (isSick) {
                tessellator.setColorOpaque_F(0.0F, 1.0F, 0.0F);
            } else {
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            }
            switch (renderType) {
                case RENDER_CROSS:
                    renderCrossPattern(tessellator, icon, layer);
                    break;
                case RENDER_X:
                    renderXPattern(tessellator, icon, layer);
                    break;
                case RENDER_HASHTAG:
                default:
                    renderHashTagPattern(tessellator, icon, layer);
                    break;
            }
            tessellator.addTranslation(-x, -y, -z);
        }
    }

    private static void renderHashTagPattern(Tessellator tessellator, IIcon icon, int layer) {
        int minY = 16 * layer;
        int maxY = 16 * (layer + 1);
        // plane 1 front
        addScaledVertexWithUV(tessellator, 0, minY, 4, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 4, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 4, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 4, 0, 16, icon);
        // plane 1 back
        addScaledVertexWithUV(tessellator, 0, minY, 4, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 4, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 4, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 4, 16, 0, icon);
        // plane 2 front
        addScaledVertexWithUV(tessellator, 4, minY, 0, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 4, minY, 16, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 16, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 0, 0, 0, icon);
        // plane 2 back
        addScaledVertexWithUV(tessellator, 4, minY, 0, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 0, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 4, maxY, 16, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 4, minY, 16, 16, 16, icon);
        // plane 3 front
        addScaledVertexWithUV(tessellator, 0, minY, 12, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 12, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 12, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 12, 0, 0, icon);
        // plane 3 back
        addScaledVertexWithUV(tessellator, 0, minY, 12, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 0, maxY, 12, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 16, maxY, 12, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 16, minY, 12, 16, 16, icon);
        // plane 4 front
        addScaledVertexWithUV(tessellator, 12, minY, 16, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 12, minY, 0, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 0, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 16, 0, 0, icon);
        // plane 4 back
        addScaledVertexWithUV(tessellator, 12, minY, 16, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 16, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 12, maxY, 0, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 12, minY, 0, 16, 16, icon);
    }

    private static void renderCrossPattern(Tessellator tessellator, IIcon icon, int layer) {
        int minY = 12 * layer;
        int maxY = 12 * (layer + 1);
        // plane 1 front right
        addScaledVertexWithUV(tessellator, 6, minY, 4.001F, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 6, maxY, 4.001F, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 18, maxY, 4.001F, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 18, minY, 4.001F, 0, 16, icon);
        // plane 1 front left
        addScaledVertexWithUV(tessellator, -2, minY, 3.999F, 16, 16, icon);
        addScaledVertexWithUV(tessellator, -2, maxY, 3.999F, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 10, maxY, 3.999F, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 10, minY, 3.999F, 0, 16, icon);
        // plane 1 back right
        addScaledVertexWithUV(tessellator, 6, minY, 4.001F, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 18, minY, 4.001F, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 18, maxY, 4.001F, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 6, maxY, 4.001F, 16, 0, icon);
        // plane 1 back left
        addScaledVertexWithUV(tessellator, -2, minY, 3.999F, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 10, minY, 3.999F, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 10, maxY, 3.999F, 0, 0, icon);
        addScaledVertexWithUV(tessellator, -2, maxY, 3.999F, 16, 0, icon);
        // plane 2 front right
        addScaledVertexWithUV(tessellator, 3.999F, minY, 6, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 3.999F, minY, 18, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 3.999F, maxY, 18, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 3.999F, maxY, 6, 0, 0, icon);
        // plane 2 front left
        addScaledVertexWithUV(tessellator, 4.001F, minY, -2, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 4.001F, minY, 10, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 4.001F, maxY, 10, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 4.001F, maxY, -2, 0, 0, icon);
        // plane 2 back right
        addScaledVertexWithUV(tessellator, 3.999F, minY, 6, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 3.999F, maxY, 6, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 3.999F, maxY, 18, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 3.999F, minY, 18, 16, 16, icon);
        // plane 2 back right
        addScaledVertexWithUV(tessellator, 4.001F, minY, -2, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 4.001F, maxY, -2, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 4.001F, maxY, 10, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 4.001F, minY, 10, 16, 16, icon);
        // plane 3 front right
        addScaledVertexWithUV(tessellator, 6, minY, 11.999F, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 18, minY, 11.999F, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 18, maxY, 11.999F, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 6, maxY, 11.999F, 0, 0, icon);
        // plane 3 front left
        addScaledVertexWithUV(tessellator, -2, minY, 12.001F, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 10, minY, 12.001F, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 10, maxY, 12.001F, 16, 0, icon);
        addScaledVertexWithUV(tessellator, -2, maxY, 12.001F, 0, 0, icon);
        // plane 3 back right
        addScaledVertexWithUV(tessellator, 6, minY, 11.999F, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 6, maxY, 11.999F, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 18, maxY, 11.999F, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 18, minY, 11.999F, 16, 16, icon);
        // plane 3 back left
        addScaledVertexWithUV(tessellator, -2, minY, 12.001F, 0, 16, icon);
        addScaledVertexWithUV(tessellator, -2, maxY, 12.001F, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 10, maxY, 12.001F, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 10, minY, 12.001F, 16, 16, icon);
        // plane 4 front right
        addScaledVertexWithUV(tessellator, 11.999F, minY, 18, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 11.999F, minY, 6, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY, 6, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY, 18, 0, 0, icon);
        // plane 4 front left
        addScaledVertexWithUV(tessellator, 12.001F, minY, 10, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 12.001F, minY, -2, 16, 16, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY, -2, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY, 10, 0, 0, icon);
        // plane 4 back right
        addScaledVertexWithUV(tessellator, 11.999F, minY, 18, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY, 18, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 11.999F, maxY, 6, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 11.999F, minY, 6, 16, 16, icon);
        // plane 4 back left
        addScaledVertexWithUV(tessellator, 12.001F, minY, 10, 0, 16, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY, 10, 0, 0, icon);
        addScaledVertexWithUV(tessellator, 12.001F, maxY, -2, 16, 0, icon);
        addScaledVertexWithUV(tessellator, 12.001F, minY, -2, 16, 16, icon);
    }

    private static void renderXPattern(Tessellator tessellator, IIcon icon, int layer) {
        int minY = 12 * layer;
        int maxY = 12 * (layer + 1);
        int minX = 2;
        int maxX = 14;
        int minZ = 2;
        int maxZ = 14;
        // plane 1 front
        addScaledVertexWithUV(tessellator, minX, minY, minZ, 16, 16, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, minZ, 16, 0, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, maxZ, 0, 0, icon);
        addScaledVertexWithUV(tessellator, maxX, minY, maxZ, 0, 16, icon);
        // plane 1 back
        addScaledVertexWithUV(tessellator, maxX, minY, maxZ, 16, 16, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, maxZ, 16, 0, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, minZ, 0, 0, icon);
        addScaledVertexWithUV(tessellator, minX, minY, minZ, 0, 16, icon);
        // plane 2 front
        addScaledVertexWithUV(tessellator, minX, minY, maxZ, 0, 16, icon);
        addScaledVertexWithUV(tessellator, maxX, minY, minZ, 16, 16, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, minZ, 16, 0, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, maxZ, 0, 0, icon);
        // plane 2 front
        addScaledVertexWithUV(tessellator, maxX, minY, minZ, 0, 16, icon);
        addScaledVertexWithUV(tessellator, minX, minY, maxZ, 16, 16, icon);
        addScaledVertexWithUV(tessellator, minX, maxY, maxZ, 16, 0, icon);
        addScaledVertexWithUV(tessellator, maxX, maxY, minZ, 0, 0, icon);
    }

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
    private static void addScaledVertexWithUV(Tessellator tessellator, float x, float y, float z, float u, float v,
        IIcon icon) {
        tessellator.addVertexWithUV(
            x * Constants.UNIT,
            y * Constants.UNIT,
            z * Constants.UNIT,
            icon.getInterpolatedU(u),
            icon.getInterpolatedV(v));
    }
}
