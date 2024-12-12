package com.gtnewhorizon.cropsnh.crops.ic2;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import gregtech.api.enums.ItemList;

public class CropStickReed extends NHCropCard {

    private final static ISoilList soil = SoilRegistry.instance.get("sugarcane");

    public CropStickReed() {
        super("stickReed", new Color(0x8DB560), new Color(0xD9D891));
        this.addDrop(ItemList.IC2_Resin.get(1L), 10_000);
    }

    @Override
    public String getCreator() {
        return "raa1337";
    }

    @Override
    public ISoilList getSoilTypes() {
        return soil;
    }

    @Override
    public int getGrowthDuration() {
        return 200;
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.GRAINS;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }
}
