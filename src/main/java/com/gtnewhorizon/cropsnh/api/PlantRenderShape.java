package com.gtnewhorizon.cropsnh.api;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import com.gtnewhorizon.cropsnh.renderers.plantshape.FlowerPlantRenderer;
import com.gtnewhorizon.cropsnh.renderers.plantshape.HashPlantRenderer;
import com.gtnewhorizon.cropsnh.renderers.plantshape.XPlantRenderer;

public enum PlantRenderShape implements IPlantRenderShape {

    X(new XPlantRenderer()),
    Hash(new HashPlantRenderer()),
    Flower(new FlowerPlantRenderer()),;

    private final IPlantRenderShape renderer;

    PlantRenderShape(IPlantRenderShape renderer) {
        this.renderer = renderer;
    }

    @Override
    public String getName() {
        return renderer.getName();
    }

    @Override
    public void render(Tessellator tessellator, IIcon icon) {
        this.renderer.render(tessellator, icon);
    }

}
