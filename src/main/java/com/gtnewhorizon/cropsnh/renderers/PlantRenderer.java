package com.gtnewhorizon.cropsnh.renderers;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PlantRenderer {

    public static void renderPlantLayer(IBlockAccess world, int x, int y, int z, IPlantRenderShape renderShape,
        IIcon icon, boolean isSick) {
        renderPlantLayer2(world, x, y, z, renderShape, icon, isSick);
    }

    public static void renderPlantLayer2(IBlockAccess world, int x, int y, int z, IPlantRenderShape renderShape,
        IIcon icon, boolean isSick) {
        if (icon != null) {
            Tessellator tessellator = Tessellator.instance;
            tessellator.addTranslation(x, y, z);
            tessellator.setBrightness(Blocks.wheat.getMixedBrightnessForBlock(world, x, y, z));
            if (isSick) {
                tessellator.setColorOpaque_F(0.0F, 1.0F, 0.0F);
            } else {
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            }
            renderShape.render(tessellator, icon);
            tessellator.addTranslation(-x, -y, -z);
        }
    }

}
