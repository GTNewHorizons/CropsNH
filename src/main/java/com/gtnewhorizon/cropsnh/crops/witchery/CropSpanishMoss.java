package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropSpanishMoss extends NHCropCard {

    public CropSpanishMoss() {
        super("spanishMoss", new Color(0x3F5227), new Color(0xC1CEBB));

        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("cropSpanishMoss", 1));

        this.addDrop(OreDictHelper.getCopiedOreStack("cropSpanishMoss", 1), 100_00);

        this.addLikedBiomes(
            BiomeDictionary.Type.LUSH,
            BiomeDictionary.Type.WET,
            BiomeDictionary.Type.FOREST,
            BiomeDictionary.Type.SWAMP);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:spanishmoss.name";
    }

    @Override
    public int getTier() {
        return 7;
    }

    @Override
    public int getGrowthDuration() {
        return 675;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    public int getMaxGrowthStage() {
        return 4;
    }
}
