package com.gtnewhorizon.cropsnh.crops.witchery;

import java.awt.Color;

import net.minecraftforge.common.BiomeDictionary;

import com.gtnewhorizon.cropsnh.api.ISeedShape;
import com.gtnewhorizon.cropsnh.api.SeedShape;
import com.gtnewhorizon.cropsnh.crops.abstracts.NHCropCard;
import com.gtnewhorizon.cropsnh.utility.OreDictHelper;

public class CropMandrake extends NHCropCard {

    public CropMandrake() {
        super("mandrake", new Color(0x755239), new Color(0xBD9A61));

        this.addAlternateSeed(OreDictHelper.getCopiedOreStack("seedMandrake", 1));

        this.addDrop(OreDictHelper.getCopiedOreStack("itemMandrake", 1), 100_00);

        this.addLikedBiomes(BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY);
    }

    @Override
    public String getCreator() {
        return "bartimaeusnek";
    }

    @Override
    public String getUnlocalizedName() {
        return "tile.witchery:mandrake.name";
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
    public float getDropChance() {
        return super.getDropChance() * 0.5f;
    }

    @Override
    public ISeedShape getSeedShape() {
        return SeedShape.magic;
    }

    @Override
    protected String getBaseTexturePath() {
        return "witchery:mandrake_stage_";
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
