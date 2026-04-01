package com.gtnewhorizon.cropsnh.crops.food;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.CropsNHSoilTypes;
import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISoilList;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.CropsNHUtils;
import com.gtnewhorizon.cropsnh.utility.ModUtils;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropLemon extends NHCropCard {

    private final static ISoilList soilTypes = CropsNHSoilTypes.dirt;

    public CropLemon() {
        super("lemon", new Color(0xD8950C), new Color(0xFFEF83));
        // TODO: MOVE LEMON ITEM TO CROPS NH
        this.addDrop(OreDictHelper.getCopiedOreStack("cropLemon"), 100_00);
        this.addAlternateSeed("seedLemon");
        this.addAlternateSeed("cropLemon");
        if (ModUtils.PamsHarvestCraft.isModLoaded()) {
            this.addAlternateSeed(CropsNHUtils.getModItem(ModUtils.PamsHarvestCraft, "pamlemonSapling", 1, 0));
        }
        // hates cold with a passion
        this.addLikedBiomes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.PLAINS);
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
