package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.farming.registries.SoilRegistry;

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
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 800;
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
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
