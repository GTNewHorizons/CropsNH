package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.CropFood;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropGarlic extends CropFood {

    public CropGarlic() {
        super("garlic", new Color(0xA88F7B), new Color(0xF3E7DC));

        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("cropGarlic", 1));

        this.addDrop(OreDictHelper.getCopiedOreStack("cropGarlic", 1), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.DRY);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:garlicplant.name";
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getGrowthDuration() {
        return 450;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    protected String getBaseTexturePath() {
        return "witchery:garlic_stage_";
    }

    @Override
    public int getMinGrowthStage() {
        return 0;
    }

    @Override
    public int getMaxGrowthStage() {
        return 5;
    }
}
