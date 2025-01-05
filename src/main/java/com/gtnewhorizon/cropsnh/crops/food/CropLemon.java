package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;

import gregtech.api.enums.ItemList;

public class CropLemon extends NHCropCard {

    private final static ISoilList soilTypes = SoilRegistry.instance.get("dirt");

    public CropLemon() {
        super("lemon", new Color(0xD8950C), new Color(0xFFEF83));
        // TODO: MOVE LEMON ITEM TO CROPS NH
        this.addDrop(ItemList.Crop_Drop_Lemon.get(1), 100_00);
        this.addAlternateSeed("seedLemon");
        this.addAlternateSeed("cropLemon");
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.food.register(this);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public ISoilList getSoilTypes() {
        return soilTypes;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }

}
