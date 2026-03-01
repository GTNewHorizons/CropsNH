package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropBelladonna extends NHCropCard {

    public CropBelladonna() {
        super("belladonna", new Color(0x000000), new Color(0x947999));

        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("seedBelladonna", 1));

        this.addDrop(OreDictHelper.getCopiedOreStack("itemBelladonna", 1), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.WET, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:belladonna.name";
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
        return "witchery:belladonna_stage_";
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
