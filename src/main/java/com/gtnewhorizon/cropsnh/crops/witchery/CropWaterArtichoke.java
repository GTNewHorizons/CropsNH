package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.IPlantRenderShape;
import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.PlantRenderShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropWaterArtichoke extends CropFood {

    public CropWaterArtichoke() {
        super("waterArtichoke", new Color(0x6F644E), new Color(0x968C74));

        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("cropArtichoke", 1));

        this.addDrop(OreDictHelper.getCopiedOreStack("cropArtichoke", 1), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.OCEAN);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:artichoke.name";
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
    public IPlantRenderShape getRenderShape() {
        return PlantRenderShape.X;
    }

    @Override
    protected String getBaseTexturePath() {
        return "witchery:artichoke_stage_";
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
