package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.init.CropsNHMutationPools;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropSnowbell extends NHCropCard {

    public CropSnowbell() {
        super("snowbell", new Color(0x33A9FF), new Color(0xA5D9FF));
        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("seedSnowbell", 1));
        this.addDrop(OreDictHelper.getCopiedOreStack("itemSnowbell", 1), 100_00);
    }

    @Override
    public void registerToPools() {
        super.registerToPools();
        CropsNHMutationPools.decorative.register(this);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:snowbell.name";
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public int getGrowthDuration() {
        return 1100;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    protected String getBaseTexturePath() {
        return "witchery:snowbell_stage_";
    }

    @Override
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
