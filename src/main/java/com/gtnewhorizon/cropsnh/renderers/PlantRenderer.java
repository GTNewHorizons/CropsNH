package com.gtnewhorizon.cropsnh.renderers;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.gtnewhorizon.cropsnh.reference.Constants;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Mods;

@SideOnly(Side.CLIENT)
public abstract class PlantRenderer {

    public static final int RENDER_CROSS = 2;
    public static final int RENDER_X = 1;
    public static final int RENDER_HASHTAG = 0;

    public static void renderPlantLayer(IBlockAccess world, int x, int y, int z, int renderType, IIcon icon,
        boolean isSick) {
        renderPlantLayer2(world, x, y, z, renderType, icon, isSick);
    }

    public static void renderPlantLayer2(IBlockAccess world, int x, int y, int z, int renderType, IIcon icon,
        boolean isSick) {
        if (icon != null) {

            if (Mods.HodgePodge.isModLoaded()
                && icon instanceof com.mitchej123.hodgepodge.textures.IPatchedTextureAtlasSprite) {
                ((com.mitchej123.hodgepodge.textures.IPatchedTextureAtlasSprite) icon).markNeedsAnimationUpdate();
            } else if (Loader.isModLoaded("angelica")
                && icon instanceof com.gtnewhorizons.angelica.mixins.interfaces.IPatchedTextureAtlasSprite) {
                    ((com.gtnewhorizons.angelica.mixins.interfaces.IPatchedTextureAtlasSprite) icon)
                        .markNeedsAnimationUpdate();
                }

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
                    renderCrossPattern(tessellator, icon);
                    break;
                case RENDER_X:
                    renderXPattern(tessellator, icon);
                    break;
                case RENDER_HASHTAG:
                default:
                    renderHashTagPattern(tessellator, icon);
                    break;
            }
            tessellator.addTranslation(-x, -y, -z);
        }
    }

    private static void renderHashTagPattern(Tessellator tessellator, IIcon icon) {
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


    private static void renderCrossPattern(Tessellator tessellator, IIcon icon) {
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

    private static void renderXPattern(Tessellator tessellator, IIcon icon) {
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
        tessellator.addVertexWithUV(x * Constants.UNIT, y * Constants.UNIT, z * Constants.UNIT, u, v);
    }
}
