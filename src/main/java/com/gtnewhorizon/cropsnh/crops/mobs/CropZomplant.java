package com.gtnewhorizon.cropsnh.crops.mobs;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

public class CropZomplant extends NHCropCard {

    public CropZomplant() {
        super("zomplant", new Color(0x3A6529), new Color(0x679056));
        this.addDrop(new ItemStack(Items.rotten_flesh, 1, 0), 100_00);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 1800;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
