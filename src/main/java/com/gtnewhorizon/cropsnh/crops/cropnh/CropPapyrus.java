package com.gtnewhorizon.cropsnh.crops.cropnh;

import java.awt.Color;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

public class CropPapyrus extends NHCropCard {

    public CropPapyrus() {
        super("papyrus", new Color(0xA3A3A3), new Color(0xEAEAEA));
        this.addDrop(new ItemStack(Items.paper, 1, 0), 100_00);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 3;
    }
}
