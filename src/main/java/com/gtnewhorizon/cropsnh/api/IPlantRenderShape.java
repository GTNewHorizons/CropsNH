package com.gtnewhorizon.cropsnh.api;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public interface IPlantRenderShape {

    void render(Tessellator tessellator, IIcon icon);

    String getName();
}
