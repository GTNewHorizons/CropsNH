package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.renderers.PlantRenderer;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropEmberMoss extends NHCropCard {

    public CropEmberMoss() {
        super("emberMoss", new Color(0x9C4D2C), new Color(0xE06F3F));
        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("cropEmberMoss", 1));
        this.addDrop(OreDictHelper.getCopiedOreStack("cropEmberMoss", 1), 100_00);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:embermoss.name";
    }

    @Override
    public int getTier() {
        return 7;
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
    public int getRenderShape() {
        return PlantRenderer.RENDER_X;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
